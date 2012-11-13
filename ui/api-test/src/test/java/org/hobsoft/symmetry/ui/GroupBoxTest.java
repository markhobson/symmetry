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

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code GroupBox}.
 * 
 * @author Mark Hobson
 * @see GroupBox
 */
public class GroupBoxTest extends AbstractComponentTest<GroupBox>
{
	// fields -----------------------------------------------------------------
	
	private GroupBox groupBox;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		groupBox = getComponent();
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void setTitle()
	{
		groupBox.setTitle("a");
		
		assertEquals("a", groupBox.getTitle());
	}
	
	@Test
	public void setTitleWithNull()
	{
		groupBox.setTitle(null);
		
		assertEquals("", groupBox.getTitle());
	}
	
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GroupBox createComponent()
	{
		return new GroupBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeToken<GroupBox> getComponentType()
	{
		return TypeToken.of(GroupBox.class);
	}
}
