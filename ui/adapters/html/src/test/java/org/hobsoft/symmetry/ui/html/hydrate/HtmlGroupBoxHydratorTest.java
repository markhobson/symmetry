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
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrators;
import org.hobsoft.symmetry.ui.common.hydrate.CompositeComponentHydrator;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.hobsoft.symmetry.ui.traversal.ContainerVisitor;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlContainerHydrator;
import static org.hobsoft.symmetry.ui.xml.test.hydrate.StubXmlComponentHydrators.stubXmlHierarchicalComponentHydrator;

/**
 * Tests {@code HtmlGroupBoxHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlGroupBoxHydrator
 */
public class HtmlGroupBoxHydratorTest extends AbstractXmlRenderKitTest<GroupBox>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<GroupBox> createRenderKit()
	{
		CompositeComponentHydrator hydrator = new CompositeComponentHydrator();
		
		ContainerVisitor<Box, HydrationContext, HydrationException> boxHydrator = stubXmlContainerHydrator("box");
		hydrator.setDelegate(Box.class, boxHydrator);
		
		hydrator.setDelegate(GroupBox.class, new HtmlGroupBoxHydrator<GroupBox>(boxHydrator));
		
		// TODO: remove unnecessary actual type argument when javac can cope
		hydrator.setDelegate(DummyComponent.class, ComponentHydrators.<DummyComponent>phase(DEHYDRATE,
			stubXmlHierarchicalComponentHydrator(DummyComponent.class, "dummy")));
		
		return StubPhasedUiComponentRenderKit.get(GroupBox.class, hydrator);
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrate() throws HydrationException
	{
		GroupBox groupBox = new GroupBox();
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithTitle() throws HydrationException
	{
		GroupBox groupBox = new GroupBox("a");
		
		String expected = "<fieldset class=\"groupbox\">"
			+ "<legend><label>a</label></legend>"
			+ "<div class=\"grouppanel\">[box][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithChild() throws HydrationException
	{
		GroupBox groupBox = new GroupBox(null, new DummyComponent());
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][dummy][/dummy][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
	
	@Test
	public void dehydrateWithChildren() throws HydrationException
	{
		GroupBox groupBox = new GroupBox(null, new DummyComponent(), new DummyComponent(), new DummyComponent());
		
		String expected = "<fieldset class=\"groupbox\">"
			// TODO: expect no <label/>
			+ "<legend><label></label></legend>"
			+ "<div class=\"grouppanel\">[box][dummy][/dummy][dummy][/dummy][dummy][/dummy][/box]</div>"
			+ "</fieldset>";
		
		assertDehydrate(expected, groupBox);
	}
}
