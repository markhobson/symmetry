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
import org.hobsoft.symmetry.ui.MultiLineLabel;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlMultiLineLabelHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlMultiLineLabelHydrator
 */
public class HtmlMultiLineLabelHydratorTest extends AbstractXmlRenderKitTest<MultiLineLabel>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<MultiLineLabel> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(MultiLineLabel.class,
			new HtmlMultiLineLabelHydrator<MultiLineLabel>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateWithText() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("a");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
	
	@Test
	public void dehydrateWithLineFeed() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("a\nb");
		
		assertDehydrate("<p class=\"label\">a<br/>b</p>", label);
	}
	
	@Test
	public void dehydrateWithLeadingLineFeed() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("\na");
		
		assertDehydrate("<p class=\"label\"><br/>a</p>", label);
	}
	
	@Test
	public void dehydrateWithTrailingLineFeed() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("a\n");
		
		assertDehydrate("<p class=\"label\">a<br/></p>", label);
	}
	
	@Test
	public void dehydrateWithCarriageReturn() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("a\rb");
		
		assertDehydrate("<p class=\"label\">a<br/>b</p>", label);
	}
	
	@Test
	public void dehydrateWithCarriageReturnLineFeed() throws HydrationException
	{
		MultiLineLabel label = new MultiLineLabel("a\r\nb");
		
		assertDehydrate("<p class=\"label\">a<br/>b</p>", label);
	}
}