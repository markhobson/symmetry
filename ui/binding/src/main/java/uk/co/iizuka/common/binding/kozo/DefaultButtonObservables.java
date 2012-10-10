/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultButtonObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.symmetry.ui.Button;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ButtonObservables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.EnableableObservables;

/**
 * Default {@code ButtonObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultButtonObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see ButtonObservables
 */
class DefaultButtonObservables extends DefaultLabelObservables implements ButtonObservables
{
	// fields -----------------------------------------------------------------
	
	private final EnableableObservables enableable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultButtonObservables(Button button)
	{
		super(button);
		
		enableable = new DefaultEnableableObservables(button);
	}
	
	// ButtonObservables methods ----------------------------------------------
	
	// TODO: implement when added
	
	// EnableableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> enabled()
	{
		return enableable.enabled();
	}
}
