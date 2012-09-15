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
package org.hobsoft.symmetry.ui.xul.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class XulLabelDehydrator<T extends Label> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T label, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("label");

			// TODO: need to reimplement flex now it is provided by the parent box, if any
//			int flex = label.getFlex();
//			writeAttributeIf(out, "flex", flex, flex > 0);
			
			writeAttributeIfNotEmpty(out, "value", label.getText());
			writeAttributeIfNotEmpty(out, "tooltiptext", label.getToolTip());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
}
