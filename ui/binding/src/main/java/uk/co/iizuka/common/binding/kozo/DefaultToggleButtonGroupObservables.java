/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultToggleButtonGroupObservables.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.symmetry.ui.ToggleButton;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;

import uk.co.iizuka.common.binding.kozo.KozoObservables.ToggleButtonGroupObservables;

/**
 * Default {@code ToggleButtonGroupObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultToggleButtonGroupObservables.java 97896 2012-01-18 18:02:06Z mark@IIZUKA.CO.UK $
 * @see ToggleButtonGroupObservables
 */
class DefaultToggleButtonGroupObservables extends DefaultBoxObservables implements ToggleButtonGroupObservables
{
	// constructors -----------------------------------------------------------

	public DefaultToggleButtonGroupObservables(ToggleButtonGroup toggleButtonGroup)
	{
		super(toggleButtonGroup);
	}
	
	// ToggleButtonGroupObservables methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<ToggleButton> selectedButton()
	{
		return new ToggleButtonGroupSelectedButtonObservable(getComponent());
	}

	// DefaultComponentObservables methods ------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToggleButtonGroup getComponent()
	{
		return (ToggleButtonGroup) super.getComponent();
	}
}
