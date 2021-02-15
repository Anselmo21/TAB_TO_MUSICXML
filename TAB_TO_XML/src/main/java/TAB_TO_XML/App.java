package TAB_TO_XML;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Model.Part;

public class App {
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new XmlMapper();
			InputStream inputStream = new FileInputStream("C:\\Users\\shawn\\Desktop\\parts1.xml");
			TypeReference<List<Part>> typeReference = new TypeReference<List<Part>>() {};
			List<Part> parts = mapper.readValue(inputStream, typeReference);
			
			for (Part p : parts) {
				System.out.println("name is: " + p.getId());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
