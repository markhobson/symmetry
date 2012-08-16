/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Deck.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import uk.co.iizuka.kozo.ui.functor.Closure;
import uk.co.iizuka.kozo.ui.functor.Command;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * A container that lays its children out on top of one another.
 * 
 * @author Mark Hobson
 * @version $Id: Deck.java 99112 2012-03-09 15:21:08Z mark@IIZUKA.CO.UK $
 */
public class Deck extends Box
{
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the deck's selected component.
	 */
	public static final String SELECTED_INDEX_PROPERTY = "selectedIndex";

	// TODO: remove, is this used?
	public static final String SELECTED_COMPONENT_PROPERTY = "selectedComponent";
	
	private static final int UNSELECTED_INDEX = -1;
	
	// fields -----------------------------------------------------------------
	
	private final PropertyChangeListener childListener = new PropertyChangeListener()
	{
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			int index = ((IndexedPropertyChangeEvent) event).getIndex();
			
			// automatically select first child when added
			if (event.getOldValue() == null)
			{
				if (!isDeselectable() && selectedIndex == UNSELECTED_INDEX)
				{
					setSelectedIndex(index);
				}
			}
			// automatically deselect selected component when removed
			else if (event.getNewValue() == null)
			{
				if (selectedIndex == index)
				{
					int newSelectedIndex = (isDeselectable() || getComponentCount() == 0) ? UNSELECTED_INDEX : 0;
					
					setSelectedIndex(newSelectedIndex);
				}
			}
		}
	};
	
	/**
	 * The index of the selected component in this deck.
	 */
	private int selectedIndex;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty deck.
	 */
	public Deck()
	{
		this(EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a deck with the specified children.
	 * 
	 * @param children
	 *            the components to be added to the deck
	 */
	public Deck(Component... children)
	{
		super(Orientation.HORIZONTAL);
		
		selectedIndex = UNSELECTED_INDEX;
		
		addPropertyChangeListener(COMPONENTS_PROPERTY, childListener);
		
		// not using super-constructor to add children since we need child listener to be registered
		add(children);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, Deck.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the index of the selected component in this deck.
	 * 
	 * @return the index of the selected component in this deck, or {@code -1} if this deck is empty
	 */
	public int getSelectedIndex()
	{
		return selectedIndex;
	}
	
	/**
	 * Gets the selected component in this deck.
	 * 
	 * @return the selected component in this deck, or {@code null} if this deck is empty
	 */
	public Component getSelectedComponent()
	{
		return (selectedIndex == UNSELECTED_INDEX) ? null : get(selectedIndex);
	}
	
	/**
	 * Sets the index of the selected component in this deck.
	 * 
	 * @param selectedIndex
	 *            the index of the selected component in this deck
	 * @throws IllegalArgumentException
	 *             if a component at the specified index does not exist
	 */
	public void setSelectedIndex(int selectedIndex)
	{
		if (selectedIndex == UNSELECTED_INDEX)
		{
			checkArgument(isDeselectable() || getComponentCount() == 0, "selectedIndex must be non-negative: %s",
				selectedIndex);
		}
		else
		{
			checkElementIndex(selectedIndex, getComponentCount(), "selectedIndex");
		}
		
		int oldSelectedIndex = this.selectedIndex;
		this.selectedIndex = selectedIndex;
		firePropertyChange(SELECTED_INDEX_PROPERTY, oldSelectedIndex, selectedIndex);
	}
	
	/**
	 * Sets the selected component in this deck.
	 * 
	 * @param selectedComponent
	 *            the selected component in this deck
	 * @throws IllegalArgumentException
	 *             if the specified component is not a child of this deck
	 */
	public void setSelectedComponent(Component selectedComponent)
	{
		int selectedIndex = indexOf(selectedComponent);
		
		checkArgument(selectedIndex != -1, "Unknown component: %s", selectedComponent);
		
		setSelectedIndex(selectedIndex);
	}
	
	public void onSelectIndex(final Closure<? super Integer> indexClosure)
	{
		addPropertyChangeListener(SELECTED_INDEX_PROPERTY, new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				indexClosure.apply(selectedIndex);
			}
		});
	}
	
	public void onSelect(final Closure<? super Component> componentClosure)
	{
		addPropertyChangeListener(SELECTED_INDEX_PROPERTY, new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent event)
			{
				componentClosure.apply(getSelectedComponent());
			}
		});
	}
	
	public Closure<Integer> selectIndex()
	{
		return new Closure<Integer>()
		{
			@Override
			public void apply(Integer selectedIndex)
			{
				setSelectedIndex((selectedIndex != null) ? selectedIndex : -1);
			}
		};
	}
	
	public Closure<Component> select()
	{
		return new Closure<Component>()
		{
			@Override
			public void apply(Component selectedComponent)
			{
				setSelectedComponent(selectedComponent);
			}
		};
	}
	
	public Command selectIndex(final int selectedIndex)
	{
		return new Command()
		{
			@Override
			public void execute()
			{
				setSelectedIndex(selectedIndex);
			}
		};
	}
	
	public Command select(final Component selectedComponent)
	{
		return new Command()
		{
			@Override
			public void execute()
			{
				setSelectedComponent(selectedComponent);
			}
		};
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Gets whether this deck enforces a selected component.
	 * <p>
	 * This implementation returns {@code false}. Subclasses can override this method to change this behaviour.
	 * 
	 * @return {@code true} if this deck can have no selected component, or {@code false} if it must always have a
	 *         selected component
	 */
	protected boolean isDeselectable()
	{
		return false;
	}
}
