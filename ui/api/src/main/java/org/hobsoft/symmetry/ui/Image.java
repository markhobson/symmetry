/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Image.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Image.java 99065 2012-03-08 13:00:14Z mark@IIZUKA.CO.UK $
 */
public class Image
{
	// TODO: refactor to internally use a data provider, e.g. ArrayDataProvider, UrlDataProvider, etc.
	
	// constants --------------------------------------------------------------
	
	private static final int BUFFER_SIZE = 1024 * 4;
	
	// fields -----------------------------------------------------------------
	
	private final URL dataUrl;
	
	private byte[] data;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates an {@code Image} from the specified data URI.
	 * <p>
	 * If the URI is relative then a URL is obtained by loading the URI as a resource from the context class loader.
	 * 
	 * @param dataUri
	 *            the URI to read the image data from
	 * @throws IllegalArgumentException
	 *             if the specified URI cannot be converted to a URL
	 */
	public Image(String dataUri)
	{
		this(toUri(dataUri));
	}
	
	/**
	 * Creates an {@code Image} from the specified data URI.
	 * <p>
	 * If the URI is relative then a URL is obtained by loading the URI as a resource from the context class loader.
	 * 
	 * @param dataUri
	 *            the URI to read the image data from
	 * @throws IllegalArgumentException
	 *             if the specified URI cannot be converted to a URL
	 */
	public Image(URI dataUri)
	{
		this(toUrl(dataUri));
	}
	
	/**
	 * Creates an {@code Image} from the specified data URL. The data is read from the URL only when {@link #getData()}
	 * is called.
	 * 
	 * @param dataUrl
	 *            the URL to read the image data from
	 */
	public Image(URL dataUrl)
	{
		this.dataUrl = checkNotNull(dataUrl, "dataUrl cannot be null");
	}
	
	/**
	 * Creates an {@code Image} from the specified data stream. The data is read from the stream immediately.
	 * 
	 * @param dataStream
	 *            the stream to read the image data from
	 */
	public Image(InputStream dataStream)
	{
		// TODO: do we want to read the stream lazily?
		this(toByteArray(dataStream));
	}
	
	/**
	 * Creates an {@code Image} with the specified data.
	 * 
	 * @param data
	 *            an array containing the image data
	 */
	public Image(byte[] data)
	{
		checkNotNull(data, "data cannot be null");
		
		dataUrl = null;
		
		// TODO: defensive copy?
		this.data = data;
	}
	
	// public methods ---------------------------------------------------------
	
	public URL getUrl()
	{
		// TODO: return data URI if url is null
		
		return dataUrl;
	}
	
	public byte[] getData()
	{
		if (data == null)
		{
			try
			{
				loadData();
			}
			catch (IOException exception)
			{
				// TODO: how best to handle?
				throw new IllegalStateException("Error reading data stream", exception);
			}
		}
		
		return data;
	}
	
	// private methods --------------------------------------------------------
	
	private synchronized void loadData() throws IOException
	{
		data = loadData(dataUrl);
	}
	
	private static URL toUrl(URI dataUri)
	{
		checkNotNull(dataUri, "dataUri cannot be null");
		
		URL dataUrl;
		
		if (dataUri.isAbsolute())
		{
			try
			{
				dataUrl = dataUri.toURL();
			}
			catch (MalformedURLException exception)
			{
				throw new IllegalArgumentException("dataUri must be a valid URL: " + dataUri, exception);
			}
		}
		else
		{
			dataUrl = loadResource(dataUri.getSchemeSpecificPart());
			
			if (dataUrl == null)
			{
				throw new IllegalArgumentException("dataUri cannot be resolved to a resource: " + dataUri);
			}
		}
		
		return dataUrl;
	}
	
	private static URL loadResource(String name)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		return classLoader.getResource(name);
	}
	
	private static URI toUri(String dataUri)
	{
		checkNotNull(dataUri, "dataUri cannot be null");
		
		try
		{
			return new URI(dataUri);
		}
		catch (URISyntaxException exception)
		{
			throw new IllegalArgumentException("dataUri must be a valid URI: " + dataUri, exception);
		}
	}
	
	private static byte[] toByteArray(InputStream dataStream)
	{
		try
		{
			return loadData(dataStream);
		}
		catch (IOException exception)
		{
			throw new IllegalArgumentException("Error reading data stream", exception);
		}
	}
	
	private static byte[] loadData(URL dataUrl) throws IOException
	{
		return loadData(dataUrl.openStream());
	}
	
	private static byte[] loadData(InputStream dataStream) throws IOException
	{
		checkNotNull(dataStream, "dataStream cannot be null");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] bytes = new byte[BUFFER_SIZE];
		int n;
		
		while ((n = dataStream.read(bytes)) != -1)
		{
			out.write(bytes, 0, n);
		}
		
		return out.toByteArray();
	}
}
