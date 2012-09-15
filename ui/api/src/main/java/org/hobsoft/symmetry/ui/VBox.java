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

/**
 * A container that lays its children out along the y-axis. An {@code VBox} is simply a {@code Box} with
 * vertical orientation.
 * 
 * @author Mark Hobson
 * @see Box
 */
public class VBox extends Box
{
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an empty box with vertical orientation.
	 */
	public VBox()
	{
		this(EMPTY_COMPONENT_ARRAY);
	}
	
	/**
	 * Creates a box with vertical orientation and the specified children.
	 * 
	 * @param children
	 *            the components to be added to the box
	 */
	public VBox(Component... children)
	{
		super(Orientation.VERTICAL, children);
	}
}
