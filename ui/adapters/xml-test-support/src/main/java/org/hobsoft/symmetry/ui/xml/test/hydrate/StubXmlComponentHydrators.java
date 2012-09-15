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

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.ui.traversal.TableVisitor;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asContainerVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class StubXmlComponentHydrators
{
	// constructors -----------------------------------------------------------

	private StubXmlComponentHydrators()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------

	public static <T extends Component> HierarchicalComponentHydrator<T> stubXmlHierarchicalComponentHydrator(
		@SuppressWarnings("unused") Class<T> componentType, String text)
	{
		return stubXmlHierarchicalComponentHydrator(text);
	}
	
	public static <T extends Component> HierarchicalComponentHydrator<T> stubXmlHierarchicalComponentHydrator(
		String text)
	{
		return new StubXmlHierarchicalComponentHydrator<T>(text);
	}
	
	public static <T extends Container> ContainerVisitor<T, HydrationContext, HydrationException>
		stubXmlContainerHydrator(@SuppressWarnings("unused") Class<T> componentType, String text)
	{
		return stubXmlContainerHydrator(text);
	}

	public static <T extends Container> ContainerVisitor<T, HydrationContext, HydrationException>
		stubXmlContainerHydrator(String text)
	{
		return asContainerVisitor(StubXmlComponentHydrators.<T>stubXmlHierarchicalComponentHydrator(text));
	}
	
	public static <T extends Table> TableVisitor<T, HydrationContext, HydrationException> stubXmlTableHydrator(
		String text)
	{
		return stubXmlTableHydrator(text, true);
	}
	
	public static <T extends Table> TableVisitor<T, HydrationContext, HydrationException> stubXmlTableHydrator(
		String text, boolean stubCells)
	{
		return new StubXmlTableHydrator<T>(text, stubCells);
	}
}
