package com.lem.model;

import com.lem.ArgsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static com.lem.model.StringsContentLoader.PREFIX_ARRAY_ITEM;
import static com.lem.model.StringsContentLoader.PREFIX_STRING_ARRAY_ITEM;

public class DecodeExcelTask implements Runnable {
  private ArgsConfig config;

  public DecodeExcelTask(ArgsConfig config) {
    this.config = config;
  }

  @Override public void run() {
    // read file
    File excel = config.getProjectDir();
    if (!excel.exists()) {
      throw new IllegalArgumentException("input excel file is not exists");
    }
    if (!excel.isFile()) {
      throw new IllegalArgumentException("excel path is not file");
    }
    try {
      processExcel(excel);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  private void processExcel(File excel) throws ParserConfigurationException {
    List<List<String>> datas = ExcelReader.importFileToData(excel, "datas");

    List<Document> xmlDocs = new ArrayList<>();
    List<File> xmlFiles = new ArrayList<>();

    List<String> preArrayNames = new ArrayList<>();
    List<Element> preArrayElements = new ArrayList<>();
    // build Document
    for (int i = 0; i < datas.size(); i++) {
      String itemName = "";
      for (int j = 0; j < datas.get(i).size(); j++) {
        if (i == 0) {
          if (j == 0) {
            continue;
          }
          // 每一列都是一个文件，除了第一列
          xmlFiles.add(new File("data/values-" + datas.get(i).get(j), "strings.xml"));
          Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
          Element root = doc.createElement("resources");
          doc.appendChild(root);
          xmlDocs.add(doc);
          // init array
          preArrayNames.add(null);
          preArrayElements.add(null);
          continue;
        } else {
          if (j == 0) {
            // 第一列获取到资源名称
            itemName = datas.get(i).get(j);
            continue;
          }
          if (itemName.startsWith(PREFIX_ARRAY_ITEM) || itemName.startsWith(
              PREFIX_STRING_ARRAY_ITEM)) {
            String arrayName = itemName.substring(0, itemName.lastIndexOf("_"));
            String arrValueName = null;
            if (itemName.startsWith(PREFIX_STRING_ARRAY_ITEM)) {
              arrayName = arrayName.substring(PREFIX_STRING_ARRAY_ITEM.length());
              arrValueName = "string-array";
            } else {
              arrayName = arrayName.substring(PREFIX_ARRAY_ITEM.length());
              arrValueName = "array";
            }
            // 对于第一个节点或者下一个节点是新的数组的，新建一个节点
            Element preEle = preArrayElements.get(j - 1);
            String preEleName = preArrayNames.get(j - 1);
            if (preEle == null || !arrayName.equals(preEleName)) {
              Element arrElement = xmlDocs.get(j - 1).createElement(arrValueName);
              arrElement.setAttribute("name", arrayName);
              xmlDocs.get(j - 1).getDocumentElement().appendChild(arrElement);
              preEle = arrElement;
              preEleName = arrayName;
              preArrayElements.set(j - 1, arrElement);
              preArrayNames.set(j - 1, arrayName);
            }
            // 是一个Array
            Element stringEle = xmlDocs.get(j - 1).createElement("item");
            stringEle.setTextContent(datas.get(i).get(j));
            preEle.appendChild(stringEle);
          } else {
            Element stringEle = xmlDocs.get(j - 1).createElement("string");
            stringEle.setAttribute("name", itemName);
            stringEle.setTextContent(datas.get(i).get(j));
            xmlDocs.get(j - 1).getDocumentElement().appendChild(stringEle);

            preArrayElements.set(j - 1, null);
            preArrayNames.set(j - 1, null);
          }
        }
      }
    }

    for (int i = 0; i < xmlFiles.size(); i++) {
      try {
        XMLWriter.writeData(xmlDocs.get(i), xmlFiles.get(i));
      } catch (TransformerException e) {
        e.printStackTrace();
      }
    }

    //  debug log:
    //System.out.println("===start===");
    //for (List<String> data : datas) {
    //  System.out.println("--------");
    //  for (String datum : data) {
    //    System.out.print("<" + datum + "> ");
    //  }
    //  System.out.println();
    //}
    //System.out.println("===end===");
  }
}
