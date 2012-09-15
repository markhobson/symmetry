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
package org.hobsoft.symmetry.support.codec;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class Encoders
{
	// constructors -----------------------------------------------------------
	
	private Encoders()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	// TODO: compound

	public static <X, Y> Encoder<X, Y> notNull(Encoder<X, Y> encoder)
	{
		return Codecs.notNull(Codecs.adapt(encoder));
	}
	
	public static <X, Y> Encoder<X, Y> adapt(Decoder<X, Y> decoder)
	{
		return Codecs.inverse(Codecs.adapt(decoder));
	}
}
