/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/test/java/uk/co/iizuka/common/codec/test/AbstractTestCodec.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.test;

import uk.co.iizuka.common.codec.Codec;

/**
 * Base for test codecs.
 * 
 * @author	Mark Hobson
 * @version	$Id: AbstractTestCodec.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 */
public abstract class AbstractTestCodec<X, Y> implements Codec<X, Y>
{
	// Object methods ---------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return getClass().hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		return (object != null) && getClass().equals(object.getClass());
	}
}
