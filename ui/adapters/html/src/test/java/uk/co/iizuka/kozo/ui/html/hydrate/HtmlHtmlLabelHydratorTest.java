/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlHtmlLabelHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import org.junit.Ignore;
import org.junit.Test;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.HtmlLabel;
import uk.co.iizuka.kozo.ui.common.test.hydrate.StubPhasedUiComponentRenderKit;
import uk.co.iizuka.kozo.xml.test.hydrate.AbstractXmlRenderKitTest;

/**
 * Tests {@code HtmlHtmlLabelHydrator}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlHtmlLabelHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see HtmlHtmlLabelHydrator
 */
public class HtmlHtmlLabelHydratorTest extends AbstractXmlRenderKitTest<HtmlLabel>
{
	// AbstractRenderKitTest methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ComponentRenderKit<HtmlLabel> createRenderKit()
	{
		return StubPhasedUiComponentRenderKit.get(HtmlLabel.class, new HtmlHtmlLabelHydrator<HtmlLabel>());
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void dehydrateLabelWithText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("text");
		
		assertDehydrate("<p class=\"label\">text</p>", label);
	}

	@Test
	public void dehydrateLabelWithHtmlElementText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<a>link</a>");
		
		assertDehydrate("<p class=\"label\"><a>link</a></p>", label);
	}

	@Test
	public void dehydrateLabelWithHtmlElementAndAttributeText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<a href='x'>link</a>");
		
		assertDehydrate("<p class=\"label\"><a href=\"x\">link</a></p>", label);
	}

	@Test
	public void dehydrateLabelWithHtmlElementAndUnsafeAttributeText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<a onclick='x'>link</a>");
		
		assertDehydrate("<p class=\"label\">&lt;a onclick='x'&gt;link&lt;/a&gt;</p>", label);
	}

	// TODO: don't expand self-closing tags
	@Ignore
	@Test
	public void dehydrateLabelWithUnsafeHtmlSelfClosingElementText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<script/>");
		
		assertDehydrate("<p class=\"label\">&lt;script/&gt;</p>", label);
	}

	@Test
	public void dehydrateLabelWithUnsafeHtmlElementText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<script>x</script>");
		
		assertDehydrate("<p class=\"label\">&lt;script&gt;x&lt;/script&gt;</p>", label);
	}

	@Test
	public void dehydrateLabelWithUnsafeHtmlElementAndAttributeText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<script language='a'>x</script>");
		
		assertDehydrate("<p class=\"label\">&lt;script language='a'&gt;x&lt;/script&gt;</p>", label);
	}

	// TODO: remove superfluous xmlns
	@Ignore
	@Test
	public void dehydrateLabelWithHtmlElementAndHtmlNamespaceText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<a xmlns='http://www.w3.org/1999/xhtml'>link</a>");
		
		assertDehydrate("<p class=\"label\"><a>link</a></p>", label);
	}
	
	// TODO: escape rather than filter non-HTML elements
	@Ignore
	@Test
	public void dehydrateLabelWithNonHtmlElementText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<a xmlns='http://www.w3.org/2000/svg'>link</a>");
		
		assertDehydrate("<p class=\"label\">&lt;a xmlns='http://www.w3.org/2000/svg'&gt;link&lt;/a&gt;</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlEntityReferenceText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("&amp;");
		
		assertDehydrate("<p class=\"label\">&amp;</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlCharacterEntityReferenceText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("&nbsp;");
		
		assertDehydrate("<p class=\"label\">&nbsp;</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlDocumentText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<html><body>a</body></html>");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
	
	// TODO: ignore trailing whitespace
	@Ignore
	@Test
	public void dehydrateLabelWithHtmlDocumentAndTrailingNewlineText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<html><body>a</body></html>\n");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlDocumentAndDoctypeWithExternalIdText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" "
				+ "\"http://www.w3.org/TR/html4/strict.dtd\">"
			+ "<html><body>a</body></html>");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlDocumentAndDoctypeText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<!DOCTYPE html><html><body>a</body></html>");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
	
	@Test
	public void dehydrateLabelWithHtmlDocumentAndProcessingInstructionText() throws HydrationException
	{
		HtmlLabel label = new HtmlLabel("<?xml version='1.0'?><html><body>a</body></html>");
		
		assertDehydrate("<p class=\"label\">a</p>", label);
	}
}
