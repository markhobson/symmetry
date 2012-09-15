/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.codec.test;

import org.hobsoft.symmetry.support.codec.Codec;

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