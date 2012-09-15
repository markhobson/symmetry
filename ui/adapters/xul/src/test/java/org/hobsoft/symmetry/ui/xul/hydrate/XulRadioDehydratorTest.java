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
package org.hobsoft.symmetry.ui.xul.hydrate;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulRadioDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulRadioDehydrator
 */
public class XulRadioDehydratorTest extends AbstractXmlRenderKitTest<Radio>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Radio> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Radio.class, new XulRadioDehydrator<Radio>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateRadio() throws HydrationException
	{
		Radio radio = new Radio();
		
		assertDehydrate("<radio/>", radio);
	}
	
	@Test
	public void dehydrateRadioWithText() throws HydrationException
	{
		Radio radio = new Radio("text");
		
		assertDehydrate("<radio label=\"text\"/>", radio);
	}
	
	@Test
	public void dehydrateRadioWhenSelected() throws HydrationException
	{
		Radio radio = new Radio();
		radio.setSelected(true);
		
		assertDehydrate("<radio checked=\"true\"/>", radio);
	}
}
