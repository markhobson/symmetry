/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/editor/DatePropertyEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.editor;

import java.beans.PropertyEditor;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hobsoft.symmetry.support.bean.PropertyEditorErrorHandler;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.HBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.model.ListModel;
import org.hobsoft.symmetry.ui.util.DateFormatSymbolsFactory;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DatePropertyEditor extends AbstractPropertyEditor implements PropertyEditorErrorHandler
{
	// TODO: sort out proper validation..

	// types ------------------------------------------------------------------
	
	private static class DateBox extends HBox
	{
		// fields -----------------------------------------------------------------
		
		private Calendar calendar;
		
		private DateFormatSymbols symbols;
		
		private ComboBox<String> day;
		
		private ComboBox<String> month;
		
		private TextBox year;
		
		private Label label;
		
		// types ------------------------------------------------------------------
		
		private class DayListModel implements ListModel<String>
		{
			// ListModel methods ------------------------------------------------------
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getItem(int index)
			{
				if (index < 0 || index >= getItemCount())
				{
					throw new IndexOutOfBoundsException();
				}
				
				return Integer.toString(index + 1);
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public int getItemCount()
			{
				return calendar.getMaximum(Calendar.DAY_OF_MONTH);
			}
		}
		
		private class MonthListModel implements ListModel<String>
		{
			// ListModel methods ------------------------------------------------------
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getItem(int index)
			{
				if (index < 0 || index >= getItemCount())
				{
					throw new IndexOutOfBoundsException();
				}
				
				return symbols.getMonths()[index];
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public int getItemCount()
			{
				return calendar.getMaximum(Calendar.MONTH) + 1;
			}
		}
		
		// constructors -----------------------------------------------------------
		
		public DateBox()
		{
			this(Locale.getDefault());
		}
		
		public DateBox(Locale locale)
		{
			setLocale(locale);
			
			day = new ComboBox<String>(new DayListModel());
			month = new ComboBox<String>(new MonthListModel());
			year = new TextBox(4);
			label = new Label();
			label.addStyle(Style.ERROR);
			add(day);
			add(month);
			add(year);
			add(label);
		}
		
		// public methods ---------------------------------------------------------
		
		public ComboBox<String> getDayBox()
		{
			return day;
		}
		
		public ComboBox<String> getMonthBox()
		{
			return month;
		}
		
		public TextBox getYearBox()
		{
			return year;
		}
		
		public Label getLabel()
		{
			return label;
		}
		
		public void setLocale(Locale locale)
		{
			calendar = Calendar.getInstance(locale);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			symbols = DateFormatSymbolsFactory.getInstance(locale);
		}
		
		public Date getDate()
		{
			calendar.set(Integer.parseInt(year.getText()), month.getSelectedIndex(), day.getSelectedIndex() + 1);
			return calendar.getTime();
		}
		
		public void setDate(Date date)
		{
			if (date != null)
			{
				calendar.setTime(date);
				day.setSelectedIndex(calendar.get(Calendar.DAY_OF_MONTH) - 1);
				month.setSelectedIndex(calendar.get(Calendar.MONTH));
				year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
			}
			else
			{
				day.setSelectedIndex(-1);
				month.setSelectedIndex(-1);
				year.setText(null);
			}
		}
		
		// Component methods ------------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setTransient(boolean trans)
		{
			// TODO: be nice to avoid this..
			super.setTransient(trans);
			day.setTransient(trans);
			month.setTransient(trans);
			year.setTransient(trans);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public DatePropertyEditor()
	{
		super(new DateBox());
	}
	
	// AbstractPropertyEditor methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		Date date = getDateBox().getDate();
		getDateBox().getLabel().setText("");
		return date;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		getDateBox().setDate((Date) value);
		getDateBox().getLabel().setText("");
	}
	
	// PropertyEditorErrorHandler ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleError(PropertyEditor editor, Throwable exception)
	{
		getDateBox().getLabel().setText("x");
	}
	
	// protected methods ------------------------------------------------------
	
	protected DateBox getDateBox()
	{
		return (DateBox) getComponent();
	}
}
