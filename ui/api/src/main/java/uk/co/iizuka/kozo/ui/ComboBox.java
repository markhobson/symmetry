/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/ComboBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.asListBoxVisitor;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.SKIP_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.Visits.nullEndVisit;

import java.beans.EventSetDescriptor;
import java.util.Collection;
import java.util.Collections;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.common.bean.EventSets;
import uk.co.iizuka.kozo.ui.event.AbstractClosureEventListener;
import uk.co.iizuka.kozo.ui.event.SelectionEvent;
import uk.co.iizuka.kozo.ui.event.SelectionListener;
import uk.co.iizuka.kozo.ui.functor.Closure;
import uk.co.iizuka.kozo.ui.model.DefaultListModel;
import uk.co.iizuka.kozo.ui.model.ListModel;
import uk.co.iizuka.kozo.ui.model.ListModels;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import uk.co.iizuka.kozo.ui.view.LabelListCellRenderer;
import uk.co.iizuka.kozo.ui.view.ListCellRenderer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComboBox.java 99065 2012-03-08 13:00:14Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the item type
 */
public class ComboBox<T> extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String SELECTION_EVENT = "selection";
	
	public static final String MODEL_PROPERTY = "model";
	
	public static final String SELECTED_INDEX_PROPERTY = "selectedIndex";
	
	public static final String LIST_CELL_RENDERER_PROPERTY = "listCellRenderer";
	
	static final int UNSELECTED_INDEX = -1;
	
	private static final EventSetDescriptor SELECTION_EVENT_SET = EventSets.getDescriptor(ComboBox.class,
		SELECTION_EVENT);
	
	// types ------------------------------------------------------------------
	
	private static class ClosureSelectionListener extends AbstractClosureEventListener<SelectionEvent>
		implements SelectionListener
	{
		// constructors -----------------------------------------------------------
		
		public ClosureSelectionListener(Closure<? super SelectionEvent> closure)
		{
			super(closure);
		}
		
		// SelectionListener methods ------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void itemSelected(SelectionEvent event)
		{
			getClosure().apply(event);
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private ListModel<T> model;
	
	private int selectedIndex;
	
	private ListCellRenderer<T> renderer;
	
	// constructors -----------------------------------------------------------
	
	// provided to avoid generic varargs warning
	public ComboBox()
	{
		this((ListModel<T>) null);
	}
	
	// provided to avoid generic varargs warning
	public ComboBox(T item)
	{
		this(Collections.singleton(item));
	}
	
	public ComboBox(T... items)
	{
		this(new DefaultListModel<T>(items));
	}
	
	public ComboBox(Collection<? extends T> items)
	{
		this(new DefaultListModel<T>(items));
	}
	
	public ComboBox(ListModel<T> model)
	{
		if (model == null)
		{
			model = new DefaultListModel<T>();
		}
		
		this.model = model;
		
		selectedIndex = UNSELECTED_INDEX;
		renderer = new LabelListCellRenderer<T>();
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptComboBox(visitor, new Generic<ComboBox<?>>() { /**/ }, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addSelectionListener(SelectionListener listener)
	{
		addListener(SelectionListener.class, listener);
	}
	
	public void removeSelectionListener(SelectionListener listener)
	{
		removeListener(SelectionListener.class, listener);
	}
	
	public SelectionListener[] getSelectionListeners()
	{
		return getListeners(SelectionListener.class);
	}
	
	public int getSelectionListenerCount()
	{
		return getListenerCount(SelectionListener.class);
	}
	
	public ComboBox<T> onSelect(Closure<? super SelectionEvent> closure)
	{
		addSelectionListener(new ClosureSelectionListener(closure));
		
		return this;
	}
	
	public ListModel<T> getModel()
	{
		return model;
	}
	
	public void setModel(ListModel<T> model)
	{
		if (model == null)
		{
			model = new DefaultListModel<T>();
		}
		
		ListModel<T> oldModel = this.model;
		this.model = model;
		firePropertyChange(MODEL_PROPERTY, oldModel, model);
	}
	
	public int getItemIndex(T item)
	{
		return ListModels.getItemIndex(model, item);
	}
	
	public int getSelectedIndex()
	{
		return selectedIndex;
	}
	
	public void setSelectedIndex(int selectedIndex)
	{
		if (selectedIndex != UNSELECTED_INDEX)
		{
			checkElementIndex(selectedIndex, model.getItemCount(), "selectedIndex");
		}
		
		int oldSelectedIndex = this.selectedIndex;
		this.selectedIndex = selectedIndex;
		firePropertyChange(SELECTED_INDEX_PROPERTY, oldSelectedIndex, selectedIndex);

		if (selectedIndex != UNSELECTED_INDEX)
		{
			fireItemSelectedEvent(selectedIndex);
		}
	}
	
	public T getSelectedItem()
	{
		if (selectedIndex == UNSELECTED_INDEX)
		{
			return null;
		}
		
		return model.getItem(selectedIndex);
	}
	
	public void setSelectedItem(T selectedItem)
	{
		int selectedIndex = getItemIndex(selectedItem);
		
		checkArgument(selectedItem == null || selectedIndex != -1, "Unknown item: %s", selectedItem);
		
		setSelectedIndex(selectedIndex);
	}
	
	public ListCellRenderer<T> getListCellRenderer()
	{
		return renderer;
	}
	
	public void setListCellRenderer(ListCellRenderer<T> renderer)
	{
		ListCellRenderer<T> oldRenderer = getListCellRenderer();
		
		this.renderer = renderer;
		
		firePropertyChange(LIST_CELL_RENDERER_PROPERTY, oldRenderer, renderer);
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends ComboBox<?>, P, E extends Exception> EndVisit acceptComboBox(
		ComponentVisitor<P, E> visitor, Class<T> comboBoxType, T comboBox, P parameter) throws E
	{
		return accept(visitor, Generic.get(comboBoxType), comboBox, parameter);
	}
	
	protected static <T extends ComboBox<?>, P, E extends Exception> EndVisit acceptComboBox(
		ComponentVisitor<P, E> visitor, Generic<T> comboBoxType, T comboBox, P parameter) throws E
	{
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(comboBoxType, comboBox, parameter);
		
		if (subvisitor == null || subvisitor.visit(comboBox, parameter) != SKIP_CHILDREN)
		{
			int itemCount = comboBox.getModel().getItemCount();
			
			for (int index = 0; index < itemCount; index++)
			{
				if (acceptItem(visitor, subvisitor, comboBox, parameter, index) == SKIP_SIBLINGS)
				{
					break;
				}
			}
		}

		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(comboBox, parameter));
	}
	
	protected void fireItemSelectedEvent(int selectedIndex)
	{
		SelectionEvent event = new SelectionEvent(this, selectedIndex);
		
		fireEvent(SELECTION_EVENT_SET, event);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T extends ComboBox<?>, P, E extends Exception> EndVisit acceptItem(ComponentVisitor<P, E> visitor,
		HierarchicalComponentVisitor<T, P, E> subvisitor, T comboBox, P parameter, int index) throws E
	{
		if (asListBoxVisitor(subvisitor).visitItem(comboBox, parameter, index) != SKIP_CHILDREN)
		{
			Component itemComponent = getListCellComponent(comboBox, index);
			
			if (itemComponent != null)
			{
				// TODO: should we use the return value?
				itemComponent.accept(visitor, parameter);
			}
		}
		
		return asListBoxVisitor(subvisitor).endVisitItem(comboBox, parameter, index);
	}
	
	private static Component getListCellComponent(ComboBox<?> comboBox, int index)
	{
		return getListCellComponentCapture(comboBox, index);
	}
	
	private static <T> Component getListCellComponentCapture(ComboBox<T> comboBox, int index)
	{
		return comboBox.getListCellRenderer().getListCellComponent(comboBox, index);
	}
}
