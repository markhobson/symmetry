/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/sax/ResourceEntityResolver.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.sax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ResourceEntityResolver.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class ResourceEntityResolver implements EntityResolver
{
	// fields -----------------------------------------------------------------
	
	private final EntityResolver parent;
	
	private final Map<String, String> entities;
	
	// constructors -----------------------------------------------------------
	
	public ResourceEntityResolver()
	{
		this(null);
	}
	
	public ResourceEntityResolver(EntityResolver parent)
	{
		this.parent = parent;
		
		entities = new HashMap<String, String>();
	}
	
	// EntityResolver methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
	{
		if (publicId != null)
		{
			String path = getEntityPath(publicId);
			
			if (path != null)
			{
				return new InputSource(getClass().getResourceAsStream(path));
			}
		}
		
		if (parent != null)
		{
			return parent.resolveEntity(publicId, systemId);
		}
		
		return null;
	}
	
	// public methods ---------------------------------------------------------
	
	public String getEntityPath(String publicId)
	{
		return entities.get(publicId);
	}
	
	public void setEntityPath(String publicId, String path)
	{
		entities.put(publicId, path);
	}
}
