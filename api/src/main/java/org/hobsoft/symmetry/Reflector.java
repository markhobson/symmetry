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
package org.hobsoft.symmetry;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Defines an API to serialise UI components.
 * 
 * @param <T>
 *            the base component type
 */
public interface Reflector<T>
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Gets the base component type that this reflector can reflect.
	 * 
	 * @return the component type
	 */
	Class<T> getComponentType();

	/**
	 * Gets the content type that this reflector reflects to.
	 * 
	 * @return the content type
	 */
	String getContentType();

	/**
	 * Serialises the specified component to the specified output stream.
	 * 
	 * @param component
	 *            the component to reflect
	 * @param outputStream
	 *            the output stream to reflect into
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ReflectorException
	 *             if an error occurs during reflection
	 */
	void reflect(T component, OutputStream outputStream) throws IOException, ReflectorException;
}
