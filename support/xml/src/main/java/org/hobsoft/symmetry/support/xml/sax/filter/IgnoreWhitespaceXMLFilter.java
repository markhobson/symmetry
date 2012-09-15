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
package org.hobsoft.symmetry.support.xml.sax.filter;

import org.hobsoft.symmetry.support.xml.dtd.DTDProvider;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class IgnoreWhitespaceXMLFilter extends AbstractWhitespaceXMLFilter
{
	// constructors -----------------------------------------------------------
	
	public IgnoreWhitespaceXMLFilter(XMLReader parent)
	{
		super(parent);
	}

	public IgnoreWhitespaceXMLFilter(XMLReader parent, DTDProvider dtdProvider)
	{
		super(parent, dtdProvider);
	}

	// AbstractWhitespaceXMLFilter methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processCharacterBuffer() throws SAXException
	{
		if (!isBufferWhitespace() || isWhitespaceSignificant())
		{
			super.processCharacterBuffer();
		}
	}
}
