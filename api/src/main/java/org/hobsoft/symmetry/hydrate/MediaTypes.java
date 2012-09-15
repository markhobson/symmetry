/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/MediaTypes.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
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
