/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultDeckObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import uk.co.iizuka.common.binding.kozo.KozoObservables.DeckObservables;
import uk.co.iizuka.kozo.ui.Deck;

/**
 * Default {@code DeckObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultDeckObservables.java 97523 2012-01-04 16:57:21Z mark@IIZUKA.CO.UK $
 * @see DeckObservables
 */
class DefaultDeckObservables extends DefaultBoxObservables implements DeckObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultDeckObservables(Deck deck)
	{
		super(deck);
	}
	
	// DeckObservables methods ------------------------------------------------
	
	// TODO: implement when added
}
