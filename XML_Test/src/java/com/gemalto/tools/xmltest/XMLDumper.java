package com.gemalto.tools.xmltest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.gemalto.tools.model.VoteModel;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * @author Hu Yao
 *
 */
public class XMLDumper
{
	/**
	 * @param model
	 * @return XML byte array
	 * @throws IOException
	 * @throws SAXException
	 */
	public static byte[] getXMLDataStream(VoteModel model)
		throws IOException, SAXException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);  //$NON-NLS-1$//$NON-NLS-2$
		of.setIndent(1);
		of.setIndenting(true);
		//of.setDoctype(null,"users.dtd"); //$NON-NLS-1$
		XMLSerializer serializer = new XMLSerializer(bos,of);
		// SAX2.0 ContentHandler.
		ContentHandler hd = serializer.asContentHandler();
		hd.startDocument();
		// Processing instruction sample.
		//hd.processingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"users.xsl\"");
		// USER attributes.
		AttributesImpl atts = new AttributesImpl();
		// USERS tag.
		hd.startElement("","","VOTE",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// USER tags.
		String title =model.getTitle();
		String disc = model.getDisc();
		String[] ops = model.getOptions();
		String uuid = model.getUid().toString();
		//UUID
		  atts.clear();
		  hd.startElement("","","UUID",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  hd.characters(uuid.toCharArray(),0,uuid.length());
		  hd.endElement("","","UUID"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Title
		  atts.clear();
		  hd.startElement("","","Title",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  hd.characters(title.toCharArray(),0,title.length());
		  hd.endElement("","","Title"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Disc
		  atts.clear();
		  hd.startElement("","","Disc",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  hd.characters(disc.toCharArray(),0,disc.length());
		  hd.endElement("","","Disc"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Options
		  atts.clear();
		  hd.startElement("","","Options",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  
		 if(ops!=null)
		 {
			 for(int i=0;i<ops.length;i++)
			 {
				 atts.clear();
				 hd.startElement("","","OP",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 hd.characters(ops[i].toCharArray(),0,ops[i].length());
				 hd.endElement("","","OP"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			 }
		 }
		 hd.endElement("","","Options"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		hd.endElement("","","VOTE"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		hd.endDocument();
		byte[] data = bos.toByteArray();
		bos.close();
		return data;
	}
	
	public static byte[] getVOTEResByteArray(UUID uuid,String[] ops) 
		throws SAXException, IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);  //$NON-NLS-1$//$NON-NLS-2$
		of.setIndent(1);
		of.setIndenting(true);
		//of.setDoctype(null,"users.dtd"); //$NON-NLS-1$
		XMLSerializer serializer = new XMLSerializer(bos,of);
		// SAX2.0 ContentHandler.
		ContentHandler hd = serializer.asContentHandler();
		hd.startDocument();
		// Processing instruction sample.
		//hd.processingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"users.xsl\"");
		// USER attributes.
		AttributesImpl atts = new AttributesImpl();
		hd.startElement("","","RESULT",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			atts.clear();
			hd.startElement("","","UUID",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			hd.characters(uuid.toString().toCharArray(),0,uuid.toString().length());
			hd.endElement("","","UUID"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			atts.clear();
			hd.startElement("","","OPTIONS",atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (ops != null)
			{
				int len = ops.length;
				for (int i = 0; i < len; i++)
				{
					atts.clear();
					hd.startElement("", "", "OP", atts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					hd.characters(ops[i].toCharArray(), 0, ops[i].toString()
							.length());
					hd.endElement("", "", "OP"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
			//hd.characters(uuid.toString().toCharArray(),0,uuid.toString().length());
			hd.endElement("","","OPTIONS"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		hd.endElement("","","RESULT");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		hd.endDocument();
		byte[] data = bos.toByteArray();
		bos.close();
		return data;
	}
}
