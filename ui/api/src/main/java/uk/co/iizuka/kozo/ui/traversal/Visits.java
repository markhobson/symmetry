/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/Visits.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Visits.java 95328 2011-11-18 11:53:51Z mark@IIZUKA.CO.UK $
 */
public final class Visits
{
	// constructors -----------------------------------------------------------
	
	private Visits()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static Visit nullVisit(Visit visit)
	{
		return (visit != null) ? visit : VISIT_CHILDREN;
	}
	
	public static EndVisit nullEndVisit(EndVisit endVisit)
	{
		return (endVisit != null) ? endVisit : VISIT_SIBLINGS;
	}
}
