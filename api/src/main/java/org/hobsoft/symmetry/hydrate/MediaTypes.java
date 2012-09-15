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
package org.hobsoft.symmetry.hydrate;

/**
 * Utility methods for working with media types.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7">Media Types</a>
 */
final class MediaTypes
{
	// constructors -----------------------------------------------------------
	
	private MediaTypes()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static String getParameterValue(String mediaType, String parameterName)
	{
		Utilities.checkNotNull(mediaType, "mediaType");
		Utilities.checkNotNull(parameterName, "parameterName");
		
		if (parameterName.length() == 0)
		{
			throw new IllegalArgumentException("parameterName cannot be empty");
		}
		
		String[] parameters = mediaType.split(";");

		for (int i = 1; i < parameters.length; i++)
		{
			String[] tokens = parameters[i].split("=");
			
			if (parameterName.equals(tokens[0].trim()))
			{
				return tokens.length > 1 ? unquote(tokens[1].trim()) : "";
			}
		}
		
		return null;
	}
	
	// private methods --------------------------------------------------------
	
	private static String unquote(String string)
	{
		if (string.length() >= 2 && string.startsWith("\"") && string.endsWith("\""))
		{
			return string.substring(1, string.length() - 1);
		}
		
		return string;
	}
}
