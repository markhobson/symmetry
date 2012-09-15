/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/TableCellRenderersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.FakeEnum;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.model.DefaultTableModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code TableCellRenderers}.
 * 
 * @author Mark Hobson
 * @see TableCellRenderers
 */
public class TableCellRenderersTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void forFunction()
	{
		Table table = new Table(new DefaultTableModel(new Object[] {new Object()}));
		Label label = TableCellRenderers.forFunction(new StubFunction<String>("x")).getTableCellComponent(table, 0, 0);
		
		assertEquals("x", label.getText());
	}
	
	@Test
	public void forEnumWithWord()
	{
		Table table = new Table(new DefaultTableModel(new Object[] {FakeEnum.WORD}));
		Label label = TableCellRenderers.forEnum().getTableCellComponent(table, 0, 0);
		
		assertEquals("Word", label.getText());
	}
}
