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
