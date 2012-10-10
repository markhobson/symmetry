/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultLabelObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;

import uk.co.iizuka.common.binding.kozo.KozoObservables.LabelObservables;
import uk.co.iizuka.kozo.ui.Label;

/**
 * Default {@code LabelObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultLabelObservables.java 97533 2012-01-04 17:51:28Z mark@IIZUKA.CO.UK $
 * @see LabelObservables
 */
class DefaultLabelObservables extends DefaultComponentObservables implements LabelObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultLabelObservables(Label label)
	{
		super(label);
	}
	
	// LabelObservables methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<String> text()
	{
		return Observables.bean(getComponent()).string(Label.TEXT_PROPERTY);
	}
}
