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
package org.hobsoft.symmetry.ui;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Image}.
 * 
 * @author Mark Hobson
 * @see Image
 */
public class ImageTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newImageWithAbsoluteDataUriString() throws FileNotFoundException
	{
		URL url = getResource("images/a.txt");
		Image image = new Image(url.toString());
		
		assertImageEquals(url, "a", image);
	}
	
	@Test
	public void newImageWithRelativeDataUriString() throws FileNotFoundException
	{
		Image image = new Image("images/a.txt");
		
		assertImageEquals(getResource("images/a.txt"), "a", image);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newImageWithRelativeDataUriStringWhenMissing()
	{
		new Image("images/b.txt");
	}
	
	@Test(expected = NullPointerException.class)
	public void newImageWithNullDataUriString()
	{
		new Image((String) null);
	}
	
	@Test
	public void newImageWithAbsoluteDataUri() throws FileNotFoundException, URISyntaxException
	{
		URL url = getResource("images/a.txt");
		Image image = new Image(url.toURI());
		
		assertImageEquals(url, "a", image);
	}
	
	@Test
	public void newImageWithRelativeDataUri() throws FileNotFoundException, URISyntaxException
	{
		Image image = new Image(new URI("images/a.txt"));
		
		assertImageEquals(getResource("images/a.txt"), "a", image);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newImageWithRelativeDataUriWhenMissing() throws URISyntaxException
	{
		new Image(new URI("images/b.txt"));
	}
	
	@Test(expected = NullPointerException.class)
	public void newImageWithNullDataUri()
	{
		new Image((URI) null);
	}
	
	@Test
	public void newImageWithDataUrl() throws FileNotFoundException
	{
		URL url = getResource("images/a.txt");
		Image image = new Image(url);
		
		assertImageEquals(url, "a", image);
	}
	
	@Test(expected = NullPointerException.class)
	public void newImageWithNullDataUrl()
	{
		new Image((URL) null);
	}
	
	@Test
	public void newImageWithDataStream()
	{
		byte[] data = createBytes();
		Image image = new Image(new ByteArrayInputStream(data));
		
		// TODO: assert data URI when implemented
		assertImageEquals(null, data, image);
	}
	
	@Test(expected = NullPointerException.class)
	public void newImageWithNullDataStream()
	{
		new Image((InputStream) null);
	}
	
	@Test
	public void newImageWithDataArray()
	{
		byte[] data = createBytes();
		Image image = new Image(data);
		
		// TODO: assert data URI when implemented
		assertImageEquals(null, data, image);
	}
	
	@Test(expected = NullPointerException.class)
	public void newImageWithNullDataArray()
	{
		new Image((byte[]) null);
	}
	
	// private methods --------------------------------------------------------
	
	private static URL getResource(String name) throws FileNotFoundException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(name);
		
		if (url == null)
		{
			throw new FileNotFoundException(name);
		}
		
		return url;
	}
	
	private static byte[] createBytes()
	{
		return createBytes("dummy");
	}
	
	private static byte[] createBytes(String data)
	{
		try
		{
			return data.getBytes("US-ASCII");
		}
		catch (UnsupportedEncodingException exception)
		{
			throw new AssertionError("JVM must support US-ASCII charset");
		}
	}
	
	private static void assertImageEquals(URL expectedUrl, String expectedData, Image actual)
	{
		assertImageEquals(expectedUrl, createBytes(expectedData), actual);
	}
	
	private static void assertImageEquals(URL expectedUrl, byte[] expectedData, Image actual)
	{
		assertEquals("url", expectedUrl, actual.getUrl());
		assertArrayEquals("data", expectedData, actual.getData());
	}
}
