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
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Table;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedTableHydrator;
import org.hobsoft.symmetry.ui.model.TableModel;
import org.hobsoft.symmetry.ui.traversal.DelegatingTableVisitor;
import org.hobsoft.symmetry.ui.traversal.TableVisitor;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHref;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.visibleRows;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

/**
 * Table dehydrator decorator that only dehydrates visible rows and adds a table footer.
 * 
 * @author Mark Hobson
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
