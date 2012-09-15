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
 * Convenience base class for all codec exceptions.
 * 
 * @author	Mark Hobson
 */
public abstract class CodecException extends Exception
{
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a codec exception with the specified message.
	 * 
	 * @param	message
	 * 				the message for this exception
	 */
	public CodecException(String message)
	{
		super(message);
	}
}
