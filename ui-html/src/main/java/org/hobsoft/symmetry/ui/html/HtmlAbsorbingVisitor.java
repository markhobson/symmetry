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
package org.hobsoft.symmetry.ui.html;

import java.util.Map;

import org.hobsoft.symmetry.ui.ComponentVisitor;
import org.hobsoft.symmetry.ui.Text;
import org.hobsoft.symmetry.ui.Window;

/**
 * Visitor that configures UI components from HTTP state.
 */
public class HtmlAbsorbingVisitor implements ComponentVisitor<Map<String, String[]>, RuntimeException>
{
	// ----------------------------------------------------------------------------------------------------------------
	// ComponentVisitor methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public void visit(Window window, Map<String, String[]> state) throws RuntimeException
	{
		// TODO: implement
	}

	@Override
	public void endVisit(Window window, Map<String, String[]> state) throws RuntimeException
	{
		// TODO: implement
	}

	@Override
	public void visit(Text text, Map<String, String[]> state) throws RuntimeException
	{
		if (state.containsKey("text"))
		{
			text.setText(state.get("text")[0]);
		}
	}
}
