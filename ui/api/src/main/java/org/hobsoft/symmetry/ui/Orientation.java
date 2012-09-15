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
 * A type-safe enumeration of component orientations.
 * 
 * @author Mark Hobson
 * @see org.hobsoft.symmetry.ui.Box#setOrientation(Orientation)
 */
public enum Orientation
{
	// constants --------------------------------------------------------------
	
	/**
	 * Instance that orients a component along the x-axis.
	 */
	HORIZONTAL,
	
	/**
	 * Instance that orients a component along the y-axis.
	 */
	VERTICAL;
}
