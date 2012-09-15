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
 * Indicates an error occurred when encoding an object.
 * 
 * @author	Mark Hobson
 * @version	$Id: EncoderException.java 66071 2009-10-12 11:32:47Z mark@IIZUKA.CO.UK $
 */
public class EncoderException extends CodecException
{
	// constants --------------------------------------------------------------

	/**
	 * The serial version id.
	 */
	private static final long serialVersionUID = 5342437922597611761L;

	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a encoder exception with the specified message.
	 * 
	 * @param	message
	 * 				the message for this exception
	 */
	public EncoderException(String message)
	{
		super(message);
	}
}
