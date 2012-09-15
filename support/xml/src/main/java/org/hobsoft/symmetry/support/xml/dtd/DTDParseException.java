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
package org.hobsoft.symmetry.support.xml.dtd;

/**
 * Indicates that an error occurred whilst trying to parse a DTD.
 * 
 * @author Mark Hobson
 */
public class DTDParseException extends Exception
{
	// constants --------------------------------------------------------------
	
	/**
	 * The serialisation unique ID.
	 */
	private static final long serialVersionUID = 2785972999124337329L;
	
	// fields -----------------------------------------------------------------
	
	private final int column;
	
	// constructors -----------------------------------------------------------
	
	public DTDParseException(String message)
	{
		this(message, -1);
	}
	
	public DTDParseException(String message, int column)
	{
		super((column != -1) ? message + " at column " + column : message);
		
		this.column = column;
	}
	
	// public methods ---------------------------------------------------------
	
	public int getColumn()
	{
		return column;
	}
}
