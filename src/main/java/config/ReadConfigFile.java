package config;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

public class ReadConfigFile {

    static String projectPath = System.getProperty("user.dir");


    public static String getProperties(String key) throws IOException {

        String result = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(projectPath + "/src/configFiles/config.xml");
            result = doc.getElementsByTagName(key).item(0).getFirstChild().getNodeValue();
//            System.out.println(result);

            }catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
