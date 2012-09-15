/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/ListCellRenderers.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static com.google.common.base.Objects.equal;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.functor.Function;
import org.hobsoft.symmetry.ui.functor.Functions;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class ListCellRenderers
{
	// constructors -----------------------------------------------------------
	
	private ListCellRenderers()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T> ListCellRenderer<T> setListCellComponent(final ListCellRenderer<T> delegate, int index,
		final Component component)
	{
		final int setIndex = index;
		
		return new ListCellRenderer<T>()
		{
			@Override
			public Component getListCellComponent(ComboBox<T> comboBox, int index)
			{
				return (index == setIndex) ? component : delegate.getListCellComponent(comboBox, index);
			}
		};
	}
	
	public static <T> ListCellRenderer<T> setListCellComponent(final ListCellRenderer<T> delegate, T item,
		final Component component)
	{
		final T setItem = item;
		
		return new ListCellRenderer<T>()
		{
			@Override
			public Component getListCellComponent(ComboBox<T> comboBox, int index)
			{
				T item = comboBox.getModel().getItem(index);
				
				return equal(setItem, item) ? component : delegate.getListCellComponent(comboBox, index);
			}
		};
	}
	
	public static <T> LabelListCellRenderer<T> forFunction(final Function<T, String> textFunction)
	{
		return new LabelListCellRenderer<T>()
		{
			@Override
			public Label getListCellComponent(ComboBox<T> comboBox, int index)
			{
				T item = comboBox.getModel().getItem(index);
				String text = textFunction.apply(item);
				
				setText(text);
				
				return this;
			}
		};
	}
	
	public static <E extends Enum<E>> LabelListCellRenderer<E> forEnum()
	{
		// TODO: cache when satisfactory way of dealing with generics decided upon
		return forFunction(Functions.<E>enumTitleCase());
	}
}
