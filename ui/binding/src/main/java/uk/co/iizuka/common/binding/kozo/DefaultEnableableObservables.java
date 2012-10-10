/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultEnableableObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;

import uk.co.iizuka.common.binding.kozo.KozoObservables.EnableableObservables;
import uk.co.iizuka.kozo.ui.Enableable;

/**
 * Default {@code EnableableObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultEnableableObservables.java 97529 2012-01-04 17:24:12Z mark@IIZUKA.CO.UK $
 * @see EnableableObservables
 */
class DefaultEnableableObservables implements EnableableObservables
{
	// fields -----------------------------------------------------------------
	
	private final Enableable enableable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultEnableableObservables(Enableable enableable)
	{
		this.enableable = enableable;
	}
	
	// EnableableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> enabled()
	{
		return Observables.bean(enableable).property(Enableable.ENABLED_PROPERTY, boolean.class);
	}
}
