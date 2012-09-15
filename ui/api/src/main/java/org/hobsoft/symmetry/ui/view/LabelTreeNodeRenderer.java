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
package org.hobsoft.symmetry.ui.view;

import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.model.TreePath;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTreeNodeRenderer extends Label implements TreeNodeRenderer
{
	// constructors -----------------------------------------------------------
	
	public LabelTreeNodeRenderer()
	{
		setTransient(true);
	}
	
	// TreeNodeRenderer methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Label getTreeNodeComponent(Tree tree, TreePath path)
	{
		checkNotNull(tree, "tree cannot be null");
		checkNotNull(path, "path cannot be null");
		
		Object node = path.getNode();
		
		setText(firstNonNull(node, "").toString());
		
		return this;
	}
}
