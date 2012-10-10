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
package uk.co.iizuka.common.binding.kozo;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;
import org.hobsoft.symmetry.ui.Label;

import uk.co.iizuka.common.binding.kozo.KozoObservables.LabelObservables;

/**
 * Default {@code LabelObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultLabelObservables.java 97533 2012-01-04 17:51:28Z mark@IIZUKA.CO.UK $
 * @see LabelObservables
 */
class DefaultLabelObservables extends DefaultComponentObservables implements LabelObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultLabelObservables(Label label)
	{
		super(label);
	}
	
	// LabelObservables methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<String> text()
	{
		return Observables.bean(getComponent()).string(Label.TEXT_PROPERTY);
	}
}
