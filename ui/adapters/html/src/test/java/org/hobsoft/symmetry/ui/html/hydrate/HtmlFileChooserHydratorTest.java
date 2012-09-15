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

import javax.activation.DataSource;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.FileChooser;
import org.hobsoft.symmetry.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import org.hobsoft.symmetry.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import org.hobsoft.symmetry.xml.test.hydrate.AbstractXmlRenderKitTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlFileChooserHydrator}.
 * 
 * @author Mark Hobson
 * @see HtmlFileChooserHydrator
 */
public class HtmlFileChooserHydratorTest extends AbstractXmlRenderKitTest<FileChooser>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<FileChooser> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(FileChooser.class, new HtmlFileChooserHydrator<FileChooser>());
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateInitializeSetsPost() throws HydrationException
	{
		dehydrate(new FileChooser());
		
		assertTrue("Expected post", getDehydrationContext().get(Parameters.class, new Parameters()).isPost());
	}
	
	@Test
	public void dehydrateInitializeSetsMultipartForm() throws HydrationException
	{
		dehydrate(new FileChooser());
		
		assertTrue("Expected multipart form", getDehydrationContext().get(Parameters.class, new Parameters())
			.isMultipartForm());
	}
	
	@Test
	public void dehydrateFileChooser() throws HydrationException
	{
		FileChooser fileChooser = new FileChooser();
		
		assertDehydrate("<input class=\"filechooser\" type=\"file\"/>", fileChooser);
	}

	@Test
	public void rehydrateWithFileParameter() throws HydrationException
	{
		DataSource file = new DummyDataSource();
		
		FileChooser fileChooser = new FileChooser();
		getStateCodec().setEncodedBean(fileChooser, "1");
		setEncodedState(createEncodedState("1", file));
		
		rehydrate(fileChooser);
		
		assertEquals(file, fileChooser.getFile());
	}
	
	@Test
	public void rehydrateWithNoFileParameter() throws HydrationException
	{
		DataSource file = new DummyDataSource();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setFile(file);
		getStateCodec().setEncodedBean(fileChooser, "1");
		
		rehydrate(fileChooser);
		
		assertEquals(file, fileChooser.getFile());
	}

	@Test
	public void rehydrateWithUnvaluedFileParameter() throws HydrationException
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setFile(new DummyDataSource());
		getStateCodec().setEncodedBean(fileChooser, "1");
		setEncodedState(createEncodedState("1"));
		
		rehydrate(fileChooser);
		
		assertNull(fileChooser.getFile());
	}

	@Test(expected = HydrationException.class)
	public void rehydrateWithMultivaluedFileParameter() throws HydrationException
	{
		FileChooser fileChooser = new FileChooser();
		getStateCodec().setEncodedBean(fileChooser, "1");
		setEncodedState(createEncodedState("1", new DummyDataSource(), new DummyDataSource()));
		
		rehydrate(fileChooser);
	}
}
