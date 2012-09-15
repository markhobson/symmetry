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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.FakeEnum;
import org.hobsoft.symmetry.ui.Label;
import org.junit.Test;

import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Tests {@code ListCellRenderers}.
 * 
 * @author Mark Hobson
 * @see ListCellRenderers
 */
public class ListCellRenderersTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void setListCellComponentWithMatchingIndex()
	{
		ListCellRenderer<Object> delegate = new StubListCellRenderer<Object>(new Label());
		Label component = new Label();
		ListCellRenderer<Object> renderer = ListCellRenderers.setListCellComponent(delegate, 0, component);
		
		assertSame(component, renderer.getListCellComponent(new ComboBox<Object>(), 0));
	}
	
	@Test
	public void setListCellComponentWithOtherIndex()
	{
		Label component = new Label();
		ListCellRenderer<Object> delegate = new StubListCellRenderer<Object>(component);
		ListCellRenderer<Object> renderer = ListCellRenderers.setListCellComponent(delegate, 0, new Label());
		
		assertSame(component, renderer.getListCellComponent(new ComboBox<Object>(), 1));
	}
	
	@Test
	public void setListCellComponentWithMatchingItem()
	{
		Object item = createItem();
		ListCellRenderer<Object> delegate = new StubListCellRenderer<Object>(new Label());
		Label component = new Label();
		ListCellRenderer<Object> renderer = ListCellRenderers.setListCellComponent(delegate, item, component);
		
		assertSame(component, renderer.getListCellComponent(new ComboBox<Object>(item), 0));
	}
	
	@Test
	public void setListCellComponentWithOtherItem()
	{
		Label component = new Label();
		ListCellRenderer<Object> delegate = new StubListCellRenderer<Object>(component);
		Object item = createItem();
		ListCellRenderer<Object> renderer = ListCellRenderers.setListCellComponent(delegate, item, new Label());
		
		assertSame(component, renderer.getListCellComponent(new ComboBox<Object>(item, createItem()), 1));
	}
	
	@Test
	public void forFunction()
	{
		ComboBox<Object> comboBox = new ComboBox<Object>(createItem());
		Label label = ListCellRenderers.forFunction(new StubFunction<String>("x")).getListCellComponent(comboBox, 0);
		
		assertEquals("x", label.getText());
	}
	
	@Test
	public void forEnumWithLetter()
	{
		ComboBox<FakeEnum> comboBox = new ComboBox<FakeEnum>(FakeEnum.A);
		Label label = ListCellRenderers.<FakeEnum>forEnum().getListCellComponent(comboBox, 0);
		
		assertEquals("A", label.getText());
	}
	
	@Test
	public void forEnumWithWord()
	{
		ComboBox<FakeEnum> comboBox = new ComboBox<FakeEnum>(FakeEnum.WORD);
		Label label = ListCellRenderers.<FakeEnum>forEnum().getListCellComponent(comboBox, 0);
		
		assertEquals("Word", label.getText());
	}
	
	@Test
	public void forEnumWithMultipleWords()
	{
		ComboBox<FakeEnum> comboBox = new ComboBox<FakeEnum>(FakeEnum.MULTIPLE_WORDS);
		Label label = ListCellRenderers.<FakeEnum>forEnum().getListCellComponent(comboBox, 0);
		
		assertEquals("Multiple Words", label.getText());
	}
}
