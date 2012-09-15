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

import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Grid;
import org.hobsoft.symmetry.ui.layout.Length;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;

/**
 * Hydrator that dehydrates a {@code Grid} component to an HTML {@code <table/>} tag without using {@code min-width} on
 * {@code <col/>}.
 * <p>
 * This works around issues in Chrome 18, Safari 5 and Opera 11.
 * 
 * @author Mark Hobson
 * @see <a href="http://code.google.com/p/chromium/issues/detail?id=120886">Chrome issue 120886</a>
 * @param <T>
 *            the grid type this visitor can visit
 */
public class CellWidthHtmlGridDehydrator<T extends Grid> extends HtmlGridDehydrator<T>
{
	// TODO: remove once Chrome issue 120886 fixed and in stable
	
	// GridVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, HydrationContext context) throws HydrationException
	{
		// don't pop Lengths yet since we need it in visitChild
		// don't write cols
		
		context.get(Lengths.class).normalize();
		
		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRows(T grid, HydrationContext context) throws HydrationException
	{
		super.endVisitRows(grid, context);
		
		// pop Lengths here instead
		context.pop(Lengths.class);
		
		return VISIT_SIBLINGS;
	}
	
	// HtmlGridDehydrator methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CssStyleBuilder getGridChildCssStyle(T grid, HydrationContext context, int childIndex)
	{
		CssStyleBuilder builder = super.getGridChildCssStyle(grid, context, childIndex);
		
		int columnIndex = grid.getColumnIndex(childIndex);
		int columnSpan = grid.getLayoutData(childIndex).getColumnSpan();
		
		// don't specify column width on spanned cells
		if (columnSpan == 1)
		{
			Lengths columnWidths = context.get(Lengths.class);
			Length columnWidth = columnWidths.getLength(columnIndex);
			boolean flexed = columnWidths.isFlexed();
			
			LengthUtils.appendWidth(builder, columnWidth, flexed);
			
			// only write each column's width once
			columnWidths.clear(columnIndex);
		}
		
		// TODO: column width is not written if there is no unspanned cell within it
		
		return builder;
	}
}
