/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/xml/src/main/java/uk/co/iizuka/kozo/xml/XmlRenderKit.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.xml;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.AbstractRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the base component type this kit can render
 */
public abstract class XmlRenderKit<T> extends AbstractRenderKit<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "text/xml";
	
	// fields -----------------------------------------------------------------
	
	private final XMLOutputFactory xmlOutputFactory;
	
	// constructors -----------------------------------------------------------
	
	public XmlRenderKit(Class<T> componentType)
	{
		this(componentType, DEFAULT_CONTENT_TYPE);
	}
	
	public XmlRenderKit(Class<T> componentType, String contentType)
	{
		super(componentType, contentType);
		
		xmlOutputFactory = XMLOutputFactory.newInstance();
	}
	
	// AbstractRenderKit methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preDehydrate(T component, DehydrationContext context) throws HydrationException
	{
		super.preDehydrate(component, context);
		
		XMLStreamWriter out = createXmlStreamWriter(context.getOutputStream());
		context.set(XMLStreamWriter.class, out);
		
		context.set(IdEncoder.class, new DefaultIdEncoder());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void postDehydrate(T component, DehydrationContext context) throws HydrationException
	{
		super.postDehydrate(component, context);
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.close();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException("Cannot close XMLStreamWriter", exception);
		}
	}
	
	// private methods --------------------------------------------------------
	
	private XMLStreamWriter createXmlStreamWriter(OutputStream out) throws HydrationException
	{
		String encoding = getCharset();
		
		XMLStreamWriter xmlOut;
		
		try
		{
			if (encoding == null)
			{
				xmlOut = xmlOutputFactory.createXMLStreamWriter(out);
			}
			else
			{
				xmlOut = xmlOutputFactory.createXMLStreamWriter(out, encoding);
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException("Cannot create XMLStreamWriter", exception);
		}
		
		return xmlOut;
	}
}
