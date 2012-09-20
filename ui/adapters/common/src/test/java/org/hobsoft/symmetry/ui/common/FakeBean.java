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
package org.hobsoft.symmetry.ui.common;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class FakeBean
{
	// fields -----------------------------------------------------------------

	private String name;
	
	private String[] nickNames;
	
	private Object data;
	
	// public methods ---------------------------------------------------------

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String[] getNickNames()
	{
		return nickNames;
	}
	
	public void setNickNames(String[] nickNames)
	{
		this.nickNames = nickNames;
	}
	
	public String getNickNames(int index)
	{
		return nickNames[index];
	}
	
	public void setNickNames(int index, String nickName)
	{
		nickNames[index] = nickName;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
}
