/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
