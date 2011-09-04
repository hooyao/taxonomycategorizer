/**
 * 
 */
package com.gemalto.tools.xmltest;

import java.util.ArrayList;
import java.util.UUID;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.gemalto.tools.model.VoteResponse;

/**
 * @author yaohu
 *
 */
public class VoteResContentHandler implements ContentHandler
{

	private VoteResponse model;
	
	private ArrayList<String> currentOps;
	private boolean insideUUID= false;
	private boolean insideOps = false;
	private boolean insideOp = false;
	
	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException
	{
		String value = String.copyValueOf(arg0, arg1, arg2);
		//System.out.println(value);
		if(this.model!=null)
		{
			if(this.insideUUID)
			{
				this.model.setVoteId(UUID.fromString(value));
			}
			if(this.insideOps)
			{
				//TODO
			}
			if(this.insideOp)
			{
				if(this.currentOps!=null)
				{
					this.currentOps.add(value);
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String arg0, String arg1, String qName)
			throws SAXException
	{
		if(qName.equals("UUID")) //$NON-NLS-1$
		{
			this.insideUUID = false;
		}
		if(qName.equals("OPTIONS")) //$NON-NLS-1$
		{
			this.insideOps = false;
			if(this.model!=null && this.currentOps!=null)
			{
				String[]  s = new String[this.currentOps.size()];
				this.model.setSelectedOps(this.currentOps.toArray(s));
			}
		}
		if(qName.equals("OP")) //$NON-NLS-1$
		{
			this.insideOp = false;
		}

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String arg0) throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(Locator arg0)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String arg0) throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String arg0, String arg1, String qName,
			Attributes arg3) throws SAXException
	{
		if(qName.equals("VOTE")) //$NON-NLS-1$
		{
			if(this.model==null)
				this.model = new VoteResponse();
		}
		if(qName.equals("UUID")) //$NON-NLS-1$
		{
			this.insideUUID = true;
		}
		if(qName.equals("OPTIONS")) //$NON-NLS-1$
		{
			this.insideOps = true;
			this.currentOps = new ArrayList<String>();
		}
		if(qName.equals("OP")) //$NON-NLS-1$
		{
			this.insideOp = true;
		}

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	public void setModel(VoteResponse model)
	{
		this.model = model;
	}

	public VoteResponse getModel()
	{
		return this.model;
	}

}
