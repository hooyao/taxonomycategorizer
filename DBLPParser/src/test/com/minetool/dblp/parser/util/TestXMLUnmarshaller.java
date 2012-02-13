/**
 * 2011 HuYao
 */
package com.minetool.dblp.parser.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.minetool.dblp.parser.model.AbstractDblpObj;
import com.minetool.dblp.parser.model.Dblp;

/**
 * @author Hu Yao
 * 
 */
public class TestXMLUnmarshaller {

    @Test
    public void TestUnmarshall() throws URISyntaxException, IOException {
	try {
	    File f = new File("Z:\\dblp.xml");
	    String packageName = Dblp.class.getPackage().getName();
	    JAXBContext jc = JAXBContext.newInstance(packageName);
	    // final InputStream in = new FileInputStream(f);
	    Unmarshaller u = jc.createUnmarshaller();
	    u.setListener(new Unmarshaller.Listener() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.xml.bind.Unmarshaller.Listener#afterUnmarshal(java.lang
		 * .Object, java.lang.Object)
		 */
		@Override
		public void afterUnmarshal(Object target, Object parent) {
		    if(target instanceof AbstractDblpObj){
			((Dblp)parent).getArticleOrInproceedingsOrProceedingsOrBookOrIncollectionOrPhdthesisOrMastersthesisOrWww().clear();
			((AbstractDblpObj) target).getTitle();
		    }
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.xml.bind.Unmarshaller.Listener#beforeUnmarshal(java
		 * .lang .Object, java.lang.Object)
		 */
		@Override
		public void beforeUnmarshal(Object target, Object parent) {
		    if(target instanceof AbstractDblpObj){
			((AbstractDblpObj) target).getTitle();
		    }
		}

	    });

	    // create a new XML parser
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setNamespaceAware(true);
	    XMLReader reader = factory.newSAXParser().getXMLReader();
	    reader.setContentHandler(u.getUnmarshallerHandler());
	    reader.parse(f.toURI().toString());
	    
	} catch (JAXBException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ParserConfigurationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
