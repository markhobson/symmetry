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
import java.io.InputStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TinyDataInput extends DefaultDataInput
{
	// constructors -----------------------------------------------------------
	
	public TinyDataInput(InputStream in)
	{
		super((in instanceof BitInputStream) ? (BitInputStream) in : new BitInputStream(in));
	}
	
	// DataInput methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean readBoolean() throws IOException
	{
		return (getBitInputStream().readBit() == 1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int readInt() throws IOException
	{
		int n = getBitInputStream().readBits(2);
		
		int b0 = (n == 0) ? safeRead() : 0;
		int b1 = (n <= 1) ? safeRead() : 0;
		int b2 = (n <= 2) ? safeRead() : 0;
		int b3 = (n <= 3) ? safeRead() : 0;
		
		return ((b0 << 24) + (b1 << 16) + (b2 << 8) + b3);
	}
	
	// protected methods ------------------------------------------------------
	
	protected BitInputStream getBitInputStream()
	{
		return (BitInputStream) getInputStream();
	}
}
