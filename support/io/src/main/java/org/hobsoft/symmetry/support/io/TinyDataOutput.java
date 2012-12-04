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
import java.io.OutputStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TinyDataOutput extends DefaultDataOutput
{
	// constructors -----------------------------------------------------------
	
	public TinyDataOutput(OutputStream out)
	{
		super((out instanceof BitOutputStream) ? (BitOutputStream) out : new BitOutputStream(out));
	}
	
	// DataOutput methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBoolean(boolean v) throws IOException
	{
		getBitOutputStream().writeBit(v ? 1 : 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeInt(int v) throws IOException
	{
		int b0 = (v >>> 24) & 0xFF;
		int b1 = (v >>> 16) & 0xFF;
		int b2 = (v >>> 8) & 0xFF;
		
		int n = (b0 == 0) ? (b1 == 0) ? (b2 == 0) ? 3 : 2 : 1 : 0;
		getBitOutputStream().writeBits(n, 2);
		
		if (n == 0)
		{
			write(b0);
		}
		
		if (n <= 1)
		{
			write(b1);
		}
		
		if (n <= 2)
		{
			write(b2);
		}
		
		if (n <= 3)
		{
			write(v & 0xFF);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected BitOutputStream getBitOutputStream()
	{
		return (BitOutputStream) getOutputStream();
	}
}
