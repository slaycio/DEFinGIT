package pl.altoriis.def;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;

public class properties {

	public Integer dbType;
	public String url;
	public String user;
	public String password;

	properties() {

		try {

			File fXmlFile = new File("./def.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

		
			NodeList nList = doc.getElementsByTagName("database");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					this.dbType = Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent());
					
					if (dbType == 0){
					url = eElement.getElementsByTagName("url:0").item(0).getTextContent();
					user = eElement.getElementsByTagName("user:0").item(0).getTextContent();
					password = eElement.getElementsByTagName("password:0").item(0).getTextContent();
					} 
					if (dbType == 1) {
						try {
							Class.forName("org.sqlite.JDBC");
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					url = eElement.getElementsByTagName("url:1").item(0).getTextContent();	
					}
					if (dbType == 2) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					url = eElement.getElementsByTagName("url:2").item(0).getTextContent();	
					user = eElement.getElementsByTagName("user:2").item(0).getTextContent();
					password = eElement.getElementsByTagName("password:2").item(0).getTextContent();
					}
					
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}/* end of properties() method*/
}/* end of properties class */
