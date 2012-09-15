/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/SortOrder.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
