package com.lem.model;

import com.lem.bean.StringItem;
import com.lem.bean.StringsFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StringsContentLoader {
  public static final String PREFIX_ARRAY_ITEM = "$array_";
  public static final String PREFIX_STRING_ARRAY_ITEM = "$strarray_";
  private StringsFile raw;

  private Document doc;
  private Element rootEl;

  private List<StringItem> stringsContents = new ArrayList<>();
  private List<StringItem> stringsArrayContents = new ArrayList<>();

  public StringsContentLoader(StringsFile raw) {
    this.raw = raw;
  }

  public void load() throws IOException, SAXException, ParserConfigurationException {
    loadFile();
    stringsContents.clear();
    loadElement("string");
    stringsArrayContents.clear();
    loadArrayElement("string-array", PREFIX_STRING_ARRAY_ITEM);
    loadArrayElement("array", PREFIX_ARRAY_ITEM);

    List<StringItem> allItem = new ArrayList<>();
    allItem.addAll(stringsContents);
    allItem.addAll(stringsArrayContents);
    raw.setItemList(allItem);
    raw.setItemMap(allItem);
  }

  /**
   * 加载文件
   */
  private void loadFile() throws ParserConfigurationException, IOException, SAXException {
    if (this.raw == null || this.raw.getFile() == null || !this.raw.getFile().isFile()) {
      throw new IllegalArgumentException("resource document file is illegal");
    }
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    doc = db.parse(this.raw.getFile());
    rootEl = doc.getDocumentElement();
  }

  /**
   * 加载单个节点
   */
  private void loadElement(String element) {
    NodeList nodeList = rootEl.getElementsByTagName(element);

    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      NamedNodeMap attrs = node.getAttributes();
      if (attrs == null || attrs.getNamedItem("name") == null) {
        continue;
      }
      boolean translatable = true;
      Node transNode = attrs.getNamedItem("translatable");
      if (transNode != null && "false".equals(transNode.getNodeValue())) {
        translatable = false;
      }
      stringsContents.add(
          new StringItem(attrs.getNamedItem("name").getNodeValue(), node.getChildNodes().item(0).getNodeValue(),
              translatable));
    }
  }

  /**
   * 加载数组类型
   */
  private void loadArrayElement(String element, String arrayPrefixTag) {
    NodeList nodeList = rootEl.getElementsByTagName(element);
    for (int i = 0; i < nodeList.getLength(); i++) {
      NamedNodeMap nodeMap = nodeList.item(i).getAttributes();
      if (nodeMap == null || nodeMap.getNamedItem("name") == null) {
        continue;
      }
      String name = nodeMap.getNamedItem("name").getNodeValue();
      boolean translatable = true;
      Node transNode = nodeMap.getNamedItem("translatable");
      if (transNode != null && "false".equals(transNode.getNodeValue())) {
        translatable = false;
      }
      NodeList childNodes = nodeList.item(i).getChildNodes();
      for (int j = 0; j < childNodes.getLength(); j++) {
        Node item = childNodes.item(j);
        if (!"item".equals(item.getNodeName())) {
          continue;
        }
        NodeList childNodeList = item.getChildNodes();
        if (childNodeList == null) {
          stringsArrayContents.add(new StringItem(arrayPrefixTag + name + "_" + j, " ", translatable));
        } else {
          stringsArrayContents.add(
              new StringItem(arrayPrefixTag + name + "_" + j, childNodeList.item(0).getNodeValue(), translatable));
        }
      }
    }
  }

  public List<StringItem> getStringsContents() {
    return stringsContents;
  }

  public List<StringItem> getStringsArrayContents() {
    return stringsArrayContents;
  }
}
