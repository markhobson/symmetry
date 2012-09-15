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

import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

/**
 * 
 * 
 * @author Mark Hobson
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
