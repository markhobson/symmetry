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
