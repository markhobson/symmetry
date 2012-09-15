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
package org.hobsoft.symmetry.ui.swing;

import javax.swing.SwingConstants;

import org.hobsoft.symmetry.ui.Orientation;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class SwingUtils
{
	// constructors -----------------------------------------------------------
	
	private SwingUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static int getOrientation(Orientation orient)
	{
		return (orient == Orientation.HORIZONTAL) ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL;
	}
}
