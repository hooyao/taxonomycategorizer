/**
 * 2011 HuYao
 */
package com.minetool.tax.model.data;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.minetool.tax.model.TaxCategory;
import com.minetool.tax.model.TaxElement;
import com.minetool.tax.model.TaxNode;

/**
 * @author HuYao
 * 
 * @version $Revision: 1.0 $
 */
public class ACMContentHandler implements ContentHandler {
    private TaxCategory _rootModel = new TaxCategory("ACM Taxonomy", null); //$NON-NLS-1$

    /**
     * 
     * @return the _rootModel
     */
    public TaxCategory getRootModel() {
	return this._rootModel;
    }

    private TaxNode _currentNode;
    private StringBuffer sb = new StringBuffer();

    /**
     * 
     */
    public static final String TAG_GROUP = "group"; //$NON-NLS-1$
    /**
     * 
     */
    public static final String TAG_ELE = "element"; //$NON-NLS-1$
    /**
     * 
     */
    public static final String TAG_TITLE = "title"; //$NON-NLS-1$

    private Stack<String> curPathBaseStack = new Stack<String>();
    private Stack<Integer> curPathIdxStack = new Stack<Integer>();

    private boolean isInEle = false;
    private boolean isInTitle = false;
    private boolean isCurrentNodeCreated = false;
    private boolean isUseEleTypeValue = false;

    private int eleTypeIdxValue = 0;
    private String eleType = ""; //$NON-NLS-1$

    /**
     * 
     */
    public ACMContentHandler() {
	this._currentNode = this._rootModel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @SuppressWarnings("boxing")
    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	String value = String.copyValueOf(ch, start, length).trim();
	value.replace("\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
	value.replace("\r", ""); //$NON-NLS-1$ //$NON-NLS-2$
	// System.out.println(value);
	if (this._currentNode != null) {
	    if (this.isInEle) {
		String path = ""; //$NON-NLS-1$
		String curPathBase = this.curPathBaseStack.peek();
		int idx = this.curPathIdxStack.peek();
		if (this.isUseEleTypeValue) {
		    if (this.eleType.equals("1")) { //$NON-NLS-1$
			path = String.valueOf(this.eleTypeIdxValue);
		    } else if (this.eleType.equals("A")) { //$NON-NLS-1$
			path = Character
				.toString((char) ('A' + this.eleTypeIdxValue - 1));
		    } else if (this.eleType.equals("a")) { //$NON-NLS-1$
			path = Character
				.toString((char) ('a' + this.eleTypeIdxValue - 1));
		    }
		} else if (curPathBase.equals("1")) { //$NON-NLS-1$
		    path = String.valueOf(idx);
		} else if (curPathBase.equals("A")) { //$NON-NLS-1$
		    path = Character.toString((char) ('A' + idx));
		} else if (curPathBase.equals("a")) { //$NON-NLS-1$
		    path = Character.toString((char) ('a' + idx));
		}
		if (value.length() > 0) {
		    if (!this.isCurrentNodeCreated) {
			TaxElement eleNode = new TaxElement("", //$NON-NLS-1$
				this._currentNode);
			eleNode.setPath(path);
			this._currentNode.getChildList().add(eleNode);
			this._currentNode = eleNode;
			this.isCurrentNodeCreated = true;
			idx++;
			this.curPathIdxStack.pop();
			this.curPathIdxStack.push(idx);
		    }
		    this.sb.append(value);
		} else if (!this.isCurrentNodeCreated) {
		    TaxCategory catNode = new TaxCategory("", this._currentNode); //$NON-NLS-1$
		    catNode.setPath(path);
		    this._currentNode.getChildList().add(catNode);
		    this._currentNode = catNode;
		    this.isCurrentNodeCreated = true;
		    idx++;
		    this.curPathIdxStack.pop();
		    this.curPathIdxStack.push(idx);
		}
	    }
	    if (this.isInTitle) {
		if (this._currentNode instanceof TaxCategory) {
		    TaxCategory cat = (TaxCategory) this._currentNode;
		    cat.setTitle(cat.getTitle() + value);
		}
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
	    this.curPathBaseStack.pop();
	    this.curPathIdxStack.pop();
	}
	if (qName.equalsIgnoreCase(TAG_ELE)) {
	    this.isInEle = false;
	    if (this._currentNode instanceof TaxElement) {
		TaxElement ele = (TaxElement) this._currentNode;
		ele.setEleEntry(this.sb.toString());
		// System.out.println(sb);
	    }
	    this._currentNode = this._currentNode.getParent();
	}
	if (qName.equalsIgnoreCase(TAG_TITLE)) {
	    this.isInTitle = false;
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
    @SuppressWarnings("boxing")
    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes atts) throws SAXException {
	// TODO Auto-generated method stub
	if (qName.equalsIgnoreCase(TAG_GROUP)) {
	    this.isInEle = false;
	    this.isInTitle = false;
	    String type = atts.getValue("type"); //$NON-NLS-1$
	    String sStart = atts.getValue("start"); //$NON-NLS-1$
	    try {
		this.curPathIdxStack.push(Integer.parseInt(sStart));
	    } catch (NumberFormatException e) {
		this.curPathIdxStack.push(0);
	    }
	    if (type.equals("A")) { //$NON-NLS-1$
		this.curPathBaseStack.push("A"); //$NON-NLS-1$
	    } else if (type.equals("a")) { //$NON-NLS-1$
		this.curPathBaseStack.push("a"); //$NON-NLS-1$

	    } else if (type.equals("1")) { //$NON-NLS-1$
		this.curPathBaseStack.push("1"); //$NON-NLS-1$
	    }
	    // System.out.println(type);
	}
	if (qName.equalsIgnoreCase(TAG_ELE)) {
	    this.isInEle = true;
	    this.isInTitle = false;
	    this.isCurrentNodeCreated = false;
	    this.sb.delete(0, this.sb.length());
	    String type = atts.getValue("type"); //$NON-NLS-1$
	    String value = atts.getValue("value"); //$NON-NLS-1$
	    if (type != null && value != null) {
		this.isUseEleTypeValue = true;
		this.eleType = type;
		this.eleTypeIdxValue = Integer.parseInt(value);
	    } else {
		this.isUseEleTypeValue = false;
	    }
	}
	if (qName.equalsIgnoreCase(TAG_TITLE)) {
	    this.isInEle = false;
	    this.isInTitle = true;
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
