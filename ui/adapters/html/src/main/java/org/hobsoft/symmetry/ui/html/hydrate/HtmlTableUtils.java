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
