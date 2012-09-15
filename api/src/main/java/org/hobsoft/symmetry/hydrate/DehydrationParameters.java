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
package org.hobsoft.symmetry.hydrate;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class DehydrationParameters
{
	// TODO: remove this and move parameters into respective dehydrators
	
	// fields -----------------------------------------------------------------
	
	private String theme;
	
	private boolean debug;

	// public methods ---------------------------------------------------------
	
	public String getTheme()
	{
		return theme;
	}
	
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
}
