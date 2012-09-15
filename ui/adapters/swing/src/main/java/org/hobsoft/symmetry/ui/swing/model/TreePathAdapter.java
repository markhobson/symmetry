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
package org.hobsoft.symmetry.ui.swing.model;

import org.hobsoft.symmetry.ui.model.TreePath;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TreePathAdapter extends TreePath
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// constructors -----------------------------------------------------------
	
	public TreePathAdapter(javax.swing.tree.TreePath treePath)
	{
		super(treePath.getParentPath() == null ? null : new TreePathAdapter(treePath.getParentPath()),
			treePath.getLastPathComponent());
	}
}
