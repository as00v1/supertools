package com.qiaohx.utils.transfer;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * xml处理的工具
 */
public class XmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    private static final String CHARSET = "UTF-8";

    /**
     * xml转map
     * @param xml
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @deprecated 已废掉，这个不好用，直接用xmlToJSON()
     */
    public static Map<String, Object> xmlToMap(String xml) throws ParserConfigurationException,IOException, SAXException {
        Map<String, Object> resultMap = new LinkedHashMap<>();

        if (xml == null || "".equals(xml)){
            return resultMap;
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new ByteArrayInputStream(xml.getBytes(CHARSET)));
        Element element = document.getDocumentElement();
        element.getTagName();
        logger.info(element.getTagName());
        return resultMap;
    }

    /**
     * xml字符串转换JSON格式
     * @param xml
     * @return
     */
    public static JSONObject xmlToJSON(String xml){
        if ("".equals(xml))
            return new JSONObject();
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resultStr = xmlSerializer.read(xml).toString();
        JSONObject result = JSONObject.fromObject(resultStr);
        return result;
    }

    public static JSONObject xmlToJSON(InputStream stream){
        if (stream == null)
            return new JSONObject();
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resultStr = xmlSerializer.readFromStream(stream).toString();
        JSONObject result = JSONObject.fromObject(resultStr);
        return result;
    }
}
