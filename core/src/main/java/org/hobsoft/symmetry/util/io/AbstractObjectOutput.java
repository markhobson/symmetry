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
package org.hobsoft.symmetry.util.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractObjectOutput extends DelegatingDataOutput implements ObjectOutput
{
	// constructors -----------------------------------------------------------
	
	public AbstractObjectOutput(DataOutput out)
	{
		super(out);
	}
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		flush(getDataOutput());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		close(getDataOutput());
	}
	
	// package methods --------------------------------------------------------
	
	static void flush(DataOutput out) throws IOException
	{
		if (out instanceof Flushable)
		{
			((Flushable) out).flush();
		}
	}
	
	static void close(DataOutput out) throws IOException
	{
		if (out instanceof Closeable)
		{
			((Closeable) out).close();
		}
	}
}
