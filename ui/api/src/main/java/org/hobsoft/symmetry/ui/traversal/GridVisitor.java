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

import org.hobsoft.symmetry.ui.Grid;

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
