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
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubUiComponentRenderKit;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code XulLabelDehydrator}.
 * 
 * @author Mark Hobson
 * @see XulLabelDehydrator
 */
public class XulLabelDehydratorTest extends AbstractXmlRenderKitTest<Label>
{
	// TODO: more tests
	
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Label> createRenderKit()
	{
		return StubUiComponentRenderKit.get(Label.class, new XulLabelDehydrator<Label>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateLabel() throws HydrationException
	{
		Label label = new Label();
		
		assertDehydrate("<label/>", label);
	}
	
	@Test
	public void dehydrateLabelWithText() throws HydrationException
	{
		Label label = new Label("text");
		
		assertDehydrate("<label value=\"text\"/>", label);
	}
	
	@Test
	public void dehydrateLabelWithToolTip() throws HydrationException
	{
		Label label = new Label();
		label.setToolTip("a");
		
		assertDehydrate("<label tooltiptext=\"a\"/>", label);
	}
}
