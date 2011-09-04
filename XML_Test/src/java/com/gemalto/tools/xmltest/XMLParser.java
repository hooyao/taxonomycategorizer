package com.gemalto.tools.xmltest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.gemalto.tools.model.VoteModel;
import com.gemalto.tools.model.VoteResponse;

public class XMLParser
{
	public static VoteModel parseByteArray(byte[] input) throws IOException, SAXException
	{

		XMLReader parser = XMLReaderFactory.createXMLReader();
		VoteModelContentHandler contentHandler = new VoteModelContentHandler();
		VoteModel vm  = new VoteModel();
		contentHandler.setModel(vm);
		parser.setContentHandler(contentHandler);
		ByteArrayInputStream boi = new ByteArrayInputStream(input);
		InputSource source = new InputSource(boi);
		parser.parse(source);
		
		return contentHandler.getModel();
		
	}
	
	public static VoteResponse parseResponseArray(byte[] input) throws IOException, SAXException
	{
		XMLReader parser = XMLReaderFactory.createXMLReader();
		VoteResContentHandler contentHandler = new VoteResContentHandler();
		VoteResponse vm  = new VoteResponse();
		contentHandler.setModel(vm);
		parser.setContentHandler(contentHandler);
		ByteArrayInputStream boi = new ByteArrayInputStream(input);
		InputSource source = new InputSource(boi);
		parser.parse(source);
		
		return contentHandler.getModel();
	}
}
