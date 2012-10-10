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
package org.hobsoft.symmetry.ui.binding;

import java.util.List;

import javax.activation.DataSource;

import org.hobsoft.entangle.Observable;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Container;
import org.hobsoft.symmetry.ui.Deck;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.TextArea;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.Window;

/**
 * Factory to create observables for Kozo UI components.
 * 
 * @author Mark Hobson
 * @version $Id: KozoObservables.java 99170 2012-03-09 17:09:31Z mark@IIZUKA.CO.UK $
 */
public final class KozoObservables
{
	// TODO: could be replaced with Observables.property when generic bean API introduced
	
	// types ------------------------------------------------------------------
	
	/**
	 * Factory to create observables for {@code Component} properties.
	 * 
	 * @see Component
	 */
	public interface ComponentObservables
	{
		// TODO: name
		// TODO: parent
		// TODO: transient
		// TODO: visible
		// TODO: flex
		// TODO: styles
	}

	/**
	 * Factory to create observables for {@code Selectable} properties.
	 * 
	 * @see uk.co.iizuka.kozo.ui.Selectable
	 */
	public interface SelectableObservables
	{
		Observable<Boolean> selected();
	}
	
	/**
	 * Factory to create observables for {@code Enableable} properties.
	 * 
	 * @see uk.co.iizuka.kozo.ui.Enableable
	 */
	public interface EnableableObservables
	{
		Observable<Boolean> enabled();
	}
	
	/**
	 * Factory to create observables for {@code Container} properties.
	 * 
	 * @see Container
	 */
	public interface ContainerObservables extends ComponentObservables
	{
		// TODO: components
	}
	
	/**
	 * Factory to create observables for {@code Box} properties.
	 * 
	 * @see Box
	 */
	public interface BoxObservables extends ContainerObservables
	{
		// TODO: orientation
	}
	
	/**
	 * Factory to create observables for {@code Deck} properties.
	 * 
	 * @see Deck
	 */
	public interface DeckObservables extends BoxObservables
	{
		// TODO: selectedIndex
		// TODO: selectedComponent?
	}
	
	/**
	 * Factory to create observables for {@code Grid} properties.
	 * 
	 * @see Grid
	 */
	public interface GridObservables extends ContainerObservables
	{
		// TODO: columns
	}
	
	/**
	 * Factory to create observables for {@code GroupBox} properties.
	 * 
	 * @see GroupBox
	 */
	public interface GroupBoxObservables extends BoxObservables
	{
		// TODO: title
	}
	
	/**
	 * Factory to create observables for {@code ToggleButtonGroup} properties.
	 * 
	 * @see ToggleButtonGroup
	 */
	public interface ToggleButtonGroupObservables extends BoxObservables
	{
		Observable<ToggleButton> selectedButton();
	}
	
	/**
	 * Factory to create observables for {@code Window} properties.
	 * 
	 * @see Window
	 */
	public interface WindowObservables extends BoxObservables
	{
		// TODO: title
		// TODO: image
	}
	
	/**
	 * Factory to create observables for {@code ComboBox} properties.
	 * 
	 * @param <T>
	 *            the item type
	 * @see ComboBox
	 */
	public interface ComboBoxObservables<T> extends ComponentObservables
	{
		// TODO: model
		
		// TODO: remove in favour of selectedItem when ComboBox switches the property for selected
		Observable<Integer> selectedIndex();
		
		Observable<T> selectedItem();

		// TODO: listCellRenderer
	}
	
	/**
	 * Factory to create observables for {@code ListBox} properties.
	 * 
	 * @param <T>
	 *            the item type
	 * @see ListBox
	 */
	public interface ListBoxObservables<T> extends ComboBoxObservables<T>
	{
		Observable<int[]> selectedIndexes();
		
		Observable<List<T>> selectedItems();
		
		// TODO: selectionMode
		// TODO: visibleRowCount
	}
	
	/**
	 * Factory to create observables for {@code FileChooser} properties.
	 * 
	 * @see FileChooser
	 */
	public interface FileChooserObservables extends ComponentObservables
	{
		Observable<DataSource> file();
	}
	
	/**
	 * Factory to create observables for {@code Label} properties.
	 * 
	 * @see Label
	 */
	public interface LabelObservables extends ComponentObservables
	{
		// TODO: image
		
		Observable<String> text();
		
		// TODO: toolTip
		// TODO: labelFor
	}
	
	/**
	 * Factory to create observables for {@code Button} properties.
	 * 
	 * @see Button
	 */
	public interface ButtonObservables extends LabelObservables, EnableableObservables
	{
		// TODO: action
		// TODO: mnemonic
	}
	
	/**
	 * Factory to create observables for {@code Tab} properties.
	 * 
	 * @see Tab
	 */
	public interface TabObservables extends ButtonObservables
	{
		// TODO: component
	}
	
