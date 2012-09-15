/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/state/HtmlFormStateCodecTest.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import org.hobsoft.symmetry.state.NullStateCodec;
import org.junit.Test;

/**
 * Tests {@code HtmlFormStateCodec}.
 * 
 * @author Mark Hobson
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
