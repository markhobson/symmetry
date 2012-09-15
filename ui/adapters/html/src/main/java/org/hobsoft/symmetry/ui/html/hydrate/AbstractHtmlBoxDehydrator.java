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

import org.hobsoft.symmetry.css.Css;
import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.css.CssStyleBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.Orientation;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.layout.Length;
import org.hobsoft.symmetry.ui.traversal.NullContainerVisitor;

/**
 * Base hydrator for dehydrating a {@code Box} component to HTML.
 * 
 * @author Mark Hobson
 * @see Box
 * @param <T>
 *            the box type this visitor can visit
 */
public abstract class AbstractHtmlBoxDehydrator<T extends Box>
	extends NullContainerVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getBoxCssClass(Box box)
	{
		return new CssClassBuilder(isHorizontal(box) ? "hbox" : "vbox");
	}
	
	protected CssClassBuilder getBoxChildCssClass(Box box, int childIndex)
	{
		return new CssClassBuilder(isHorizontal(box) ? "hbox-child" : "vbox-child");
	}
	
	protected CssStyleBuilder getBoxChildCssStyle(Box box, int childIndex)
	{
		Length length = box.getLayoutData(childIndex).getLength();
		
		if (LengthUtils.getFlex(length) == 0)
		{
			return null;
		}
		
		int percentage = getPercentage(box, childIndex);
		
		CssStyleBuilder builder = new CssStyleBuilder();
		builder.append(isHorizontal(box) ? Css.Property.WIDTH : Css.Property.HEIGHT, percentage, Css.Unit.PERCENTAGE);
		return builder;
	}
	
	protected static boolean isHorizontal(Box box)
	{
		return box.getOrientation() == Orientation.HORIZONTAL;
	}
	
	protected static boolean isEmpty(Box box)
	{
		return box.getComponentCount() == 0;
	}
	
	// private methods --------------------------------------------------------
	
	private static int getPercentage(Box box, int childIndex)
	{
		Length length = box.getLayoutData(childIndex).getLength();
		int flex = LengthUtils.getFlex(length);
		int totalFlex = BoxUtils.getTotalFlex(box);
		int childCount = box.getComponentCount();
		
		return LengthUtils.getPercentage(flex, totalFlex, childIndex, childCount);
	}
}
