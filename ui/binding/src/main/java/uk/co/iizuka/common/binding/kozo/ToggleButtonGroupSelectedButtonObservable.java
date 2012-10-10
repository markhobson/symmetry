/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/ToggleButtonGroupSelectedButtonObservable.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.ObservableListener;
import uk.co.iizuka.kozo.ui.ToggleButton;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ToggleButtonGroupSelectedButtonObservable.java 97896 2012-01-18 18:02:06Z mark@IIZUKA.CO.UK $
 */
class ToggleButtonGroupSelectedButtonObservable implements Observable<ToggleButton>
{
	// TODO: remove when ToggleButtonGroup.SELECTED_BUTTON property implemented
	
	// fields -----------------------------------------------------------------

	private final ToggleButtonGroup group;
	
	// constructors -----------------------------------------------------------

	public ToggleButtonGroupSelectedButtonObservable(ToggleButtonGroup group)
	{
		this.group = group;
	}

	// Observable methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addObservableListener(ObservableListener<ToggleButton> listener)
	{
		// TODO: implement
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeObservableListener(ObservableListener<ToggleButton> listener)
	{
		// TODO: implement
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToggleButton getValue()
	{
		return group.getSelectedButton();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(ToggleButton value)
	{
		group.setSelectedButton(value);
	}
}
