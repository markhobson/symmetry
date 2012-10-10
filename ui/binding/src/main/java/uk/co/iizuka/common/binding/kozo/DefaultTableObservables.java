/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultTableObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.symmetry.ui.Table;

import uk.co.iizuka.common.binding.kozo.KozoObservables.TableObservables;

/**
 * Default {@code TableObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTableObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see TableObservables
 */
class DefaultTableObservables extends DefaultComponentObservables implements TableObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultTableObservables(Table table)
	{
		super(table);
	}
	
	// TableObservables methods -----------------------------------------------
	
	// TODO: implement when added
}
