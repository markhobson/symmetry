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

import com.googlecode.jtype.Generic;

/**
 * Tests {@code CheckBox}.
 * 
 * @author Mark Hobson
 * @see CheckBox
 */
public class CheckBoxTest extends AbstractComponentTest<CheckBox>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CheckBox createComponent()
	{
		return new CheckBox();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<CheckBox> getComponentType()
	{
		return Generic.get(CheckBox.class);
	}
}
