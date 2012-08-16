/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xml-test-support/src/main/java/uk/co/iizuka/kozo/ui/xml/test/hydrate/StubXmlComponentHydrators.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xml.test.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asContainerVisitor;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Container;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.traversal.ContainerVisitor;
import uk.co.iizuka.kozo.ui.traversal.TableVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubXmlComponentHydrators.java 99143 2012-03-09 16:50:29Z mark@IIZUKA.CO.UK $
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
