/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultToggleButtonObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.kozo.KozoObservables.SelectableObservables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.ToggleButtonObservables;
import uk.co.iizuka.kozo.ui.ToggleButton;

/**
 * Default {@code ToggleButtonObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultToggleButtonObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see ToggleButtonObservables
 */
class DefaultToggleButtonObservables extends DefaultButtonObservables implements ToggleButtonObservables
{
	// fields -----------------------------------------------------------------
	
	private final SelectableObservables selectable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultToggleButtonObservables(ToggleButton toggleButton)
	{
		super(toggleButton);
		
		selectable = new DefaultSelectableObservables(toggleButton);
	}
	
	// ToggleButtonObservables methods ----------------------------------------

	// TODO: implement when added
	
	// SelectableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> selected()
	{
		return selectable.selected();
	}
}
