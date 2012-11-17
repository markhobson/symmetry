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
package org.hobsoft.symmetry.demo.gallery.tab;

import org.hobsoft.symmetry.ui.BeanEditor;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.ToolBar;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.Wizard;
import org.hobsoft.symmetry.ui.model.DefaultTreeModel;
import org.hobsoft.symmetry.ui.model.DefaultTreeNode;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.model.TreeNode;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.view.LabelTreeNodeRenderer;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreeTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public TreeTab()
	{
		setText("Tree");
		setComponent(createTreeBox());
	}

	// private methods --------------------------------------------------------
	
	private static GroupBox createTreeBox()
	{
		GroupBox box = new GroupBox("Tree");
		
		box.add(new Label("A Tree can display data in hierarchical form:"));
		
		Tree tree = new Tree(createTreeModel());
		tree.setTreeNodeRenderer(createClassTreeNodeRenderer());
		tree.setAutoExpand(true);
		box.add(tree);
		
		return box;
	}
	
	private static TreeModel createTreeModel()
	{
		TreeNode rootNode = new DefaultTreeNode(Component.class,
			new DefaultTreeNode(Box.class,
				new DefaultTreeNode(Deck.class),
				new DefaultTreeNode(Grid.class,
					new DefaultTreeNode(BeanEditor.class)
				),
				new DefaultTreeNode(GroupBox.class),
				new DefaultTreeNode(HBox.class,
					new DefaultTreeNode(ToolBar.class)
				),
				new DefaultTreeNode(VBox.class,
					new DefaultTreeNode(ToggleButtonGroup.class),
					new DefaultTreeNode(Wizard.class)
				),
				new DefaultTreeNode(Window.class)
			),
			new DefaultTreeNode(ComboBox.class,
				new DefaultTreeNode(ListBox.class)
			),
			new DefaultTreeNode(FileChooser.class),
			new DefaultTreeNode(Label.class,
				new DefaultTreeNode(Button.class,
					new DefaultTreeNode(Tab.class),
					new DefaultTreeNode(ToggleButton.class,
						new DefaultTreeNode(CheckBox.class),
						new DefaultTreeNode(Radio.class)
					)
				)
			),
			new DefaultTreeNode(TabBox.class),
			new DefaultTreeNode(Table.class),
			new DefaultTreeNode(TextBox.class),
			new DefaultTreeNode(Tree.class)
		);
		
		return new DefaultTreeModel(rootNode);
	}
	
	private static TreeNodeRenderer createClassTreeNodeRenderer()
	{
		return new LabelTreeNodeRenderer()
		{
			@Override
			public Label getTreeNodeComponent(Tree tree, TreePath path)
			{
				Class<?> klass = (Class<?>) ((DefaultTreeNode) path.getNode()).getValue();
				setText(klass.getSimpleName());
				setToolTip(klass.getName());
				return this;
			}
		};
	}
}
