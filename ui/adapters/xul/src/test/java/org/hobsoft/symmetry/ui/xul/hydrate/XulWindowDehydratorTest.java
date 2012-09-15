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
import org.hobsoft.symmetry.ui.Window;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulWindowDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulWindowDehydrator
 */
public class XulWindowDehydratorTest extends AbstractXmlRenderKitTest<Window>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Window> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Window.class, new XulWindowDehydrator<Window>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateWindow() throws HydrationException
	{
		Window window = new Window();
		
		String expected = "<?xml version=\"1.0\" ?>"
			+ "<window xmlns=\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\"></window>";
		
		assertDehydrate(expected, window);
	}
	
	@Test
	public void dehydrateWindowWithTitle() throws HydrationException
	{
		Window window = new Window("a");
		
		String expected = "<?xml version=\"1.0\" ?>"
			+ "<window xmlns=\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\" title=\"a\"></window>";
		
		assertDehydrate(expected, window);
	}
}
