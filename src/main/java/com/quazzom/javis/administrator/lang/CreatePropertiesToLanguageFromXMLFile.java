package com.quazzom.javis.administrator.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreatePropertiesToLanguageFromXMLFile implements CreatePropertiesToLanguage {

  private static final String TAG_TEXT = "text";
  private static final String ATTRIBUTE_KEY = "key";

  private LanguageIdiom languageIdiom;
  private LanguagePathToFile languagePathToFile;
  private InputStream xmlInputStream;

  public CreatePropertiesToLanguageFromXMLFile(
      LanguageIdiom languageIdiom, LanguagePathToFile languagePathToFile) {
    this(languageIdiom, languagePathToFile, null);
  }

  public CreatePropertiesToLanguageFromXMLFile(
      LanguageIdiom languageIdiom,
      LanguagePathToFile languagePathToFile,
      InputStream xmlInputStream) {
    this.languageIdiom = languageIdiom;
    this.languagePathToFile = languagePathToFile;
    this.xmlInputStream = xmlInputStream;
  }

  @Override
  public Properties createProperties() throws CreatePropertiesToLanguageException {

    Properties propertiesLang = new Properties();

    try {

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();

      InputStream file = loadXMLFile();
      Document document = builder.parse(file);

      document.getDocumentElement().normalize();

      NodeList nodeListTagsText = document.getElementsByTagName(TAG_TEXT);

      for (int i = 0; i < nodeListTagsText.getLength(); i++) {

        Node nodeTagText = nodeListTagsText.item(i);

        if (nodeTagText.getNodeType() == Node.ELEMENT_NODE) {

          Element elementText = (Element) nodeTagText;

          Node nodeTheLanguageTag =
              elementText.getElementsByTagName(languageIdiom.getValue()).item(0);

          String value;

          if (nodeTheLanguageTag.hasChildNodes()) {
            value = getElementContent(nodeTheLanguageTag, 0);
          } else {
            value = nodeTheLanguageTag.getTextContent();
          }

          String key = elementText.getAttribute(ATTRIBUTE_KEY);

          propertiesLang.put(key, value);
        }
      }
    } catch (Exception e) {
      throw new CreatePropertiesToLanguageException(e.getMessage());
    }
    return propertiesLang;
  }

  private InputStream loadXMLFile() throws FileNotFoundException {

    if (xmlInputStream != null) return xmlInputStream;

    String fullFileXMLPath = LanguageFromFile.FOLDER_TO_LANGUAGES + languagePathToFile.getPath();

    return new FileInputStream(fullFileXMLPath);
  }

  // the countTagsLevel MUST start with 0 value (to jump de parent langague tag
  // i.e en_us)
  private String getElementContent(Node node, int countTagsLevel) {

    StringBuilder content = new StringBuilder();

    if (node.getNodeType() == Node.ELEMENT_NODE) {

      // jump de first parent tag! this tag is the tag language
      // i.e: en_us
      if (countTagsLevel > 0) {
        content
            .append("<")
            .append(node.getNodeName())
            .append(createAStringWithAttributesInsideTheTag(node))
            .append(">");
      }

      NodeList childNodes = node.getChildNodes();
      for (int i = 0; i < childNodes.getLength(); i++) {
        Node child = childNodes.item(i);

        if (child.getNodeType() == Node.ELEMENT_NODE) {
          content.append(getElementContent(child, countTagsLevel + 1));
        } else if (child.getNodeType() == Node.TEXT_NODE) {
          content.append(child.getTextContent());
        }
      }

      // jump de first parent tag! this tag is the tag language
      // i.e: en_us
      if (countTagsLevel > 0) {
        content.append("</").append(node.getNodeName()).append(">");
      }

      return content.toString();
    }

    if (node.getNodeType() == Node.TEXT_NODE) {
      content.append(node.getTextContent());
    }

    return content.toString();
  }

  // return a empty string with tag doesn't have attributes
  private String createAStringWithAttributesInsideTheTag(Node node) {

    StringBuilder atts = new StringBuilder();

    // add all attributes inside the tag if any
    NamedNodeMap attributes = node.getAttributes();
    for (int i = 0; i < attributes.getLength(); i++) {
      Node attr = attributes.item(i);
      atts.append(" ")
          .append(attr.getNodeName())
          .append("=\"")
          .append(attr.getNodeValue())
          .append("\"");
    }
    return atts.toString();
  }
}
