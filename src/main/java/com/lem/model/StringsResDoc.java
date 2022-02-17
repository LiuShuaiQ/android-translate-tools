package com.lem.model;

import com.lem.bean.NValue;
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

public class StringsResDoc {
  private File raw;

  private List<NValue> contents;
  private Document doc;
  private Element rootEl;

  public StringsResDoc(File raw) {
    this.raw = raw;
  }

  public void load() throws IOException, SAXException, ParserConfigurationException {
    loadFile();
    loadStringElement();
  }

  private void loadFile() throws ParserConfigurationException, IOException, SAXException {
    if (this.raw == null || !this.raw.isFile()) {
      throw new IllegalArgumentException("resource document file is illegal");
    }
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    doc = db.parse(this.raw);
    rootEl = doc.getDocumentElement();
  }

  private void loadStringElement() {
    NodeList nodeList = rootEl.getElementsByTagName("string");
    contents = new ArrayList<>();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      NamedNodeMap attrs = node.getAttributes();
      if (attrs == null || attrs.getNamedItem("name") == null) {
        continue;
      }
      contents.add(new NValue(attrs.getNamedItem("name").getNodeName(), node.getNodeValue()));
    }
  }

  public List<NValue> getContents() {
    return contents;
  }

  public void setContents(List<NValue> contents) {
    this.contents = contents;
  }
}
