/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTableDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.TableColumn;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.event.TableEvent;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.traversal.NullTableVisitor;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHref;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlTableUtils.writeCols;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Table} component to an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTableDehydrator.java 100104 2012-03-30 16:02:52Z mark@IIZUKA.CO.UK $
 * @see Table
 * @param <T>
 *            the table type this visitor can visit
 */
public class HtmlTableDehydrator<T extends Table>
	extends NullTableVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("table");
			writeClass(out, getTableCssClass(table));
			
			writeColumns(out, table);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// table
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("thead");
			out.writeStartElement("tr");
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (table.getHeaderListenerCount() > 0);
		
		try
		{
			out.writeStartElement("th");
			
			// TODO: remove this in preference to always using <a> and differentiate using a and a:link CSS classes
			if (!hasListeners)
			{
				out.writeAttribute("class", "table-nolink");
			}
			
			if (hasListeners)
			{
				out.writeStartElement("a");
				writeHref((DehydrationContext) context, Table.HEADER_EVENT, new TableEvent(table, columnIndex));
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (table.getHeaderListenerCount() > 0);
		
		try
		{
			if (hasListeners)
			{
				// a
				out.writeEndElement();
			}
			
			// th
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// tr
			out.writeEndElement();
			// thead
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("tbody");
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("tr");
			writeClass(out, getTableRowCssClass(table, rowIndex));
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, HydrationContext context, int rowIndex, int columnIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (table.getColumn(columnIndex).getCellListeners().length > 0);
		
		try
		{
			out.writeStartElement("td");
			
			if (hasListeners)
			{
				out.writeStartElement("a");
				// TODO: we should strictly write out an indexed event set here for the current column
				writeHref((DehydrationContext) context, Table.CELL_EVENT, new TableEvent(table, rowIndex, columnIndex));
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, HydrationContext context, int rowIndex, int columnIndex)
		throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (table.getColumn(columnIndex).getCellListeners().length > 0);
		
		try
		{
			if (hasListeners)
			{
				// a
				out.writeEndElement();
			}
			
			// td
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// tr
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// tbody
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// TODO: reinstate when StAX problems fixed
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	protected void dehydrateTableHeaderCell(Table table, RendererContext context, int column) throws Exception
//	{
//		EventFilter filter = new NegationEventFilter(XHTMLUtils.BLOCK_FILTER);
//		RendererContext filteredContext =
//			XMLUtils.createFilteredContext(context, filter);
//		super.renderTableHeaderCell(table, filteredContext, column);
//	}
	
	// TODO: reinstate when StAX problems fixed
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	protected void dehydrateTableRowCell(Table table, RendererContext context, int row, int column) throws Exception
//	{
//		EventFilter filter = new NegationEventFilter(HtmlFilters.BLOCK);
//		RendererContext filteredContext = XmlUtils.createFilteredContext(context, filter);
//		super.renderTableRowCell(table, filteredContext, row, column);
//	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getTableCssClass(T table)
	{
		CssClassBuilder builder = new CssClassBuilder("table");
		
		if (getTotalHorizontalFlex(table) > 0)
		{
			builder.append("flexed");
		}
		
		return builder;
	}
	
	protected CssClassBuilder getTableRowCssClass(T table, int rowIndex)
	{
		CssClassBuilder builder = new CssClassBuilder();
		
		int visibleRowIndex = rowIndex - table.getFirstVisibleRowIndex();
		boolean odd = (visibleRowIndex % 2 == 0);
		
		// TODO: remove and replace with :nth-child CSS3 selector
		builder.append(odd ? "table-row-odd" : "table-row-even");
		
		return builder;
	}
	
	// private methods --------------------------------------------------------
	
	private static void writeColumns(XMLStreamWriter out, Table table) throws XMLStreamException
	{
		// TODO: can we achieve this by TableVisitor visiting columns?
		
		List<Length> columnWidths = getColumnWidths(table);
		LengthUtils.normalize(columnWidths);
		
		writeCols(out, columnWidths);
	}
	
	private static List<Length> getColumnWidths(Table table)
	{
		List<Length> columnWidths = new ArrayList<Length>();
		
		for (TableColumn column : table.getColumns())
		{
			columnWidths.add(column.getWidth());
		}
		
		return columnWidths;
	}
	
	private static int getTotalHorizontalFlex(Table table)
	{
		int flex = 0;
		
		for (TableColumn column : table.getColumns())
		{
			flex += LengthUtils.getFlex(column.getWidth());
		}
		
		return flex;
	}
}
