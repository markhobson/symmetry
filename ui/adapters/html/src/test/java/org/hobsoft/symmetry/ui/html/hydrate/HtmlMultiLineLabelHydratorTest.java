/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlMultiLineLabelHydratorTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
 * @version $Id: HtmlMultiLineLabelHydratorTest.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
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
