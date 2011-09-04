package test.com.minetool.tax.model.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.minetool.tax.model.data.ACMContentHandler;

public class testContentHandler {
    
    @Test
    public void testACMContentHandler() throws SAXException, IOException{
	InputStream is = testContentHandler.class.getClass().getResourceAsStream("/com/minetool/tax/model/data/acm/acm_tax.xml");
	String s =new Scanner(is).useDelimiter("\\A").next();
	byte[] data = s.getBytes();
	XMLReader parser = XMLReaderFactory.createXMLReader();
	ACMContentHandler contentHandler = new ACMContentHandler();
	parser.setContentHandler(contentHandler);
	ByteArrayInputStream boi = new ByteArrayInputStream(data);
	InputSource source = new InputSource(boi);
	parser.parse(source);
    }

}
