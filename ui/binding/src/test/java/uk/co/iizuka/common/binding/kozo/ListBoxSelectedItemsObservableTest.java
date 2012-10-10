/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/test/java/uk/co/iizuka/common/binding/kozo/ListBoxSelectedItemsObservableTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import static org.junit.Assert.assertEquals;
import static uk.co.iizuka.common.binding.kozo.test.TestObservableListeners.mockObservableListenerWithValueChanged;

import java.util.Arrays;
import java.util.Collections;

import org.hobsoft.symmetry.ui.ListBox;
import org.hobsoft.symmetry.ui.SelectionMode;
import org.hobsoft.symmetry.ui.model.DefaultListModel;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code ListBoxSelectedItemsObservable}.
 * 
 * @author Mark Hobson
 * @version $Id: ListBoxSelectedItemsObservableTest.java 98355 2012-02-08 12:04:55Z mark@IIZUKA.CO.UK $
 * @see ListBoxSelectedItemsObservable
 */
@RunWith(JMock.class)
public class ListBoxSelectedItemsObservableTest
{
	// fields -----------------------------------------------------------------
	
	private Mockery mockery = new JUnit4Mockery();
	
	private ListBox<String> listBox;
	
	private ListBoxSelectedItemsObservable<String> observable;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		listBox = new ListBox<String>();
		observable = new ListBoxSelectedItemsObservable<String>(listBox);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void addObservableListener()
	{
		listBox.setModel(new DefaultListModel<String>("x"));
		
		observable.addObservableListener(mockObservableListenerWithValueChanged(mockery, observable,
			Collections.<String>emptyList(), Collections.singletonList("x")));
		
		listBox.setSelectedItems("x");
	}
	
	@Test
	public void getValueWhenNoneSelected()
	{
		assertEquals(Collections.emptyList(), observable.getValue());
	}
	
	@Test
	public void getValueWhenItemSelected()
	{
		listBox.setModel(new DefaultListModel<String>("x"));
		listBox.setSelectedItems("x");
		
		assertEquals(Collections.singletonList("x"), observable.getValue());
	}
	
	@Test
	public void getValueWhenItemsSelected()
	{
		listBox.setModel(new DefaultListModel<String>("x", "y"));
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		listBox.setSelectedItems("x", "y");
		
		assertEquals(Arrays.asList("x", "y"), observable.getValue());
	}
	
	@Test
	public void setValueWithItem()
	{
		listBox.setModel(new DefaultListModel<String>("x"));
		
		observable.setValue(Collections.singletonList("x"));
		
		assertEquals(Collections.singletonList("x"), listBox.getSelectedItemsAsList());
	}
	
	@Test
	public void setValueWithItems()
	{
		listBox.setModel(new DefaultListModel<String>("x", "y"));
		listBox.setSelectionMode(SelectionMode.MULTIPLE);
		
		observable.setValue(Arrays.asList("x", "y"));
		
		assertEquals(Arrays.asList("x", "y"), listBox.getSelectedItemsAsList());
	}
}
