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
package org.hobsoft.symmetry.support.io;

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class DelegatingObjectOutput extends DelegatingDataOutput implements ObjectOutput
{
	// constructors -----------------------------------------------------------
	
	public DelegatingObjectOutput(ObjectOutput out)
	{
		super(out);
	}
	
	// ObjectOutput methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeObject(Object object) throws IOException
	{
		getObjectOutput().writeObject(object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() throws IOException
	{
		getObjectOutput().flush();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException
	{
		getObjectOutput().close();
	}
	
	// protected methods ------------------------------------------------------
	
	protected ObjectOutput getObjectOutput()
	{
		return (ObjectOutput) getDataOutput();
	}
}
