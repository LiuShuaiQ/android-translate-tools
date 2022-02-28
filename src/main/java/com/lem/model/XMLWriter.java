package com.lem.model;

import java.io.File;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XMLWriter {

  /**
   * 写数据
   */
  public static void writeData(Document document, File file) throws TransformerException {
    File parent = file.getParentFile();
    if (!parent.exists()) {
      parent.mkdirs();
    }
    TransformerFactory tff = TransformerFactory.newInstance();
    Transformer tf = tff.newTransformer();
    tf.setOutputProperty(OutputKeys.INDENT, "yes");// 节点换行
    tf.transform(new DOMSource(document), new StreamResult(file));
  }
}
