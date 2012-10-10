/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultTextBoxObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.Observable;
import uk.co.iizuka.common.binding.Observables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.EnableableObservables;
import uk.co.iizuka.common.binding.kozo.KozoObservables.TextBoxObservables;
import uk.co.iizuka.kozo.ui.TextBox;

/**
 * Default {@code TextBoxObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultTextBoxObservables.java 97533 2012-01-04 17:51:28Z mark@IIZUKA.CO.UK $
 * @see TextBoxObservables
 */
class DefaultTextBoxObservables extends DefaultComponentObservables implements TextBoxObservables
{
	// fields -----------------------------------------------------------------
	
	private final EnableableObservables enableable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultTextBoxObservables(TextBox textBox)
	{
		super(textBox);
		
		enableable = new DefaultEnableableObservables(textBox);
	}
	
	// TextBoxObservables methods ---------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<String> text()
	{
		return Observables.bean(getComponent()).string(TextBox.TEXT_PROPERTY);
	}
	
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
