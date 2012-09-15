/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
