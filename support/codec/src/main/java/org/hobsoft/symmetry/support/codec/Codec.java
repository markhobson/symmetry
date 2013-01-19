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

import com.google.common.base.Function;

/**
 * Encodes and decodes objects to and from another object.
 * 
 * @author	Mark Hobson
 * @param	<X>
 * 				the object type that this codec can encode
 * @param	<Y>
 * 				the object type that this codec can decode
 * @see		Encoder
 * @see		Decoder
 */
public interface Codec<X, Y> extends Function<X, Y>
{
	// TODO: replace with Guava's Converter when implemented (see #14)
	
	Function<Y, X> inverse();
}
