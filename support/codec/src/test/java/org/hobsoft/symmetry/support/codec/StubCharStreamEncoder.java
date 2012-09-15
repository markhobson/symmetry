/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/StubCharStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.IOException;
import java.io.Writer;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubCharStreamEncoder.java 75577 2010-08-02 17:54:34Z mark@IIZUKA.CO.UK $
 */
public class StubCharStreamEncoder extends AbstractCharStreamEncoder<String>
{
	// CharStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(String string, Writer writer) throws EncoderException
	{
		try
		{
			writer.write(string);
		}
		catch (IOException exception)
		{
			throw new EncoderException(exception.getMessage());
		}
	}
}
