/**
 * 2011 HuYao
 */
package com.minetool.dblp.parser.util;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.minetool.dblp.parser.AfterListener;
import com.minetool.dblp.parser.DblpResultWriter;
import com.minetool.dblp.parser.Processor;

/**
 * @author Hu Yao
 * 
 */
public class TestXMLUnmarshaller {

    @Test
    public void TestUnmarshall() throws URISyntaxException, IOException {

	try {
	    Processor p = new Processor("Z:\\smalldblp.xml");
	    DblpResultWriter w = new DblpResultWriter("Z:\\result.txt");
	    p.setPostListener(new AfterListener(w));
	    w.open();
	    p.process();
	    w.close();
	} catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
