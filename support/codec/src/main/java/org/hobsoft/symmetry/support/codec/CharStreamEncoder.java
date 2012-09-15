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

import java.io.Reader;
import java.io.Writer;

/**
 * Encodes an object into a stream of characters.
 * 
 * @author Mark Hobson
 * @param <X>
 *            the object type that this encoder can encode
 */
public interface CharStreamEncoder<X> extends Encoder<X, Reader>
{
	/**
	 * Encodes the specified object to the specified writer.
	 * 
	 * @param object
	 *            the object to encode
	 * @param writer
	 *            the writer to encode the object to
	 * @throws EncoderException
	 *             if an error occurred encoding the object
	 */
	void encodeTo(X object, Writer writer) throws EncoderException;
}
