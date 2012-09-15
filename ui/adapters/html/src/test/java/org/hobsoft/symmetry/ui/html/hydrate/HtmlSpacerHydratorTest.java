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

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Spacer;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlSpacerHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlSpacerHydrator
 */
public class HtmlSpacerHydratorTest extends AbstractXmlRenderKitTest<Spacer>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Spacer> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(Spacer.class, new HtmlSpacerHydrator<Spacer>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrate() throws HydrationException
	{
		assertDehydrate("", new Spacer());
	}
}
