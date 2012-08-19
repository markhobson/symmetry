/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/test/ABCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.test;

import uk.co.iizuka.common.codec.DecoderException;
import uk.co.iizuka.common.codec.EncoderException;

/**
 * Codec that encodes <code>A</code> to <code>B</code> and vice-versa, for use by tests.
 * 
 * @author	Mark Hobson
 * @version	$Id: ABCodec.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public class ABCodec extends AbstractTestCodec<A, B>
{
	// Codec methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public B encode(A a) throws EncoderException
	{
		return new B(a.getId());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public A decode(B b) throws DecoderException
	{
		return new A(b.getId());
	}
}
