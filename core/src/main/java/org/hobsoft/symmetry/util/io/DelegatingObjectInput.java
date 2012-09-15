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

import java.io.IOException;
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingObjectInput extends DelegatingDataInput implements ExplicitObjectInput
{
	// constructors -----------------------------------------------------------
	
	public DelegatingObjectInput(ObjectInput in)
	{
		super(in);
	}
	
	// ExplicitObjectInput methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject(Class<?> type) throws ClassNotFoundException, IOException
	{
		Object object;
		
		if (getObjectInput() instanceof ExplicitObjectInput)
		{
			object = ((ExplicitObjectInput) getObjectInput()).readObject(type);
		}
		else
		{
			object = readObject();
		}
		
		return object;
	}
	
	// ObjectInput methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readObject() throws ClassNotFoundException, IOException
	{
		return getObjectInput().readObject();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException
	{
		return getObjectInput().read();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b) throws IOException
	{
		return getObjectInput().read(b);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int read(byte[] b, int off, int len) throws IOException
	{
		return getObjectInput().read(b, off, len);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long skip(long n) throws IOException
	{
		return getObjectInput().skip(n);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int available() throws IOException
	{
		return getObjectInput().available();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		getObjectInput().close();
	}
	
	// protected methods ------------------------------------------------------
	
	protected ObjectInput getObjectInput()
	{
		return (ObjectInput) getDataInput();
	}
}
