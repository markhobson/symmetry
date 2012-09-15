/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTableUtils.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.layout.Length.Unit;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeStyle;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTableUtils.java 100106 2012-03-30 16:22:57Z mark@IIZUKA.CO.UK $
 */
final class HtmlTableUtils
{
	// constructors -----------------------------------------------------------
	
	private HtmlTableUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void writeCols(XMLStreamWriter out, List<Length> columnWidths) throws XMLStreamException
	{
		int columnCount = columnWidths.size();
		int lastConstrainedColumnIndex = -1;
		boolean flexed = false;
		
		// first pass to check whether any columns are constrained and whether any are flexed
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
		{
			Length columnWidth = columnWidths.get(columnIndex);
			
			if (columnWidth != null)
			{
				lastConstrainedColumnIndex = columnIndex;
				
				Unit columnWidthUnit = columnWidth.getUnit();
				
				if (columnWidthUnit == Unit.PERCENTAGE || columnWidthUnit == Unit.FLEX)
				{
					flexed = true;
				}
			}
		}
		
		if (lastConstrainedColumnIndex == -1)
		{
			return;
		}
		
		out.writeStartElement("colgroup");
		
		// only write up to last constrained column, if any
		for (int columnIndex = 0; columnIndex <= lastConstrainedColumnIndex; columnIndex++)
		{
			Length columnWidth = columnWidths.get(columnIndex);
			
			out.writeEmptyElement("col");
			writeStyle(out, getColStyle(columnWidth, flexed));
		}
		
		// colgroup
		out.writeEndElement();
	}
	
	// private methods --------------------------------------------------------
	
	private static CssStyleBuilder getColStyle(Length columnWidth, boolean flexed)
	{
		CssStyleBuilder style = new CssStyleBuilder();
		
		LengthUtils.appendWidth(style, columnWidth, flexed);
		
		return style;
	}
}
