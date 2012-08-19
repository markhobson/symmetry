/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/StreamUtils.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec.lang;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Provides utility methods for working with streams.
 * 
 * @author Mark Hobson
 * @version $Id: StreamUtils.java 75587 2010-08-02 20:41:05Z mark@IIZUKA.CO.UK $
 */
final class StreamUtils
{
	// constants --------------------------------------------------------------
	
	private static final int BUFFER_SIZE = 1024 * 4;

	// constructors -----------------------------------------------------------
	
	private StreamUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static byte[] toBytes(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int n;
		
		while ((n = in.read(buffer)) != -1)
		{
			out.write(buffer, 0, n);
		}
		
		return out.toByteArray();
	}
	
	public static char[] toChars(Reader reader) throws IOException
	{
		CharArrayWriter writer = new CharArrayWriter();
		
		char[] buffer = new char[BUFFER_SIZE];
		int n;
		
		while ((n = reader.read(buffer)) != -1)
		{
			writer.write(buffer, 0, n);
		}
		
		return writer.toCharArray();
	}
}
