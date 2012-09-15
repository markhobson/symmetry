/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/BoxUtils.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.layout.BoxLayoutData;

/**
 * 
 * 
 * @author Mark Hobson
 */
final class BoxUtils
{
	// constructors -----------------------------------------------------------
	
	private BoxUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static int getTotalFlex(Box box)
	{
		int totalFlex = 0;
		int childCount = box.getComponentCount();
		
		for (int childIndex = 0; childIndex < childCount; childIndex++)
		{
			BoxLayoutData layoutData = box.getLayoutData(childIndex);
			
			totalFlex += LengthUtils.getFlex(layoutData.getLength());
		}
		
		return totalFlex;
	}
}
