/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xml-test-support/src/main/java/uk/co/iizuka/kozo/ui/xml/test/hydrate/StubXmlComponentHydrators.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
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
