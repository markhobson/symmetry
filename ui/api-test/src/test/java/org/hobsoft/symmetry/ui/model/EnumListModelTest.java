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
package org.hobsoft.symmetry.ui.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code EnumListModel}.
 * 
 * @author Mark Hobson
 * @see EnumListModel
 */
public class EnumListModelTest
{
	// types ------------------------------------------------------------------
	
	private enum TestEnum
	{
		A,
		B,
		C;
	}
	
	// fields -----------------------------------------------------------------
	
	private EnumListModel<TestEnum> model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new EnumListModel<TestEnum>(TestEnum.class);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getItem()
	{
		assertEquals("item", TestEnum.A, model.getItem(0));
	}
	
	@Test
	public void getItemCount()
	{
		assertEquals("item count", 3, model.getItemCount());
	}
}
