/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/GridVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Grid;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: GridVisitor.java 100092 2012-03-30 11:49:15Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public interface GridVisitor<T extends Grid, P, E extends Exception> extends ContainerVisitor<T, P, E>
{
	// TODO: visit children in columns too to give implementations a choice of navigation?
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visitColumns(T grid, P parameter) throws E;
	
	EndVisit visitColumn(T grid, P parameter, int columnIndex) throws E;
	
	EndVisit endVisitColumns(T grid, P parameter) throws E;
	
	Visit visitRows(T grid, P parameter) throws E;
	
	Visit visitRow(T grid, P parameter, int rowIndex) throws E;
	
	EndVisit endVisitRow(T grid, P parameter, int rowIndex) throws E;
	
	EndVisit endVisitRows(T grid, P parameter) throws E;
	// CHECKSTYLE:ON
}
