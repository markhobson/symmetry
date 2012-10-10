/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultSelectableObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;

import uk.co.iizuka.common.binding.kozo.KozoObservables.SelectableObservables;
import uk.co.iizuka.kozo.ui.Selectable;

/**
 * Default {@code SelectableObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultSelectableObservables.java 97529 2012-01-04 17:24:12Z mark@IIZUKA.CO.UK $
 * @see SelectableObservables
 */
class DefaultSelectableObservables implements SelectableObservables
{
	// fields -----------------------------------------------------------------
	
	private final Selectable selectable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultSelectableObservables(Selectable selectable)
	{
		this.selectable = selectable;
	}
	
	// SelectableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> selected()
	{
		return Observables.bean(selectable).property(Selectable.SELECTED_PROPERTY, boolean.class);
	}
}
