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
package org.hobsoft.symmetry.ui.binding;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;
import org.hobsoft.symmetry.ui.Enableable;
import org.hobsoft.symmetry.ui.binding.KozoObservables.EnableableObservables;

/**
 * Default {@code EnableableObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultEnableableObservables.java 97529 2012-01-04 17:24:12Z mark@IIZUKA.CO.UK $
 * @see EnableableObservables
 */
class DefaultEnableableObservables implements EnableableObservables
{
	// fields -----------------------------------------------------------------
	
	private final Enableable enableable;
	
	// constructors -----------------------------------------------------------
	
	public DefaultEnableableObservables(Enableable enableable)
	{
		this.enableable = enableable;
	}
	
	// EnableableObservables methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<Boolean> enabled()
	{
		return Observables.bean(enableable).property(Enableable.ENABLED_PROPERTY, boolean.class);
	}
}
