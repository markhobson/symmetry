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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hobsoft.symmetry.ui.layout.Length;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class Lengths
{
	// fields -----------------------------------------------------------------
	
	private final List<Length> lengths;
	
	private boolean flexed;
	
	// constructors -----------------------------------------------------------
	
	public Lengths()
	{
		lengths = new ArrayList<Length>();
		flexed = false;
	}

	// public methods ---------------------------------------------------------
	
	public void add(Length length)
	{
		lengths.add(length);
		
		if (length != null && length.getUnit() == Length.Unit.FLEX)
		{
			flexed = true;
		}
	}
	
	public Length getLength(int index)
	{
		return lengths.get(index);
	}
	
	public List<Length> getLengths()
	{
		return Collections.unmodifiableList(lengths);
	}
	
	public void clear(int index)
	{
		lengths.set(index, null);
	}
	
	public boolean isFlexed()
	{
		return flexed;
	}

	public Lengths normalize()
	{
		LengthUtils.normalize(lengths);
		
		return this;
	}
}
