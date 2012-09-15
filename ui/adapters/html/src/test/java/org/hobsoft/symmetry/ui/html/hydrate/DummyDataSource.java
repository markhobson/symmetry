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
package org.hobsoft.symmetry.ui.html.hydrate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DummyDataSource implements DataSource
{
	// DataSource methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException
	{
		return new ByteArrayInputStream(new byte[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getOutputStream() throws IOException
	{
		throw new IOException("Dummy data source is read-only");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return "application/octet-stream";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "dummy";
	}
}
