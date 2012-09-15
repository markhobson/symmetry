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

import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes <code>B</code> to <code>C</code> and vice-versa, for use by tests.
 * 
 * @author	Mark Hobson
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
