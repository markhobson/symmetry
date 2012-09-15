/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/test/BCCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.test;

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes <code>B</code> to <code>C</code> and vice-versa, for use by tests.
 * 
 * @author	Mark Hobson
 * @version	$Id: BCCodec.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public class BCCodec extends AbstractTestCodec<B, C>
{
	// Codec methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public C encode(B a) throws EncoderException
	{
		return new C(a.getId());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public B decode(C c) throws DecoderException
	{
		return new B(c.getId());
	}
}
