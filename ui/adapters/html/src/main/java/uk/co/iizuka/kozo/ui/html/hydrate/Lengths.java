/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/Lengths.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.iizuka.kozo.ui.layout.Length;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Lengths.java 100133 2012-04-02 10:09:45Z mark@IIZUKA.CO.UK $
 */
final class Lengths
{
	// fields -----------------------------------------------------------------
	
	private final List<Length> lengths;
	
	private boolean flexed;
	
	// constructors -----------------------------------------------------------
	
	public Lengths()
	{
		lengths = new ArrayList<Length>();
		flexed = false;
	}

	// public methods ---------------------------------------------------------
	
	public void add(Length length)
	{
		lengths.add(length);
		
		if (length != null && length.getUnit() == Length.Unit.FLEX)
		{
			flexed = true;
		}
	}
	
	public Length getLength(int index)
	{
		return lengths.get(index);
	}
	
	public List<Length> getLengths()
	{
		return Collections.unmodifiableList(lengths);
	}
	
	public void clear(int index)
	{
		lengths.set(index, null);
	}
	
	public boolean isFlexed()
	{
		return flexed;
	}

	public Lengths normalize()
	{
		LengthUtils.normalize(lengths);
		
		return this;
	}
}
