/**
 * 2011 HuYao
 */
package com.minetool.tax.model.data;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.minetool.tax.model.TaxCategory;
import com.minetool.tax.model.TaxNode;

/**
 * @author HuYao
 * 
 */
public class ACMContentHandler implements ContentHandler {
    private TaxCategory _rootModel  = new TaxCategory("ACM Taxonomy",null);
    private TaxNode _currentNode;
    
    public static final String TAG_GROUP = "group";
    public static final String TAG_ELE = "element";
    public static final String TAG_TITLE = "title";

    private int groupDepth = 0;
    private int eleDepth = 0;

    private boolean isInGroup = false;
    private boolean isInEle = false;
    private boolean isInTitle = false;

    public ACMContentHandler(){
	_currentNode = _rootModel;
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	if(this._currentNode!=null){
	    if(isInGroup){
		
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	if (qName.equalsIgnoreCase(TAG_GROUP)) {
	    groupDepth--;
	    isInGroup = false;
	    
	}
	if (qName.equalsIgnoreCase(TAG_ELE)) {
	    eleDepth--;
	    isInEle = false;
	    
	    _currentNode

	}
	if (qName.equalsIgnoreCase(TAG_TITLE)) {
	    isInTitle = false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
	    throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void processingInstruction(String target, String data)
	    throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
     */
    @Override
    public void setDocumentLocator(Locator locator) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    @Override
    public void skippedEntity(String name) throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes atts) throws SAXException {
	// TODO Auto-generated method stub
	if (qName.equalsIgnoreCase(TAG_GROUP)) {
	    groupDepth++;
	    isInGroup = true;
	    isInEle = false;
	    isInTitle = false;
	}
	if (qName.equalsIgnoreCase(TAG_ELE)) {
	    eleDepth++;
	    isInEle = true;
	    isInGroup = false;
	    isInTitle = false;

	}
	if (qName.equalsIgnoreCase(TAG_TITLE)) {
	    isInEle = false;
	    isInGroup = false;
	    isInTitle = true;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void startPrefixMapping(String prefix, String uri)
	    throws SAXException {
	// TODO Auto-generated method stub

    }

}
