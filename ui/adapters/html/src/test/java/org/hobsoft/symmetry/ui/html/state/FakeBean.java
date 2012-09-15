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
package org.hobsoft.symmetry.ui.html.state;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class FakeBean
{
	// fields -----------------------------------------------------------------
	
	private boolean booleanPrimitive;
	
	private int intPrimitive;
	
	private String string;

	// constructors -----------------------------------------------------------
	
	public FakeBean()
	{
		// for JavaBean
	}
	
	// public methods ---------------------------------------------------------
	
	public boolean isBooleanPrimitive()
	{
		return booleanPrimitive;
	}
	
	public void setBooleanPrimitive(boolean booleanPrimitive)
	{
		this.booleanPrimitive = booleanPrimitive;
	}
	
	public int getIntPrimitive()
	{
		return intPrimitive;
	}
	
	public void setIntPrimitive(int intPrimitive)
	{
		this.intPrimitive = intPrimitive;
	}
	
	public String getString()
	{
		return string;
	}
	
	public void setString(String string)
	{
		this.string = string;
	}
}
