package com.qiaohx.test.xml;

import com.qiaohx.utils.transfer.XmlUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLTest {

    @Test
    public void xmlTest(){

        String xml = "<xml><test>123</test><confs><conf>1</conf><conf>2</conf></confs></xml>";
        try {
            JSONObject jsonObject = XmlUtils.xmlToJSON(xml);
            System.out.println(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
