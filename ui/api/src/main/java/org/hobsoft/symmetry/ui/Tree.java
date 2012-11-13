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

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hobsoft.symmetry.ui.event.AbstractClosureEventListener;
import org.hobsoft.symmetry.ui.event.TreeEvent;
import org.hobsoft.symmetry.ui.event.TreeExpansionListener;
import org.hobsoft.symmetry.ui.event.TreeSelectionListener;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.model.DefaultTreeModel;
import org.hobsoft.symmetry.ui.model.TreeModel;
import org.hobsoft.symmetry.ui.model.TreeNode;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import org.hobsoft.symmetry.ui.view.LabelTreeNodeRenderer;
import org.hobsoft.symmetry.ui.view.TreeNodeRenderer;

import com.google.common.reflect.TypeToken;

import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.asTreeVisitor;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.ui.traversal.Visits.nullEndVisit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Tree extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String TREE_EXPANSION_EVENT = "treeExpansion";
	
	public static final String TREE_SELECTION_EVENT = "treeSelection";
	
	public static final String TREE_EXPANSION_LISTENER_PROPERTY = "treeExpansionListener";
	
	public static final String TREE_SELECTION_LISTENER_PROPERTY = "treeSelectionListener";
	
	public static final String MODEL_PROPERTY = "model";
	
	public static final String SELECTION_MODE_PROPERTY = "selectionMode";
	
	public static final String TREE_NODE_RENDERER_PROPERTY = "treeNodeRenderer";
	
	public static final String EXPANDED_PATHS_PROPERTY = "expandedPaths";
	
	public static final String SELECTED_PATHS_PROPERTY = "selectedPaths";
	
	public static final String AUTO_EXPAND_PROPERTY = "autoExpand";
	
	public static final String AUTO_COLLAPSE_PROPERTY = "autoCollapse";
	
	private static final TreePath[] EMPTY_PATHS = new TreePath[0];
	
	// fields -----------------------------------------------------------------
	
	private TreeModel model;
	
	private TreeNodeRenderer renderer;
	
	private SelectionMode selectionMode;
	
	private Set<TreePath> expandedPaths;
	
	private Set<TreePath> selectedPaths;
	
	private boolean autoExpand;
	
	private boolean autoCollapse;

	// types ------------------------------------------------------------------
	
	/**
	 * A tree expansion listener that executes a closure when expanded or collapsed.
	 */
	private static class ClosureTreeExpansionListener extends AbstractClosureEventListener<TreeEvent>
		implements TreeExpansionListener
	{
		// fields -----------------------------------------------------------------
		
		private final boolean onExpand;
		
		private final boolean onCollapse;

		// constructors -----------------------------------------------------------
		
		public ClosureTreeExpansionListener(Closure<? super TreeEvent> closure, boolean onExpand, boolean onCollapse)
		{
			super(closure);
			
			this.onCollapse = onCollapse;
			this.onExpand = onExpand;
		}
		
		// TreeExpansionListener methods ------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeExpanded(TreeEvent event)
		{
			if (onExpand)
			{
				getClosure().apply(event);
			}
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeCollapsed(TreeEvent event)
		{
			if (onCollapse)
			{
				getClosure().apply(event);
			}
		}
	}
	
	/**
	 * A tree selection listener that executes a closure when invoked.
	 */
	private static class ClosureTreeSelectionListener extends AbstractClosureEventListener<TreeEvent>
		implements TreeSelectionListener
	{
		// constructors -----------------------------------------------------------
		
		public ClosureTreeSelectionListener(Closure<? super TreeEvent> closure)
		{
			super(closure);
		}
		
		// TreeSelectionListener methods ------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeSelected(TreeEvent event)
		{
			getClosure().apply(event);
		}
	}
	
	private class TreeTreeExpansionListener implements TreeExpansionListener
	{
		// TreeExpansionListener methods ------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeExpanded(TreeEvent event)
		{
			setPathExpanded(event.getPath(), true);
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeCollapsed(TreeEvent event)
		{
			setPathExpanded(event.getPath(), false);
		}
	}
	
	private class TreeTreeSelectionListener implements TreeSelectionListener
	{
		// TreeSelectionListener methods ------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void treeSelected(TreeEvent event)
		{
			TreePath path = event.getPath();
			
			if (selectionMode == SelectionMode.SINGLE)
			{
				setSelectedPaths(EMPTY_PATHS);
				
				if (isAutoCollapse())
				{
					setExpandedPaths(EMPTY_PATHS);
				}
			}
			
			setPathSelected(path, true);
			
			if (isAutoExpand())
			{
				// TODO: should only expand if not leaf, but hits the db..
				setPathsExpanded(path.getAncestorsAndSelf(), true);
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public Tree()
	{
		this(new DefaultTreeModel());
	}
	
	public Tree(TreeNode root)
	{
		this(new DefaultTreeModel(root));
	}
	
	public Tree(TreeModel model)
	{
		expandedPaths = new LinkedHashSet<TreePath>();
		selectedPaths = new LinkedHashSet<TreePath>();
		
		setModel(model);
		setTreeNodeRenderer(new LabelTreeNodeRenderer());
		setSelectionMode(SelectionMode.SINGLE);
		setAutoExpand(false);
		setAutoCollapse(false);
		addTreeExpansionListener(new TreeTreeExpansionListener());
		addTreeSelectionListener(new TreeTreeSelectionListener());
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptTree(visitor, Tree.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addTreeExpansionListener(TreeExpansionListener listener)
	{
		addListener(TreeExpansionListener.class, listener);
		firePropertyChange(TREE_EXPANSION_LISTENER_PROPERTY, null, listener);
	}
	
	public void addTreeSelectionListener(TreeSelectionListener listener)
	{
		addListener(TreeSelectionListener.class, listener);
		firePropertyChange(TREE_SELECTION_LISTENER_PROPERTY, null, listener);
	}
	
	public void removeTreeExpansionListener(TreeExpansionListener listener)
	{
		removeListener(TreeExpansionListener.class, listener);
		firePropertyChange(TREE_EXPANSION_LISTENER_PROPERTY, listener, null);
	}
	
	public void removeTreeSelectionListener(TreeSelectionListener listener)
	{
		removeListener(TreeSelectionListener.class, listener);
		firePropertyChange(TREE_SELECTION_LISTENER_PROPERTY, listener, null);
	}
	
	public TreeExpansionListener[] getTreeExpansionListeners()
	{
		return getListeners(TreeExpansionListener.class);
	}
	
	public TreeSelectionListener[] getTreeSelectionListeners()
	{
		return getListeners(TreeSelectionListener.class);
	}
	
	public int getTreeExpansionListenerCount()
	{
		return getListenerCount(TreeExpansionListener.class);
	}
	
	public int getTreeSelectionListenerCount()
	{
		return getListenerCount(TreeSelectionListener.class);
	}
	
	/**
	 * Adds the specified closure to those that will be executed when this tree is collapsed.
	 * 
	 * @param closure
	 *            the closure that will be executed
	 * @return this tree
	 */
	public Tree onCollapse(Closure<? super TreeEvent> closure)
	{
		addTreeExpansionListener(new ClosureTreeExpansionListener(closure, false, true));
		
		return this;
	}
	
	/**
	 * Adds the specified closure to those that will be executed when this tree is expanded.
	 * 
	 * @param closure
	 *            the closure that will be executed
	 * @return this tree
	 */
	public Tree onExpand(Closure<? super TreeEvent> closure)
	{
		addTreeExpansionListener(new ClosureTreeExpansionListener(closure, true, false));
		
		return this;
	}
	
	/**
	 * Adds the specified closure to those that will be executed when this tree is selected.
	 * 
	 * @param closure
	 *            the closure that will be executed
	 * @return this tree
	 */
	public Tree onSelect(Closure<? super TreeEvent> closure)
	{
		addTreeSelectionListener(new ClosureTreeSelectionListener(closure));
		
		return this;
	}
	
	public TreeModel getModel()
	{
		return model;
	}
	
	public void setModel(TreeModel model)
	{
		if (model == null)
		{
			model = new DefaultTreeModel();
		}
		
		TreeModel oldModel = this.model;
		this.model = model;
		firePropertyChange(MODEL_PROPERTY, oldModel, model);
	}
	
	public TreeNodeRenderer getTreeNodeRenderer()
	{
		return renderer;
	}
	
	public void setTreeNodeRenderer(TreeNodeRenderer renderer)
	{
		TreeNodeRenderer oldRenderer = this.renderer;
		this.renderer = renderer;
		firePropertyChange(TREE_NODE_RENDERER_PROPERTY, oldRenderer, renderer);
	}
	
	public boolean isPathExpanded(TreePath path)
	{
		checkNotNull(path, "path cannot be null");
		
		return expandedPaths.contains(path);
	}
	
	public void setPathExpanded(TreePath path, boolean expanded)
	{
		checkNotNull(path, "path cannot be null");
		
		TreePath[] oldPaths = getExpandedPaths();
		
		if (expanded)
		{
			expandedPaths.add(path);
		}
		else
		{
			expandedPaths.remove(path);
		}
		
		firePropertyChange(EXPANDED_PATHS_PROPERTY, oldPaths, getExpandedPaths());
	}
	
	public void setPathsExpanded(TreePath[] paths, boolean expanded)
	{
		checkNotNull(paths, "paths cannot be null");
		
		TreePath[] oldPaths = getExpandedPaths();
		List<TreePath> pathList = Arrays.asList(paths);
		
		if (expanded)
		{
			expandedPaths.addAll(pathList);
		}
		else
		{
			expandedPaths.removeAll(pathList);
		}
		
		firePropertyChange(EXPANDED_PATHS_PROPERTY, oldPaths, getExpandedPaths());
	}
	
	public TreePath[] getExpandedPaths()
	{
		return expandedPaths.toArray(new TreePath[expandedPaths.size()]);
	}
	
	public void setExpandedPaths(TreePath[] paths)
	{
		checkNotNull(paths, "paths cannot be null");
		
		TreePath[] oldPaths = getExpandedPaths();
		expandedPaths.clear();
		expandedPaths.addAll(Arrays.asList(paths));
		firePropertyChange(EXPANDED_PATHS_PROPERTY, oldPaths, paths);
	}
	
	public SelectionMode getSelectionMode()
	{
		return selectionMode;
	}
	
	public void setSelectionMode(SelectionMode selectionMode)
	{
		checkNotNull(selectionMode, "selectionMode cannot be null");
		
		SelectionMode oldSelectionMode = this.selectionMode;
		this.selectionMode = selectionMode;
		firePropertyChange(SELECTION_MODE_PROPERTY, oldSelectionMode, selectionMode);
	}
	
	public boolean isPathSelected(TreePath path)
	{
		checkNotNull(path, "path cannot be null");
		
		return selectedPaths.contains(path);
	}
	
	public void setPathSelected(TreePath path, boolean selected)
	{
		checkNotNull(path, "path cannot be null");
		
		TreePath[] oldPaths = getSelectedPaths();
		
		if (selected)
		{
			selectedPaths.add(path);
		}
		else
		{
			selectedPaths.remove(path);
		}
		
		firePropertyChange(SELECTED_PATHS_PROPERTY, oldPaths, getSelectedPaths());
	}
	
	public void setPathsSelected(TreePath[] paths, boolean selected)
	{
		checkNotNull(paths, "paths cannot be null");
		
		TreePath[] oldPaths = getSelectedPaths();
		List<TreePath> pathList = Arrays.asList(paths);
		
		if (selected)
		{
			selectedPaths.addAll(pathList);
		}
		else
		{
			selectedPaths.removeAll(pathList);
		}
		
		firePropertyChange(SELECTED_PATHS_PROPERTY, oldPaths, getSelectedPaths());
	}
	
	public TreePath[] getSelectedPaths()
	{
		return selectedPaths.toArray(new TreePath[selectedPaths.size()]);
	}
	
	public void setSelectedPaths(TreePath[] paths)
	{
		checkNotNull(paths, "paths cannot be null");
		
		TreePath[] oldPaths = getSelectedPaths();
		selectedPaths.clear();
		selectedPaths.addAll(Arrays.asList(paths));
		firePropertyChange(SELECTED_PATHS_PROPERTY, oldPaths, paths);
	}
	
	public boolean isAutoExpand()
	{
		return autoExpand;
	}
	
	public void setAutoExpand(boolean autoExpand)
	{
		boolean oldAutoExpand = this.autoExpand;
		this.autoExpand = autoExpand;
		firePropertyChange(AUTO_EXPAND_PROPERTY, oldAutoExpand, autoExpand);
	}
	
	public boolean isAutoCollapse()
	{
		return autoCollapse;
	}
	
	public void setAutoCollapse(boolean autoCollapse)
	{
		boolean oldAutoCollapse = this.autoCollapse;
		this.autoCollapse = autoCollapse;
		firePropertyChange(AUTO_COLLAPSE_PROPERTY, oldAutoCollapse, autoCollapse);
	}
	
	public int getRowForPath(TreePath path)
	{
		// TODO: need to cache this mapping for speed
		
		TreePath parentPath = path.getParentPath();
		
		if (parentPath == null)
		{
			return 0;
		}
		
		Object parent = parentPath.getNode();
		Object node = path.getNode();
		int index = model.getChildIndex(parent, node);
		if (index == 0)
		{
			return getRowForPath(parentPath) + 1;
		}
		
		Object sibling = model.getChild(parent, index - 1);
		TreePath siblingPath = new TreePath(parentPath, sibling);
		return getRowForPath(siblingPath) + getDeepChildCount(sibling) + 1;
	}
	
	public TreePath getPathForRow(int row)
	{
		// TODO: need to cache this mapping for speed
		
		return getPathForRow(new TreePath(model.getRoot()), 0, row);
	}
	
	public void doClick()
	{
		doClick(new TreePath(getModel().getRoot()));
	}
	
	public void doClick(TreePath path)
	{
		fireTreeSelectionEvent(path);
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Tree, P, E extends Exception> EndVisit acceptTree(ComponentVisitor<P, E> visitor,
		Class<T> treeType, T tree, P parameter) throws E
	{
		return acceptTree(visitor, TypeToken.of(treeType), tree, parameter);
	}
	
	protected static <T extends Tree, P, E extends Exception> EndVisit acceptTree(ComponentVisitor<P, E> visitor,
		TypeToken<T> treeType, T tree, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(treeType, tree, parameter);
		
		if (subvisitor == null || subvisitor.visit(tree, parameter) != SKIP_CHILDREN)
		{
			Object root = tree.getModel().getRoot();
			
			if (root != null)
			{
				TreePath path = new TreePath(root);

				acceptNode(visitor, subvisitor, tree, parameter, path);
			}
		}
		
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(tree, parameter));
	}
	
	protected void fireTreeSelectionEvent(TreePath path)
	{
		Object[] listenerArray = getListeners();
		TreeEvent event = null;
		for (int i = 0; i < listenerArray.length; i += 2)
		{
			if (listenerArray[i] == TreeSelectionListener.class)
			{
				TreeSelectionListener listener = (TreeSelectionListener) listenerArray[i + 1];
				if (event == null)
				{
					event = new TreeEvent(this, path);
				}
				
				listener.treeSelected(event);
			}
		}
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends Tree, P, E extends Exception> EndVisit acceptNode(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T tree, P parameter, TreePath path) throws E
	{
		if (asTreeVisitor(subvisitor).visitNode(tree, parameter, path) != SKIP_CHILDREN)
		{
			Component nodeComponent = tree.getTreeNodeRenderer().getTreeNodeComponent(tree, path);
			
			if (nodeComponent != null)
			{
				// TODO: should we use the return value?
				nodeComponent.accept(visitor, parameter);
			}

			Object node = path.getNode();
			int childCount = tree.getModel().getChildCount(node);
			
			if (childCount > 0)
			{
				acceptNodeChildren(visitor, subvisitor, tree, parameter, path);
			}
		}
		
		return nullEndVisit(asTreeVisitor(subvisitor).endVisitNode(tree, parameter, path));
	}
	
	private static <T extends Tree, P, E extends Exception> EndVisit acceptNodeChildren(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T tree, P parameter, TreePath path) throws E
	{
		if (asTreeVisitor(subvisitor).visitNodeChildren(tree, parameter, path) != SKIP_CHILDREN)
		{
			Object node = path.getNode();
			int childCount = tree.getModel().getChildCount(node);
			
			for (int index = 0; index < childCount; index++)
			{
				Object child = tree.getModel().getChild(node, index);
				TreePath childPath = new TreePath(path, child);
				
				acceptNode(visitor, subvisitor, tree, parameter, childPath);
			}
		}
		
		return nullEndVisit(asTreeVisitor(subvisitor).endVisitNodeChildren(tree, parameter, path));
	}
	
	private int getDeepChildCount(Object node)
	{
		int n = model.getChildCount(node);
		int count = n;
		
		for (int i = 0; i < n; i++)
		{
			count += getDeepChildCount(model.getChild(node, i));
		}
		
		return count;
	}
	
	private TreePath getPathForRow(TreePath path, int current, int row)
	{
		if (current == row)
		{
			return path;
		}
		
		Object node = path.getNode();
		int n = model.getChildCount(node);
		boolean found = false;
		TreePath previousPath = null;
		int previousRow = 0;
		for (int i = 0; !found && i < n; i++)
		{
			Object child = model.getChild(node, i);
			TreePath childPath = new TreePath(path, child);
			int childRow = getRowForPath(childPath);
			
			if (childRow > row)
			{
				found = true;
			}
			else
			{
				previousPath = childPath;
				previousRow = childRow;
			}
		}
		return getPathForRow(previousPath, previousRow, row);
	}
}
