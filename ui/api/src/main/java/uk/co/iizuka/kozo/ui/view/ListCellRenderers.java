/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/ListCellRenderers.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import static com.google.common.base.Objects.equal;

import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.functor.Function;
import uk.co.iizuka.kozo.ui.functor.Functions;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ListCellRenderers.java 99067 2012-03-08 13:04:22Z mark@IIZUKA.CO.UK $
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
