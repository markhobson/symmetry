/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/WizardTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.ComboBox;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.ListBox;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Tab;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;
import uk.co.iizuka.kozo.ui.VBox;
import uk.co.iizuka.kozo.ui.Wizard;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: WizardTab.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public class WizardTab extends Tab
{
	// types ------------------------------------------------------------------
	
	private static class DemoWizard extends Wizard
	{
		// fields -----------------------------------------------------------------
		
		private TypePage typePage = new TypePage();
		
		private FruitPage fruitPage = new FruitPage();
		
		private VegetablePage vegetablePage = new VegetablePage();
		
		private MeatPage meatPage = new MeatPage();
		
		private SummaryPage summaryPage = new SummaryPage();
		
		// types ------------------------------------------------------------------
		
		private static class TypePage extends VBox
		{
			// fields -----------------------------------------------------------------
			
			private ToggleButtonGroup group;
			
			// constructors -----------------------------------------------------------
			
			public TypePage()
			{
				group = new ToggleButtonGroup(
					new Radio("Fruit"),
					new Radio("Vegetable"),
					new Radio("Meat")
					);
				add(new Label("What would you like for dinner?"), group);
			}
			
			// public methods ---------------------------------------------------------
			
			public String getType()
			{
				ToggleButton button = group.getSelectedButton();
				return (button == null) ? null : button.getText();
			}
		}
		
		private static class FruitPage extends VBox
		{
			// fields -----------------------------------------------------------------
			
			private ComboBox<String> comboBox;
			
			// constructors -----------------------------------------------------------
			
			public FruitPage()
			{
				comboBox = new ComboBox<String>("Apple", "Banana", "Grapefruit", "Kiwi", "Lemon", "Orange");
				add(new Label("Pick a type of fruit:"), comboBox);
			}
			
			// public methods ---------------------------------------------------------
			
			public boolean isValid()
			{
				return comboBox.getSelectedIndex() != -1;
			}
		}
		
		private static class VegetablePage extends VBox
		{
			// fields -----------------------------------------------------------------
			
			private CheckBox onion = new CheckBox("Onion");
			
			private CheckBox pepper = new CheckBox("Pepper");
			
			private CheckBox carrot = new CheckBox("Carrot");
			
			// constructors -----------------------------------------------------------
			
			public VegetablePage()
			{
				add(new Label("Choose some vegetables for a stir-fry:"), onion, pepper, carrot);
			}
			
			// public methods ---------------------------------------------------------
			
			public boolean isValid()
			{
				return onion.isSelected() || pepper.isSelected() || carrot.isSelected();
			}
		}
		
		private static class MeatPage extends VBox
		{
			// fields -----------------------------------------------------------------
			
			private ListBox<String> listBox;
			
			// constructors -----------------------------------------------------------
			
			public MeatPage()
			{
				listBox = new ListBox<String>("Chicken", "Lamb", "Beef");
				add(new Label("Select a type of meat:"), listBox);
			}
			
			// public methods ---------------------------------------------------------
			
			public boolean isValid()
			{
				return listBox.getSelectedIndex() != -1;
			}
		}
		
		private static class SummaryPage extends VBox
		{
			// public methods ---------------------------------------------------------
			
			public SummaryPage()
			{
				add(new Label("Your meal is ready, click Finish to eat."));
			}
		}
		
		// constructors -----------------------------------------------------------
		
		public DemoWizard()
		{
			addPages(typePage, fruitPage, vegetablePage, meatPage, summaryPage);
		}
		
		// Wizard methods -----------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected Component getPreviousPage(Component page)
		{
			if (page == summaryPage)
			{
				String type = typePage.getType();
				
				if ("Fruit".equals(type))
				{
					return fruitPage;
				}
				
				if ("Vegetable".equals(type))
				{
					return vegetablePage;
				}
				
				if ("Meat".equals(type))
				{
					return meatPage;
				}
			}
			else if (page == fruitPage || page == vegetablePage || page == meatPage)
			{
				return typePage;
			}
			
			return super.getPreviousPage(page);
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected Component getNextPage(Component page)
		{
			if (page == typePage)
			{
				String type = typePage.getType();
				
				if ("Fruit".equals(type))
				{
					return fruitPage;
				}
				
				if ("Vegetable".equals(type))
				{
					return vegetablePage;
				}
				
				if ("Meat".equals(type))
				{
					return meatPage;
				}
				
				return page;
			}
			else if (page == fruitPage)
			{
				return fruitPage.isValid() ? (Component) summaryPage : page;
			}
			else if (page == vegetablePage)
			{
				return vegetablePage.isValid() ? (Component) summaryPage : page;
			}
			else if (page == meatPage)
			{
				return meatPage.isValid() ? (Component) summaryPage : page;
			}
			
			return super.getNextPage(page);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public WizardTab()
	{
		Box box = new GroupBox("Wizard",
			new Label("A Wizard allows the user to navigate through various steps to achieve tasks:"),
			new DemoWizard()
		);
		
		setText("Wizard");
		setComponent(box);
	}
}
