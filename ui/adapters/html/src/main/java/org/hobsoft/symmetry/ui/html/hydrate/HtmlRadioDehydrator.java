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

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ComponentUtils;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodeComponent;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeFor;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeMnemonic;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeSelected;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

/**
 * Hydrator that dehydrates a {@code Radio} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @see Radio
 * @param <T>
 *            the component type this visitor can visit
 */
public class HtmlRadioDehydrator<T extends Radio> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T radio, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String toolTip = radio.getToolTip();
		
		Component groupComponent = ComponentUtils.getAncestor(radio, ToggleButtonGroup.class);
		
		if (groupComponent == null)
		{
			groupComponent = radio;
		}
		
		try
		{
			out.writeEmptyElement("input");
			writeClass(out, getRadioCssClass(radio));
			
			writeId(context, radio);
			writeName(context, groupComponent);
			out.writeAttribute("type", "radio");
			writeAttributeIfNotEmpty(out, "value", encodeComponent(context, radio));
			writeMnemonic(out, radio.getMnemonic());
			writeToolTip(out, toolTip);
			writeEnabled(out, radio);
			writeSelected(out, radio);
			
			String text = radio.getText();
			
			if (text.length() > 0)
			{
				out.writeStartElement("label");
				writeClass(out, getRadioCssClass(radio));
				
				writeFor(context, radio);
				writeToolTip(out, toolTip);
				writeText(out, text, radio.getMnemonic());
				
				// label
				out.writeEndElement();
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getRadioCssClass(T radio)
	{
		CssClassBuilder builder = new CssClassBuilder("radio");
		
		if (!radio.isEnabled())
		{
			builder.append("radio-disabled");
		}
		
		return builder;
	}
}
