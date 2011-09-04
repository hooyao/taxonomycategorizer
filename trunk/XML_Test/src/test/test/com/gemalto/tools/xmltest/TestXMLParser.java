package test.com.gemalto.tools.xmltest;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.gemalto.tools.model.VoteModel;
import com.gemalto.tools.model.VoteResponse;
import com.gemalto.tools.xmltest.XMLDumper;
import com.gemalto.tools.xmltest.XMLParser;

public class TestXMLParser
{
	@Test
	public void testXMLParser()
	{
		String[] ops = new String[]{"A","B","C"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		VoteModel model = new VoteModel("test vote disc", "test title", ops); //$NON-NLS-1$ //$NON-NLS-2$
		try
		{
			byte[] data = XMLDumper.getXMLDataStream(model);
			String xml = new String(data);
			System.out.println(xml);
			VoteModel vm = XMLParser.parseByteArray(data);
			System.out.println(vm);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResXMLParser()
	{
		String[] ops = new String[]{"A","B"}; //$NON-NLS-1$ //$NON-NLS-2$
		try
		{
			byte[] data = XMLDumper.getVOTEResByteArray(UUID.randomUUID(),ops);
			String xml = new String(data);
			System.out.println(xml);
			VoteResponse vm = XMLParser.parseResponseArray(data);
			System.out.println(vm);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
