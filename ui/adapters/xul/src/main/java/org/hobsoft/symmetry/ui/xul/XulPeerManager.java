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
package org.hobsoft.symmetry.ui.xul;

import org.hobsoft.symmetry.state.StatePeerManager;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.traversal.PostorderComponentVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class XulPeerManager extends StatePeerManager
{
	// TODO: move to symmetry-ui-common
	
	// PeerManager methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerComponent(Object component)
	{
		((Component) component).accept(new PostorderComponentVisitor<Void, RuntimeException>()
		{
			@Override
			protected void visit(Component component, Void parameter)
			{
				if (!component.isTransient())
				{
					XulPeerManager.super.registerComponent(component);
				}
			}
		}, null);
	}
}
