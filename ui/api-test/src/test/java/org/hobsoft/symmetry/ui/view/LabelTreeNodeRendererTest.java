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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@code LabelTreeNodeRenderer}.
 * 
 * @author Mark Hobson
 * @see LabelTreeNodeRenderer
 */
public class LabelTreeNodeRendererTest
{
	// fields -----------------------------------------------------------------
	
	private LabelTreeNodeRenderer renderer;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		renderer = new LabelTreeNodeRenderer();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getTreeNodeComponentWithNode()
	{
		assertLabelText("x", renderer.getTreeNodeComponent(new Tree(), new TreePath("x")));
	}
	
	@Test
	public void getTreeNodeComponentWithNullNode()
	{
		assertLabelText("", renderer.getTreeNodeComponent(new Tree(), new TreePath(null)));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTreeNodeComponentWithNullTree()
	{
		renderer.getTreeNodeComponent(null, new TreePath("x"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getTreeNodeComponentWithNullPath()
	{
		renderer.getTreeNodeComponent(new Tree(), null);
	}
	
	// private methods --------------------------------------------------------
	
	private static void assertLabelText(String expected, Label actualLabel)
	{
		assertNotNull("label", actualLabel);
		assertEquals("text", expected, actualLabel.getText());
	}
}
