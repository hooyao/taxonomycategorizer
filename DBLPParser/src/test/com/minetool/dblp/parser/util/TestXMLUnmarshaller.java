/**
 * 2011 HuYao
 */
package com.minetool.dblp.parser.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.minetool.dblp.parser.model.Dblp;

/**
 * @author Hu Yao
 *
 */
public class TestXMLUnmarshaller {

    @Test
    public void TestUnmarshall() throws URISyntaxException, IOException{
	File f = new File("Z:\\dblp.xml");
	XMLUnmarshaller um = new XMLUnmarshaller(f.toURI());
	Dblp dblpDb = um.load(Dblp.class);
	System.out.println(dblpDb);
	
    }
}
