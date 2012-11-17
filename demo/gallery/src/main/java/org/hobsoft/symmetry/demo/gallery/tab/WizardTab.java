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

import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.VBox;
import org.hobsoft.symmetry.ui.Wizard;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class WizardTab extends Tab
{
	// types ------------------------------------------------------------------
	
	private static class DemoWizard extends Wizard
	{
		// fields -----------------------------------------------------------------
		
		private final TypePage typePage;
		
		private final FruitPage fruitPage;
		
		private final VegetablePage vegetablePage;
		
		private final MeatPage meatPage;
		
		private final SummaryPage summaryPage;
		
		// types ------------------------------------------------------------------
		
		private static class TypePage extends VBox
		{
			// fields -----------------------------------------------------------------
			
			private final ToggleButtonGroup group;
			
			// constructors -----------------------------------------------------------
			
			public TypePage()
			{
				group = new ToggleButtonGroup(new Radio("Fruit"), new Radio("Vegetable"), new Radio("Meat"));
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
			
			private final ComboBox<String> comboBox;
			
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
			
			private final CheckBox onion;
			
			private final CheckBox pepper;
			
			private final CheckBox carrot;
			
			// constructors -----------------------------------------------------------
			
			public VegetablePage()
			{
				onion = new CheckBox("Onion");
				pepper = new CheckBox("Pepper");
				carrot = new CheckBox("Carrot");
				
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
			
			private final ListBox<String> listBox;
			
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
			// constructors -----------------------------------------------------------
			
			public SummaryPage()
			{
				add(new Label("Your meal is ready, click Finish to eat."));
			}
		}
		
		// constructors -----------------------------------------------------------
		
		public DemoWizard()
		{
			typePage = new TypePage();
			fruitPage = new FruitPage();
			vegetablePage = new VegetablePage();
			meatPage = new MeatPage();
			summaryPage = new SummaryPage();

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
		setText("Wizard");
		setComponent(createWizardBox());
	}

	// private methods --------------------------------------------------------
	
	private GroupBox createWizardBox()
	{
		GroupBox box = new GroupBox("Wizard");
		box.add(new Label("A Wizard allows the user to navigate through various steps to achieve tasks:"));
		box.add(new DemoWizard());
		return box;
	}
}
