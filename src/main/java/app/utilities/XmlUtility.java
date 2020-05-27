package app.utilities;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlUtility {
	private static XmlUtility xmlUtility;
	private static File xmlfile ;
	private static SAXReader   reader;
	
	private XmlUtility() {}
	
	public static XmlUtility getInstance()
	{
		return (xmlUtility==null) ? xmlUtility =new XmlUtility() : xmlUtility;
	}
	
	public  String fetchdata(String locaterName)
	{ 
		String loc ="";
		try {
			xmlfile = new File(ConfigManager.getInstance().getProperty("XmlPath"));
			reader = new SAXReader();
			Document document = reader.read(xmlfile);
			
			 List<Node> nodes = document.selectNodes("/TestData/element");
			
			for (Node node : nodes)
			{
				if (node.valueOf("@name").contentEquals(locaterName))
				{
					loc= node.selectSingleNode("locator").getText();
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loc;
	}

	

}
