/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/TableVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public interface TableVisitor<T extends Table, P, E extends Exception> extends HierarchicalComponentVisitor<T, P, E>
{
	// TODO: visit columns too to give implementations a choice of navigation?
	
	// workaround checkstyle bug: "Unable to get class information for E"
	// CHECKSTYLE:OFF
	Visit visitHeader(T table, P parameter) throws E;
	
	Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E;
	
	EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E;
	
	EndVisit endVisitHeader(T table, P parameter) throws E;
	
	Visit visitBody(T table, P parameter) throws E;
	
	Visit visitRow(T table, P parameter, int rowIndex) throws E;
	
	Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E;
	
	EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E;
	
	EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E;
	
	EndVisit endVisitBody(T table, P parameter) throws E;
	// CHECKSTYLE:ON
}
