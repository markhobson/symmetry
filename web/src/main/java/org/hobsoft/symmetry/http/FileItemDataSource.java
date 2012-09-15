/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/web/src/main/java/uk/co/iizuka/kozo/http/FileItemDataSource.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

import org.apache.commons.fileupload.FileItem;

/**
 * Adapts Commons FileUpload's {@code FileItem} to a {@code DataSource}.
 * 
 * @author Mark Hobson
 * @see FileItem
 * @see DataSource
 */
class FileItemDataSource implements DataSource
{
	// fields -----------------------------------------------------------------
	
	private final FileItem item;

	// constructors -----------------------------------------------------------
	
	public FileItemDataSource(FileItem item)
	{
		if (item == null)
		{
			throw new NullPointerException("item cannot be null");
		}
		
		this.item = item;
	}

	// DataSource methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException
	{
		return item.getInputStream();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getOutputStream() throws IOException
	{
		// TODO: is this the 'appropriate exception' as detailed in the javadoc?
		throw new IOException("Data source is read-only");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return item.getContentType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return item.getName();
	}
}
