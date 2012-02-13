/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minetool.dblp.parser.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author zlaja
 */
public class XMLUnmarshaller {

    protected URI uri;

    /**
     * Create an xml based loader of bookmarks.
     * @param uri the bookmark file
     */
    public XMLUnmarshaller(final URI uri) {
        this.uri = uri;
    }
    
    /**
     * Load bookmarks into a action list.
     * 
     * @throws IOException
     *             If an I/O error occurred.
     * @throws FileNotFoundException
     *             If the resource was not found.
     */
    public <T> T load(Class<T> docClass) throws IOException {
	final InputStream in = new FileInputStream(uri.getPath());

	if (in == null) {
	    throw new FileNotFoundException("Cannot find resource: " + uri);
	}

	try {
	    return load(docClass, in);
	} finally {
	    in.close();
	}
    }

    protected <T> T load(Class<T> docClass, final InputStream in) {
        T o = null;
        try {
            o = unmarshal(docClass, in);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    public <T> T unmarshal(Class<T> docClass, InputStream inputStream)
            throws JAXBException {
        String packageName = docClass.getPackage().getName();
        JAXBContext jc = JAXBContext.newInstance(packageName);
        Unmarshaller u = jc.createUnmarshaller();
        JAXBElement<T> doc = u.unmarshal(new StreamSource(inputStream), docClass);
        return doc.getValue();
    }
}
