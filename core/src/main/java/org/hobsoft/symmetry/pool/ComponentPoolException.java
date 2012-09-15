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
package org.hobsoft.symmetry.pool;

import org.hobsoft.symmetry.KozoException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ComponentPoolException extends KozoException
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 5836476041196557338L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code ComponentPoolException} with no message or cause.
	 */
	public ComponentPoolException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public ComponentPoolException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public ComponentPoolException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code ComponentPoolException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public ComponentPoolException(String message, Throwable cause)
	{
		super(message, cause);
	}
}