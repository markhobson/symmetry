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
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.MultiLineLabel;

/**
 * Hydrator that dehydrates a {@code MultiLineLabel} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @see MultiLineLabel
 * @param <T>
 *            the multi-line label type this visitor can visit
 */
public class HtmlMultiLineLabelDehydrator<T extends MultiLineLabel> extends HtmlLabelDehydrator<T>
{
	// constants --------------------------------------------------------------
	
	private static final String NEWLINE_PATTERN = "\r\n|[\n\r]";

	// HtmlLabelDehydrator methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateLabelText(T label, HydrationContext context) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String text = label.getText();

		boolean first = true;
		
		for (String line : text.split(NEWLINE_PATTERN, -1))
		{
			if (!first)
			{
				out.writeEmptyElement("br");
			}
			
			out.writeCharacters(line);
			
			first = false;
		}
	}
}
