/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/BoxUtils.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import uk.co.iizuka.kozo.ui.Box;
import uk.co.iizuka.kozo.ui.layout.BoxLayoutData;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BoxUtils.java 100091 2012-03-30 10:58:09Z mark@IIZUKA.CO.UK $
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
