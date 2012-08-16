/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTreeHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrators;
import uk.co.iizuka.kozo.ui.common.hydrate.CompositeComponentHydrator;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.model.DefaultTreeModel;
import uk.co.iizuka.kozo.ui.model.DefaultTreeNode;
import uk.co.iizuka.kozo.ui.model.TreeModel;
import uk.co.iizuka.kozo.ui.test.DummyComponent;
import uk.co.iizuka.kozo.ui.test.view.DummyComponentTreeNodeRenderer;
import uk.co.iizuka.kozo.ui.test.view.NullComponentTreeNodeRenderer;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlTreeHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTreeHydratorTest.java 98871 2012-02-29 11:44:00Z mark@IIZUKA.CO.UK $
 * @see HtmlTreeHydrator
 */
public class HtmlTreeHydratorTest extends AbstractXmlRenderKitTest<Tree>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Tree> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		hydrator.setDelegate(Tree.class, new HtmlTreeHydrator<Tree>());
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(Tree.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrate() throws HydrationException
	{
		Tree tree = new Tree();
		
		assertDehydrate("<ul class=\"tree\"></ul>", tree);
	}
	
	@Test
	public void dehydrateWithNode() throws HydrationException
	{
		TreeModel model = new DefaultTreeModel(new DefaultTreeNode("a"));
		Tree tree = createTree(model);
		
		assertDehydrate("<ul class=\"tree\"><li><a href=\"state\">[dummy][/dummy]</a></li></ul>", tree);
	}
	
	@Test
	public void dehydrateWithNullComponentRenderer() throws HydrationException
	{
		TreeModel model = new DefaultTreeModel(new DefaultTreeNode("a"));
		Tree tree = createTree(model);
		tree.setTreeNodeRenderer(new NullComponentTreeNodeRenderer());
		
		assertDehydrate("<ul class=\"tree\"><li><a href=\"state\"></a></li></ul>", tree);
	}
	
	// private methods --------------------------------------------------------
	
	private static Tree createTree(TreeModel model)
	{
		Tree tree = new Tree(model);
		tree.setTreeNodeRenderer(new DummyComponentTreeNodeRenderer());
		
		return tree;
	}
}
