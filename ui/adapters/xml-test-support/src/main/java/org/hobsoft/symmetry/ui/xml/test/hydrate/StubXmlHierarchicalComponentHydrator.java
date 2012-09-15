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
package org.hobsoft.symmetry.ui.xml.test.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
class StubXmlHierarchicalComponentHydrator<T extends Component> extends NullHierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final String text;
	
	// constructors -----------------------------------------------------------
	
	public StubXmlHierarchicalComponentHydrator(String text)
	{
		this.text = text;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + "]", context);
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + "]", context);

		return VISIT_SIBLINGS;
	}

	// protected methods ------------------------------------------------------
	
	protected final String getText()
	{
		return text;
	}
	
	protected static void write(String text, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeCharacters(text);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
	}
}
