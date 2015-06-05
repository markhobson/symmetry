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
import java.io.Writer;
import java.util.Map;

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
	 * Configures the specified component for the specified state.
	 * 
	 * @param component
	 *            the component to absorb into
	 * @param state
	 *            the state to absorb
	 */
	void absorb(T component, Map<String, String[]> state);

	/**
	 * Serialises the specified component to the specified writer.
	 * <p>
	 * The character encoding used by the writer should be the {@code charset} parameter of the media type returned by
	 * {@link #getContentType()}.
	 * 
	 * @param component
	 *            the component to reflect
	 * @param writer
	 *            the writer to reflect into
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ReflectorException
	 *             if an error occurs during reflection
	 */
	void reflect(T component, Writer writer) throws IOException, ReflectorException;
}
