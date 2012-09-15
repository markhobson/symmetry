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

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.html.state.HtmlEventStateCodec;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodeState;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeMnemonic;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Base hydrator for dehydrating a {@code Button} component to an HTML form control.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractControlHtmlButtonDehydrator<T extends Button> extends AbstractHtmlButtonDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T button, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			dehydrateButtonStart(button, context);
			writeId(context, button);
			writeClass(out, getButtonCssClass(button));
			writeName(context, button);
			out.writeAttribute("type", getButtonType(button));
			writeEnabled(out, button);
			
			if (button.isEnabled())
			{
				writeMnemonic(out, button.getMnemonic());
				writeToolTip(out, button.getToolTip());
				writeAttributeIfNotEmpty(out, "onclick", getOnClick(button, (DehydrationContext) context));
			}
			
			dehydrateButtonImage(button, context);
			dehydrateButtonText(button, context);

			dehydrateButtonEnd(button, context);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
				
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract void dehydrateButtonStart(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
	
	protected String getButtonType(T button)
	{
		return "button";
	}
	
	protected String getOnClick(T button, DehydrationContext context) throws HydrationException
	{
		if (button.getActionListenerCount() > 0)
		{
			DehydrationContext subcontext = new DehydrationContext(context);
			subcontext.getOrSet(HtmlEventStateCodec.Parameters.class, new HtmlEventStateCodec.Parameters())
				.setEvent(true);

			return encodeState(subcontext, Button.ACTION_EVENT, new ActionEvent(button));
		}
		
		return null;
	}
	
	protected abstract void dehydrateButtonImage(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;

	protected abstract void dehydrateButtonText(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
	
	protected abstract void dehydrateButtonEnd(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
}
