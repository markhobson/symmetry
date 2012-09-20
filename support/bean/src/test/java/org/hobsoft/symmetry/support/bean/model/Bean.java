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
package org.hobsoft.symmetry.support.bean.model;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Bean
{
	// fields -----------------------------------------------------------------
	
	private String foo;
	
	private String bar;
	
	private int baz;
	
	// constructors -----------------------------------------------------------
	
	public Bean()
	{
		// for JavaBean
	}
	
	// public methods ---------------------------------------------------------
	
	public String getFoo()
	{
		return foo;
	}
	
	public void setFoo(String foo)
	{
		this.foo = foo;
	}
	
	public String getBar()
	{
		return bar;
	}
	
	public void setBar(String bar)
	{
		this.bar = bar;
	}
	
	public int getBaz()
	{
		return baz;
	}
	
	public void setBaz(int baz)
	{
		this.baz = baz;
	}
}
