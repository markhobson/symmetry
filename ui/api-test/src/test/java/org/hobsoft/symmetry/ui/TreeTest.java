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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.model.DefaultTreeModel;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code Tree}.
 * 
 * @author Mark Hobson
 * @see Tree
 */
public class TreeTest extends AbstractComponentTest<Tree>
{
	// fields -----------------------------------------------------------------
	
	private Tree tree;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		tree = createComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void newTree()
	{
		assertTreeModelEmpty(tree.getModel());
		assertEquals("selectionMode", SelectionMode.SINGLE, tree.getSelectionMode());
		assertFalse("autoExpand", tree.isAutoExpand());
		assertFalse("autoCollapse", tree.isAutoCollapse());
	}
	
	@Test
	public void newTreeWithNullModel()
	{
		tree = new Tree((TreeModel) null);
		
		assertTreeModelEmpty(tree.getModel());
		assertEquals("selectionMode", SelectionMode.SINGLE, tree.getSelectionMode());
		assertFalse("autoExpand", tree.isAutoExpand());
		assertFalse("autoCollapse", tree.isAutoCollapse());
	}
	
	@Test
	public void setModel()
	{
		TreeModel model = new DefaultTreeModel();
		
		tree.setModel(model);
		
		assertSame(model, tree.getModel());
	}
	
	@Test
	public void setModelWithNull()
	{
		tree.setModel(null);
	
		assertTreeModelEmpty(tree.getModel());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Tree createComponent()
	{
		return new Tree();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<Tree> getComponentType()
	{
		return TypeToken.of(Tree.class);
	}
	
	// private methods --------------------------------------------------------
	
	// TODO: replace with assertEquals when DefaultTreeModel.equals implemented
	private static void assertTreeModelEmpty(TreeModel actual)
	{
		assertNotNull("Model was null", actual);
		assertNull("Root expected null", actual.getRoot());
	}
}
