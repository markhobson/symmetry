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
 * Decodes an object into another object.
 * 
 * @author	Mark Hobson
 * @param	<X>
 * 				the object type that this decoder can decode
 * @param	<Y>
 * 				the object type that this decoder decodes to
 * @see		Encoder
 */
public interface Decoder<X, Y>
{
	/**
	 * Decodes the specified object into another object.
	 *
	 * @param	object
	 * 				the object to decode
	 * @return	the decoded object
	 * @throws	DecoderException
	 * 				if an error occurred decoding the object
	 */
	Y decode(X object) throws DecoderException;
}
