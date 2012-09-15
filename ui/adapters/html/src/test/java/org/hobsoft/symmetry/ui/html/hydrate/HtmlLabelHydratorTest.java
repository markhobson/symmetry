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
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Image;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

/**
 * Tests {@code HtmlLabelHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlLabelHydrator
 */
public class HtmlLabelHydratorTest extends AbstractXmlRenderKitTest<Label>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<Label> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(Label.class, new HtmlLabelHydrator<Label>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateLabel() throws HydrationException
	{
		Label label = new Label();
		
		assertDehydrate("", label);
	}
	
	@Test
	public void dehydrateLabelWithText() throws HydrationException
	{
		Label label = new Label("text");
		
		assertDehydrate("<p class=\"label\">text</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlText() throws HydrationException
	{
		Label label = new Label("<a>link</a>");
		
		assertDehydrate("<p class=\"label\">&lt;a&gt;link&lt;/a&gt;</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndHeading1Style() throws HydrationException
	{
		Label label = new Label("text", Style.HEADING1);
		
		assertDehydrate("<h1 class=\"label\">text</h1>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndHeading2Style() throws HydrationException
	{
		Label label = new Label("text", Style.HEADING2);
		
		assertDehydrate("<h2 class=\"label\">text</h2>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndHeading3Style() throws HydrationException
	{
		Label label = new Label("text", Style.HEADING3);
		
		assertDehydrate("<h3 class=\"label\">text</h3>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndWarningStyle() throws HydrationException
	{
		Label label = new Label("text", Style.WARNING);
		
		assertDehydrate("<p class=\"label label-warning\">text</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndErrorStyle() throws HydrationException
	{
		Label label = new Label("text", Style.ERROR);
		
		assertDehydrate("<p class=\"label label-error\">text</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHttpImage() throws HydrationException
	{
		Label label = new Label(new Image("http://a/b"));
		
		assertDehydrate("<p class=\"label\"><img src=\"http://a/b\"/></p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHttpsImage() throws HydrationException
	{
		Label label = new Label(new Image("https://a/b"));
		
		assertDehydrate("<p class=\"label\"><img src=\"https://a/b\"/></p>", label);
	}
	
	@Test
	public void dehydrateLabelWithWebResourceImage() throws HydrationException
	{
		Label label = new Label(new Image("jar:file:/a!/META-INF/resources/b"));
		
		assertDehydrate("<p class=\"label\"><img src=\"b\"/></p>", label);
	}
	
	@Test
	public void dehydrateLabelWithTextAndToolTip() throws HydrationException
	{
		Label label = new Label("text");
		label.setToolTip("toolTip");
		
		assertDehydrate("<p class=\"label\" title=\"toolTip\">text</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithLabelFor() throws HydrationException
	{
		Component component = new DummyComponent();
		getIdEncoder().setEncodedObject(component, "1");
		
		Label label = new Label("text");
		label.setLabelFor(component);
		
		assertDehydrate("<label class=\"label\" for=\"1\">text</label>", label);
	}
}
