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
import org.hobsoft.symmetry.ui.Button;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeImage;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <button/>} tag.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class ButtonHtmlButtonDehydrator<T extends Button> extends AbstractControlHtmlButtonDehydrator<T>
{
	// AbstractControlHtmlButtonDehydrator methods ----------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonStart(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);

		out.writeStartElement("button");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonImage(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		writeImage(out, button.getImage());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonText(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		writeText(out, button.getText(), button.getMnemonic());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonEnd(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);

		// button
		out.writeEndElement();
	}
}
