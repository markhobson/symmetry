/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/TreeTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import uk.co.iizuka.kozo.ui.BeanEditor;
import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Deck;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.Grid;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.HBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.TabBox;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;
import uk.co.iizuka.kozo.ui.ToolBar;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.VBox;
import uk.co.iizuka.kozo.ui.Window;
import uk.co.iizuka.kozo.ui.Wizard;
import uk.co.iizuka.kozo.ui.model.DefaultTreeNode;
import uk.co.iizuka.kozo.ui.model.TreeNode;
import uk.co.iizuka.kozo.ui.model.TreePath;
import uk.co.iizuka.kozo.ui.view.LabelTreeNodeRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TreeTab.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public class TreeTab extends Tab
{
	// types ------------------------------------------------------------------
	
	private static class ClassTreeNodeRenderer extends LabelTreeNodeRenderer
	{
		// TreeNodeRenderer methods -----------------------------------------------

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Label getTreeNodeComponent(Tree tree, TreePath path)
		{
			Class<?> klass = (Class<?>) ((DefaultTreeNode) path.getNode()).getValue();
			String name = klass.getName();
			int dot = name.lastIndexOf('.');
			String className = (dot == -1) ? name : name.substring(dot + 1);
			String packageName = (dot == -1) ? "default package" : name.substring(0, dot);
			String type = klass.isInterface() ? "interface" : "class";
			
			setText(className);
			setToolTip(type + " in " + packageName);
			return this;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public TreeTab()
	{
		Tree tree = new Tree(createTreeRoot());
		tree.setTreeNodeRenderer(new ClassTreeNodeRenderer());
		tree.setAutoExpand(true);
		// TODO: remove this when TreePath serialization implemented properly
		// TODO: reimplement when API stabilised
//		SerializerManager.registerSerializer(TreePath.class, new TreePathSerializer(tree));
		
		Box box = new VBox(
			new GroupBox("Tree",
				new Label("A Tree can display data in hierarchical form:"),
				tree
			)
		);
		
		setText("Tree");
		setComponent(box);
	}
	
	// private methods --------------------------------------------------------
	
	private static TreeNode createTreeRoot()
	{
		// TODO: dynamically generate this tree
		return new DefaultTreeNode(Component.class,
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
			new DefaultTreeNode(Tree.class));
	}
}
