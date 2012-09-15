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
package org.hobsoft.symmetry.ui;

/**
 * A type-safe enumeration of sorting methods.
 * 
 * @author Mark Hobson
 */
public enum SortOrder
{
	// constants --------------------------------------------------------------
	
	/**
	 * Instance that does not sort.
	 */
	UNSORTED
	{
		// SortOrder methods --------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public SortOrder toInverse()
		{
			return UNSORTED;
		}
	},
	
	/**
	 * Instance that sorts in order.
	 */
	ASCENDING
	{
		// SortOrder methods --------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public SortOrder toInverse()
		{
			return DESCENDING;
		}
	},
	
	/**
	 * Instance that sorts in reverse order.
	 */
	DESCENDING
	{
		// SortOrder methods --------------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public SortOrder toInverse()
		{
			return ASCENDING;
		}
	};
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Returns the opposite sort order to this sort order. {@code ASCENDING} and {@code DESCENDING} are mutual
	 * inverses, whereas {@code UNSORTED} is self-inverting.
	 * 
	 * @return the opposite sort order to this sort order
	 */
	public abstract SortOrder toInverse();
}
