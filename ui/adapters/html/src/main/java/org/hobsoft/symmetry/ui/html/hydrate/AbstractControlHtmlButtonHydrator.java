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
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrators.form;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * Phased base hydrator for dehydrating and rehydrating a {@code Button} component using an HTML form control.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractControlHtmlButtonHydrator<T extends Button> extends AbstractHtmlButtonHydrator<T>
{
	// types ------------------------------------------------------------------
	
	private static class ParameterRehydrator<T extends Button> extends NullHierarchicalComponentHydrator<T>
	{
		// constants --------------------------------------------------------------
		
		/**
		 * Submitted parameter name suffix for the clicked x-coordinate of image submit buttons. 
		 */
		private static final String IMAGE_X_SUFFIX = ".x";
		
		// HierarchicalComponentVisitor methods -----------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Visit visit(T button, HydrationContext context) throws HydrationException
		{
			// TODO: can we map parameters to event sets better?
			// TODO: ignore ToggleButtons?
			
			RehydrationContext rehydrationContext = (RehydrationContext) context;
			
			try
			{
				// TODO: how to generalise image parameter name suffix?
				if (hasParameter(button, rehydrationContext)
					|| hasParameter(button, IMAGE_X_SUFFIX, rehydrationContext))
				{
					button.doClick();
				}
			}
			catch (StateException exception)
			{
				throw new HydrationException(exception);
			}
			
			return SKIP_CHILDREN;
		}
		
		// private methods --------------------------------------------------------

		// TODO: move to BeanRehydrationUtils
		
		private static boolean hasParameter(Object bean, RehydrationContext context) throws StateException
		{
			return hasParameter(bean, "", context);
		}
		
		private static boolean hasParameter(Object bean, String parameterNameSuffix, RehydrationContext context)
			throws StateException
		{
			EncodedState encodedState = context.getEncodedState();
			StateCodec stateCodec = context.get(StateCodec.class);
			
			String parameterName = stateCodec.encodeBean(bean) + parameterNameSuffix;
			
			return encodedState.getParameterNames().contains(parameterName);
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public AbstractControlHtmlButtonHydrator()
	{
		setDelegate(DEHYDRATE_INITIALIZE, form(true, false));
		setDelegate(REHYDRATE_PARAMETERS, new ParameterRehydrator<T>());
	}
}
