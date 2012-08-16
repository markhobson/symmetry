/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/HtmlFormStateCodecTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.state;

import org.junit.Test;

import uk.co.iizuka.kozo.state.NullStateCodec;

/**
 * Tests {@code HtmlFormStateCodec}.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlFormStateCodecTest.java 98223 2012-02-02 11:04:03Z mark@IIZUKA.CO.UK $
 * @see HtmlFormStateCodec
 */
public class HtmlFormStateCodecTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newHtmlFormStateCodec()
	{
		new HtmlFormStateCodec(new NullStateCodec());
	}
}
