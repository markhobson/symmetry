/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/StubCharStreamCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StubCharStreamCodec extends StubCharStreamEncoder implements CharStreamCodec<String>
{
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String decode(Reader reader) throws DecoderException
	{
		try
		{
			return IOUtils.toString(reader);
		}
		catch (IOException exception)
		{
			throw new DecoderException(exception.getMessage());
		}
	}
}
