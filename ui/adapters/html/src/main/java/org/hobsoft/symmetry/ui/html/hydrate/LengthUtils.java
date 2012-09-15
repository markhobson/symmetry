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

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.hobsoft.symmetry.css.Css;
import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.layout.Length.Unit;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class LengthUtils
{
	// constructors -----------------------------------------------------------
	
	private LengthUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void normalize(List<Length> lengths)
	{
		int totalFlex = getTotalFlex(lengths);
		int count = lengths.size();
		
		for (ListIterator<Length> iterator = lengths.listIterator(); iterator.hasNext(); )
		{
			int index = iterator.nextIndex();
			Length length = iterator.next();
			
			length = normalize(length, totalFlex, index, count);
			
			iterator.set(length);
		}
	}
	
	// TODO: privatise when no longer used by AbstractHtmlBoxDehydrator
	public static int getPercentage(int flex, int totalFlex, int childIndex, int childCount)
	{
		double length = (100d * flex) / totalFlex;
		
		// round down unless last, then round up
		if (childIndex < childCount - 1)
		{
			length = Math.floor(length);
		}
		else
		{
			length = Math.ceil(length);
		}

		return (int) length;
	}
	
	public static int getFlex(Length length)
	{
		return isFlex(length) ? length.getValue() : 0;
	}
	
	public static CssStyleBuilder appendWidth(CssStyleBuilder style, Length width, boolean flexed)
	{
		if (width != null)
		{
			// use min-width rather than width for fixed columns in the presence of flexed columns, otherwise column
			// will shrink to smallest possible size
			boolean fixed = (width.getUnit() == Unit.PIXELS && flexed);
			Css.Property property = fixed ? Css.Property.MIN_WIDTH : Css.Property.WIDTH;
			
			style.append(property, width.getValue(), toCssUnit(width.getUnit()));
		}
		
		return style;
	}
	
	// private methods --------------------------------------------------------
	
	private static Length normalize(Length length, int totalFlex, int childIndex, int childCount)
	{
		if (isFlex(length))
		{
			// ideally use HTML's relative length syntax here but it's not supported by browsers
			int percentage = getPercentage(length.getValue(), totalFlex, childIndex, childCount);
			
			length = Length.percentage(percentage);
		}
		
		return length;
	}
	
	private static int getTotalFlex(Collection<Length> lengths)
	{
		int flex = 0;
		
		for (Length length : lengths)
		{
			flex += getFlex(length);
		}
		
		return flex;
	}
	
	private static boolean isFlex(Length length)
	{
		return (length != null) && (length.getUnit() == Unit.FLEX);
	}

	private static Css.Unit toCssUnit(Length.Unit unit)
	{
		Css.Unit cssUnit;
		
		switch (unit)
		{
			case PIXELS:
				cssUnit = Css.Unit.PX;
				break;
				
			case PERCENTAGE:
				cssUnit = Css.Unit.PERCENTAGE;
				break;
				
			case FLEX:
			default:
				throw new IllegalArgumentException("Cannot represent length unit in CSS: " + unit);
		}
		
		return cssUnit;
	}
}
