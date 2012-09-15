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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import javax.swing.SwingConstants;

import org.hobsoft.symmetry.ui.Orientation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class SwingUtilsTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void nonInstantiable()
	{
		Constructor<?>[] constructors = SwingUtils.class.getConstructors();
		
		for (int i = 0; i < constructors.length; i++)
		{
			assertTrue(Modifier.isPrivate(constructors[i].getModifiers()));
		}
	}
	
	@Test
	public void getOrientationHorizontal()
	{
		assertEquals(SwingConstants.HORIZONTAL, SwingUtils.getOrientation(Orientation.HORIZONTAL));
	}
	
	@Test
	public void getOrientationVertical()
	{
		assertEquals(SwingConstants.VERTICAL, SwingUtils.getOrientation(Orientation.VERTICAL));
	}
}
