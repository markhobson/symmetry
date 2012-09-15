/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/ListCellRenderersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import static org.hobsoft.symmetry.ui.test.model.ListModelSupport.createItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.hobsoft.symmetry.ui.ComboBox;
import org.hobsoft.symmetry.ui.FakeEnum;
import org.hobsoft.symmetry.ui.Label;
import org.junit.Test;

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
