/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlGridDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.Css;
import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.GridColumn;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.layout.GridLayoutData;
import org.hobsoft.symmetry.ui.layout.HorizontalAlignment;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.layout.VerticalAlignment;
import org.hobsoft.symmetry.ui.traversal.NullGridVisitor;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeStyle;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlTableUtils.writeCols;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttribute;

/**
 * Hydrator that dehydrates a {@code Grid} component to an HTML {@code <table/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlGridDehydrator.java 100110 2012-03-30 16:31:26Z mark@IIZUKA.CO.UK $
 * @see Grid
 * @param <T>
 *            the grid type this visitor can visit
 */
public class HtmlGridDehydrator<T extends Grid>
	extends NullGridVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// TODO: can we reuse TableHtmlBoxHydrator, or vice-versa?
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T grid, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("table");
			writeClass(out, getGridCssClass(grid));
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
	public EndVisit endVisit(T grid, HydrationContext context) throws HydrationException
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
	
	// GridVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitColumns(T grid, HydrationContext context) throws HydrationException
	{
		context.push(Lengths.class, new Lengths());
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit visitColumn(T grid, HydrationContext context, int columnIndex) throws HydrationException
	{
		GridColumn column = grid.getColumn(columnIndex);
		Length width = column.getWidth();

		// defer writing since we need all column widths first
		context.get(Lengths.class).add(width);
		
		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		List<Length> columnWidths = context.pop(Lengths.class).normalize().getLengths();
		
		try
		{
			writeCols(out, columnWidths);
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
	public Visit visitRows(T grid, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			if (grid.getRowCount() > 0)
			{
				out.writeStartElement("tbody");
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
	public Visit visitRow(T grid, HydrationContext context, int rowIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
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
	public EndVisit endVisitRow(T grid, HydrationContext context, int rowIndex) throws HydrationException
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
	public EndVisit endVisitRows(T grid, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			if (grid.getRowCount() > 0)
			{
				// tbody
				out.writeEndElement();
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// ContainerVisitor methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitChild(T grid, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("td");
			writeStyle(out, getGridChildCssStyle(grid, context, childIndex));
			
			int columnSpan = grid.getLayoutData(childIndex).getColumnSpan();
			
			if (columnSpan > 1)
			{
				writeAttribute(out, "colspan", columnSpan);
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
	public EndVisit endVisitChild(T grid, HydrationContext context, int childIndex) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// td
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getGridCssClass(T grid)
	{
		CssClassBuilder builder = new CssClassBuilder("grid");
		
		if (getTotalHorizontalFlex(grid) > 0)
		{
			builder.append("flexed");
		}
		
		return builder;
	}
	
	protected CssStyleBuilder getGridChildCssStyle(T grid, HydrationContext context, int childIndex)
	{
		CssStyleBuilder style = new CssStyleBuilder();
		
		GridLayoutData layoutData = grid.getLayoutData(childIndex);
		
		appendHorizontalAlignment(style, layoutData.getHorizontalAlignment());
		appendVerticalAlignment(style, layoutData.getVerticalAlignment());
		
		return style;
	}
	
	// private methods --------------------------------------------------------
	
	private static int getTotalHorizontalFlex(Grid grid)
	{
		int flex = 0;
		
		for (GridColumn column : grid.getColumns())
		{
			flex += LengthUtils.getFlex(column.getWidth());
		}
		
		return flex;
	}
	
	private static CssStyleBuilder appendHorizontalAlignment(CssStyleBuilder style,
		HorizontalAlignment horizontalAlignment)
	{
		switch (horizontalAlignment)
		{
			case LEFT:
				// CSS default value (assuming direction LTR)
				break;
				
			case MIDDLE:
				style.append(Css.Property.TEXT_ALIGN, Css.Value.CENTER);
				break;
				
			case RIGHT:
				style.append(Css.Property.TEXT_ALIGN, Css.Value.RIGHT);
				break;
				
			// TODO: checkstyle shouldn't mandate this
			default:
				break;
		}
		
		return style;
	}
	
	private static CssStyleBuilder appendVerticalAlignment(CssStyleBuilder style, VerticalAlignment verticalAlignment)
	{
		switch (verticalAlignment)
		{
			case TOP:
				style.append(Css.Property.VERTICAL_ALIGN, Css.Value.TOP);
				break;
				
			case MIDDLE:
				// CSS default value
				break;
				
			case BOTTOM:
				style.append(Css.Property.VERTICAL_ALIGN, Css.Value.BOTTOM);
				break;
				
				// TODO: checkstyle shouldn't mandate this
			default:
				break;
		}
		
		return style;
	}
}
