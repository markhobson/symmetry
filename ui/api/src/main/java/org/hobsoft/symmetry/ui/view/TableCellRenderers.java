/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/view/TableCellRenderers.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.functor.Function;
import org.hobsoft.symmetry.ui.functor.Functions;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableCellRenderers.java 88032 2011-05-13 19:04:22Z mark@IIZUKA.CO.UK $
 */
public final class TableCellRenderers
{
	// constructors -----------------------------------------------------------
	
	private TableCellRenderers()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static LabelTableCellRenderer forFunction(final Function<Object, String> textFunction)
	{
		return new LabelTableCellRenderer()
		{
			@Override
			public Label getTableCellComponent(Table table, int row, int column)
			{
				Object value = table.getModel().getValueAt(row, column);
				String text = textFunction.apply(value);
				
				setText(text);
				
				return this;
			}
		};
	}
	
	public static LabelTableCellRenderer forEnum()
	{
		// TODO: cache
		// TODO: resolve how to avoid using raw Enum type
		// TODO: compose functions when cast function implemented
		return new LabelTableCellRenderer()
		{
			@Override
			public Label getTableCellComponent(Table table, int row, int column)
			{
				Enum value = (Enum) table.getModel().getValueAt(row, column);
				// TODO: string cast for javac
				String text = (String) Functions.<Enum>enumTitleCase().apply(value);
				
				setText(text);
				
				return this;
			}
		};
	}
}
