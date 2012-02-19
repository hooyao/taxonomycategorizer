package com.minetool.dblp.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.minetool.dblp.parser.model.Dblp;

public class Processor {
    private String filePath = ""; //$NON-NLS-1$

    ProcessListener postListener;

    ProcessListener beforeListener;

    public Processor(String xmlPath) {
	this.filePath = xmlPath;
    }

    public void process() {
	try {
	    File f = new File(this.filePath);
	    String packageName = Dblp.class.getPackage().getName();
	    JAXBContext jc = JAXBContext.newInstance(packageName);
	    Unmarshaller u = jc.createUnmarshaller();
	    u.setListener(new Unmarshaller.Listener() {
		@Override
		public void afterUnmarshal(Object target, Object parent) {
		    if (Processor.this.postListener != null)
			Processor.this.postListener.run(target, parent);
		}

		@Override
		public void beforeUnmarshal(Object target, Object parent) {
		    if (Processor.this.beforeListener != null)
			Processor.this.beforeListener.run(target, parent);
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
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public ProcessListener getBeforeListener() {
	return this.beforeListener;
    }

    public void setBeforeListener(ProcessListener beforeListener) {
	this.beforeListener = beforeListener;
    }

    public ProcessListener getPostListener() {
	return this.postListener;
    }

    public void setPostListener(ProcessListener postListener) {
	this.postListener = postListener;
    }

    public static interface ProcessListener {
	public void run(Object target, Object parent);
    }
}
