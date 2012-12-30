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
package org.hobsoft.symmetry.http.pool;

import org.hobsoft.symmetry.SymmetryException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class PeerManagerProviderException extends SymmetryException
{
	// constants --------------------------------------------------------------
	
	/**
	 * The Java serialization UID for this class.
	 */
	private static final long serialVersionUID = 5836476041196557338L;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code PeerManagerProviderException} with no message or cause.
	 */
	public PeerManagerProviderException()
	{
		super();
	}
	
	/**
	 * Creates a new {@code PeerManagerProviderException} with the specified message and no cause.
	 * 
	 * @param message
	 *            the message for this exception
	 */
	public PeerManagerProviderException(String message)
	{
		super(message);
	}
	
	/**
	 * Creates a new {@code PeerManagerProviderException} with no message and the specified cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public PeerManagerProviderException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Creates a new {@code PeerManagerProviderException} with the specified message and cause.
	 * 
	 * @param message
	 *            the message for this exception
	 * @param cause
	 *            the cause of this exception
	 */
	public PeerManagerProviderException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
