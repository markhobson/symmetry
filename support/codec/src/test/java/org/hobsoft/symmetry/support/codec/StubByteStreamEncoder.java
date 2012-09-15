/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/StubByteStreamEncoder.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StubByteStreamEncoder extends AbstractByteStreamEncoder<String>
{
	// ByteStreamEncoder methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void encodeTo(String string, OutputStream out) throws EncoderException
	{
		try
		{
			out.write(string.getBytes("UTF-8"));
		}
		catch (IOException exception)
		{
			throw new EncoderException(exception.getMessage());
		}
	}
}
