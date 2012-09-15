/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTreeHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.model.DefaultTreeModel;
import org.hobsoft.symmetry.ui.model.DefaultTreeNode;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.test.view.DummyComponentTreeNodeRenderer;
import org.hobsoft.symmetry.ui.test.view.NullComponentTreeNodeRenderer;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

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
