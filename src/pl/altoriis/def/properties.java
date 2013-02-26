package pl.altoriis.def;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;

public class properties {

	public String url;
	public String user;
	public String password;

	properties() {

		try {

			File fXmlFile = new File("src/pl/altoriis/def/properties.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// System.out.println("Root element :" +
			// doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("database");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					this.url = eElement.getElementsByTagName("url").item(0).getTextContent();
					this.user = eElement.getElementsByTagName("user").item(0).getTextContent();
					this.password = eElement.getElementsByTagName("password").item(0).getTextContent();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/* end of properties() method*/
}/* end of properties class */
