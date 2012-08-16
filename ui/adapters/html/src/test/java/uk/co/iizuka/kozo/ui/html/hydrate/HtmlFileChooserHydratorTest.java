/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlFileChooserHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.activation.DataSource;

import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.ui.html.hydrate.FormHtmlWindowDehydrator.Parameters;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlFileChooserHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlFileChooserHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
