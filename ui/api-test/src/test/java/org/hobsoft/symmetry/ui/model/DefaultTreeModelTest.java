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

import static org.junit.Assert.assertNull;

/**
 * Tests {@code DefaultTreeModel}.
 * 
 * @author Mark Hobson
 * @see DefaultTreeModel
 */
public class DefaultTreeModelTest
{
	// fields -----------------------------------------------------------------
	
	private DefaultTreeModel model;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		model = new DefaultTreeModel();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getRootWhenEmpty()
	{
		assertNull(model.getRoot());
	}
}
