/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api-test/src/test/java/uk/co/iizuka/kozo/ui/FileChooserTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.test.AbstractComponentTest;

import com.googlecode.jtype.Generic;

/**
 * Tests {@code FileChooser}.
 * 
 * @author Mark Hobson
 * @version $Id: FileChooserTest.java 95528 2011-11-25 19:00:17Z mark@IIZUKA.CO.UK $
 * @see FileChooser
 */
public class FileChooserTest extends AbstractComponentTest<FileChooser>
{
	// AbstractComponentTest methods ------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FileChooser createComponent()
	{
		return new FileChooser();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Generic<FileChooser> getComponentType()
	{
		return Generic.get(FileChooser.class);
	}
}
