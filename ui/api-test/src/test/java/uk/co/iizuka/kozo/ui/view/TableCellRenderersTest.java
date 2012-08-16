/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/view/TableCellRenderersTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.iizuka.kozo.ui.FakeEnum;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.model.DefaultTableModel;

/**
 * Tests {@code TableCellRenderers}.
 * 
 * @author Mark Hobson
 * @version $Id: TableCellRenderersTest.java 90330 2011-07-08 11:17:36Z mark@IIZUKA.CO.UK $
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