	/**
	 * Factory to create observables for {@code ToggleButton} properties.
	 * 
	 * @see ToggleButton
	 */
	public interface ToggleButtonObservables extends ButtonObservables, SelectableObservables
	{
		// no properties
	}
	
	/**
	 * Factory to create observables for {@code TabBox} properties.
	 * 
	 * @see TabBox
	 */
	public interface TabBoxObservables extends ComponentObservables
	{
		// TODO: selectedTabIndex
		// TODO: tabs
	}
	
	/**
	 * Factory to create observables for {@code Table} properties.
	 * 
	 * @see Table
	 */
	public interface TableObservables extends ComponentObservables
	{
		// TODO: model
		// TODO: visibleRowCount
		// TODO: firstVisibleRowIndex
		// TODO: tableHeaderRenderer
		// TODO: tableCellRenderer
		// TODO: tableHeaderListener
		// TODO: tableCellListener
	}
	
	// TODO: SortableTableObservables?
	
	/**
	 * Factory to create observables for {@code TextBox} properties.
	 * 
	 * @see TextBox
	 */
	public interface TextBoxObservables extends ComponentObservables, EnableableObservables
	{
		// TODO: columns
		// TODO: maxLength
		// TODO: readOnly
		
		Observable<String> text();
	}
	
	/**
	 * Factory to create observables for {@code TextArea} properties.
	 * 
	 * @see TextArea
	 */
	public interface TextAreaObservables extends TextBoxObservables
	{
		// TODO: rows
	}
	
	/**
	 * Factory to create observables for {@code Tree} properties.
	 * 
	 * @see Tree
	 */
	public interface TreeObservables extends ComponentObservables
	{
		// TODO: treeExpansionListener
		// TODO: treeSelectionListener
		// TODO: model
		// TODO: selectionMode
		// TODO: treeNodeRenderer
		// TODO: expandedPaths
		// TODO: selectedPaths
		// TODO: autoExpand
		// TODO: autoCollapse
	}
	
	// constructors -----------------------------------------------------------
	
	private KozoObservables()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static ComponentObservables component(Component component)
	{
		return new DefaultComponentObservables(component);
	}
	
	// TODO: component(Selectable)?
	
	// TODO: component(Enableable)?
	
	public static ContainerObservables component(Container container)
	{
		return new DefaultContainerObservables(container);
	}
	
	public static BoxObservables component(Box box)
	{
		return new DefaultBoxObservables(box);
	}
	
	public static DeckObservables component(Deck deck)
	{
		return new DefaultDeckObservables(deck);
	}
	
	public static GridObservables component(Grid grid)
	{
		return new DefaultGridObservables(grid);
	}
	
	public static GroupBoxObservables component(GroupBox groupBox)
	{
		return new DefaultGroupBoxObservables(groupBox);
	}
	
	public static ToggleButtonGroupObservables component(ToggleButtonGroup toggleButtonGroup)
	{
		return new DefaultToggleButtonGroupObservables(toggleButtonGroup);
	}
	
	public static WindowObservables component(Window window)
	{
		return new DefaultWindowObservables(window);
	}
	
	public static <T> ComboBoxObservables<T> component(ComboBox<T> comboBox)
	{
		return new DefaultComboBoxObservables<T>(comboBox);
	}
	
	public static <T> ListBoxObservables<T> component(ListBox<T> listBox)
	{
		return new DefaultListBoxObservables<T>(listBox);
	}

	public static FileChooserObservables component(FileChooser fileChooser)
	{
		return new DefaultFileChooserObservables(fileChooser);
	}
	
	public static LabelObservables component(Label label)
	{
		return new DefaultLabelObservables(label);
	}
	
	public static ButtonObservables component(Button button)
	{
		return new DefaultButtonObservables(button);
	}
	
	public static TabObservables component(Tab tab)
	{
		return new DefaultTabObservables(tab);
	}
	
	public static ToggleButtonObservables component(ToggleButton toggleButton)
	{
		return new DefaultToggleButtonObservables(toggleButton);
	}
	
	public static TabBoxObservables component(TabBox tabBox)
	{
		return new DefaultTabBoxObservables(tabBox);
	}
	
	public static TableObservables component(Table table)
	{
		return new DefaultTableObservables(table);
	}
	
	// TODO: component(SortableTable)?
	
	public static TextBoxObservables component(TextBox textBox)
	{
		return new DefaultTextBoxObservables(textBox);
	}
	
	public static TextAreaObservables component(TextArea textArea)
	{
		return new DefaultTextAreaObservables(textArea);
	}
	
	public static TreeObservables component(Tree tree)
	{
		return new DefaultTreeObservables(tree);
	}
}
