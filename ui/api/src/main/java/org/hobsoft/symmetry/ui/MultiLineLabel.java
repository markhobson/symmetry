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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class MultiLineLabel extends Label
{
	// TODO: does image make much sense here?
	
	// constructors -----------------------------------------------------------
	
	public MultiLineLabel(Style... styles)
	{
		super(styles);
	}

	public MultiLineLabel(String text, Style... styles)
	{
		super(text, styles);
	}

	public MultiLineLabel(Image image, Style... styles)
	{
		super(image, styles);
	}

	public MultiLineLabel(String text, Image image, Style... styles)
	{
		super(text, image, styles);
	}

	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, MultiLineLabel.class, this, parameter);
	}
}