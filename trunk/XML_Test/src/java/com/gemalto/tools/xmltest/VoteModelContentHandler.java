package com.gemalto.tools.xmltest;

import java.util.ArrayList;
import java.util.UUID;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.gemalto.tools.model.VoteModel;

public class VoteModelContentHandler implements ContentHandler
{
	private VoteModel model;
	private ArrayList<String> currentOps;
	private boolean insideUUID= false;
	private boolean insideTitle = false;
	private boolean insideDics = false;
	private boolean insideOps = false;
	private boolean insideOp = false;

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException
	{
		String value = String.copyValueOf(arg0, arg1, arg2);
		//System.out.println(value);
		if(this.model!=null)
		{
			if(this.insideUUID)
			{
				this.model.setUid(UUID.fromString(value));
			}
			if(this.insideTitle)
			{
				this.model.setTitle(value);
			}
			if(this.insideDics)
			{
				this.model.setDisc(value);
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

	@Override
	public void endDocument() throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void endElement(String arg0, String arg1, String qName)
			throws SAXException
	{
		if(qName.equals("VOTE")) //$NON-NLS-1$
		{
			//TODO
		}
		if(qName.equals("UUID")) //$NON-NLS-1$
		{
			this.insideUUID = false;
		}
		if(qName.equals("Title")) //$NON-NLS-1$
		{
			this.insideTitle = false;
		}
		if(qName.equals("Disc")) //$NON-NLS-1$
		{
			this.insideDics = false;
			}
		if(qName.equals("Options")) //$NON-NLS-1$
		{
			this.insideOps = false;
			if(this.model!=null && this.currentOps!=null)
			{
				String[]  s = new String[this.currentOps.size()];
				this.model.setOptions(this.currentOps.toArray(s));
			}
		}
		if(qName.equals("OP")) //$NON-NLS-1$
		{
			this.insideOp = false;
		}
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String arg0) throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String arg0, String arg1, String qName,
			Attributes arg3) throws SAXException
	{
		// TODO Auto-generated method stub
		if(qName.equals("VOTE")) //$NON-NLS-1$
		{
			if(this.model==null)
				this.model = new VoteModel();
		}
		if(qName.equals("UUID")) //$NON-NLS-1$
		{
			this.insideUUID = true;
		}
		if(qName.equals("Title")) //$NON-NLS-1$
		{
			this.insideTitle = true;
		}
		if(qName.equals("Disc")) //$NON-NLS-1$
		{
			this.insideDics = true;
			}
		if(qName.equals("Options")) //$NON-NLS-1$
		{
			this.insideOps = true;
			this.currentOps = new ArrayList<String>();
		}
		if(qName.equals("OP")) //$NON-NLS-1$
		{
			this.insideOp = true;
		}
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException
	{
		// TODO Auto-generated method stub

	}

	public void setModel(VoteModel model)
	{
		this.model = model;
	}

	public VoteModel getModel()
	{
		return this.model;
	}

}
