/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPagedTableDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeHref;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.visibleRows;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedTableHydrator;
import uk.co.iizuka.kozo.ui.model.TableModel;
import uk.co.iizuka.kozo.ui.traversal.DelegatingTableVisitor;
import uk.co.iizuka.kozo.ui.traversal.TableVisitor;

/**
 * Table dehydrator decorator that only dehydrates visible rows and adds a table footer.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlPagedTableDehydrator.java 99706 2012-03-20 16:50:54Z mark@IIZUKA.CO.UK $
 * @see Table
 * @param <T>
 *            the table type this visitor can visit
 */
public class HtmlPagedTableDehydrator<T extends Table>
	extends DelegatingTableVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// constructors -----------------------------------------------------------
	
	public HtmlPagedTableDehydrator(TableVisitor<? super T, HydrationContext, HydrationException> delegate)
	{
		super(visibleRows(delegate));
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, HydrationContext context) throws HydrationException
	{
		super.endVisitHeader(table, context);
		
		try
		{
			writeFooter(table, context);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	// public methods ---------------------------------------------------------
	
	public static <T extends PhasedTableHydrator<U>, U extends Table> T decorate(T phasedHydrator)
	{
		phasedHydrator.setDelegate(DEHYDRATE_INITIALIZE, visibleRows(phasedHydrator.getDelegate(DEHYDRATE_INITIALIZE)));
		phasedHydrator.setDelegate(DEHYDRATE, new HtmlPagedTableDehydrator<U>(phasedHydrator.getDelegate(DEHYDRATE)));
		return phasedHydrator;
	}
	
	// private methods --------------------------------------------------------
	
	private static void writeFooter(Table table, HydrationContext context) throws HydrationException, XMLStreamException
	{
		// TODO: refactor into smaller methods!
		
		int pageSize = table.getVisibleRowCount();
		
		if (pageSize == 0)
		{
			return;
		}
		
		TableModel model = table.getModel();
		int columns = model.getColumnCount();
		int rows = model.getRowCount();
		int firstRow = Math.min(table.getFirstVisibleRowIndex(), rows - 1);
		int lastRow = Math.min(firstRow + pageSize - 1, rows - 1);
		int previousPageRow = Math.max(firstRow - pageSize, 0);
		int nextPageRow = Math.min(firstRow + pageSize, rows - 1);
		int lastPageRow = (rows - 1) - ((rows - 1) % pageSize);
		boolean hasPrevious = (firstRow > 0);
		boolean hasNext = (lastRow < rows - 1);
		
		if (!hasPrevious && !hasNext)
		{
			return;
		}

		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		out.writeStartElement("tfoot");
		out.writeStartElement("tr");
		out.writeStartElement("td");
		out.writeAttribute("colspan", Integer.toString(columns));
		out.writeStartElement("ul");
		
		// << first
		out.writeStartElement("li");
		if (hasPrevious)
		{
			out.writeStartElement("a");
			out.writeAttribute("title", "Go to first page");
			writeHref((DehydrationContext) context, table, Table.FIRST_VISIBLE_ROW_INDEX_PROPERTY, 0);
		}
		else
		{
			out.writeAttribute("class", "disabled");
		}
		
		out.writeEntityRef("laquo");
		out.writeCharacters(" First");
		
		if (hasPrevious)
		{
			// a
			out.writeEndElement();
		}
		
		// li
		out.writeEndElement();
		
		// < previous
		out.writeStartElement("li");
		
		if (hasPrevious)
		{
			out.writeStartElement("a");
			out.writeAttribute("title", "Go to previous page");
			writeHref((DehydrationContext) context, table, Table.FIRST_VISIBLE_ROW_INDEX_PROPERTY, previousPageRow);
		}
		else
		{
			out.writeAttribute("class", "disabled");
		}
		
		out.writeEntityRef("lsaquo");
		out.writeCharacters(" Previous");
		
		if (hasPrevious)
		{
			// a
			out.writeEndElement();
		}
		
		// li
		out.writeEndElement();
		
		// position
		out.writeStartElement("li");
		out.writeAttribute("class", "separated");
		out.writeCharacters(Integer.toString(firstRow + 1));
		out.writeCharacters("-");
		out.writeCharacters(Integer.toString(lastRow + 1));
		out.writeCharacters(" of ");
		out.writeCharacters(Integer.toString(rows));
		// li
		out.writeEndElement();
		
		// > next
		out.writeStartElement("li");
		
		if (hasNext)
		{
			out.writeStartElement("a");
			out.writeAttribute("title", "Go to next page");
			writeHref((DehydrationContext) context, table, Table.FIRST_VISIBLE_ROW_INDEX_PROPERTY, nextPageRow);
		}
		else
		{
			out.writeAttribute("class", "disabled");
		}
		
		out.writeCharacters("Next ");
		out.writeEntityRef("rsaquo");
		
		if (hasNext)
		{
			// a
			out.writeEndElement();
		}
		
		// li
		out.writeEndElement();
		
		// >> last
		out.writeStartElement("li");
		
		if (hasNext)
		{
			out.writeStartElement("a");
			out.writeAttribute("title", "Go to last page");
			writeHref((DehydrationContext) context, table, Table.FIRST_VISIBLE_ROW_INDEX_PROPERTY, lastPageRow);
		}
		else
		{
			out.writeAttribute("class", "disabled");
		}
		
		out.writeCharacters("Last ");
		out.writeEntityRef("raquo");
		
		if (hasNext)
		{
			// a
			out.writeEndElement();
		}
		
		// li
		out.writeEndElement();
		
		// ul
		out.writeEndElement();
		// td
		out.writeEndElement();
		// tr
		out.writeEndElement();
		// tfoot
		out.writeEndElement();
	}
}
