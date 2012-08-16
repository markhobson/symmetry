/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractControlHtmlButtonHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.html.hydrate.HtmlComponentHydrators.form;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.state.StateException;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Phased base hydrator for dehydrating and rehydrating a {@code Button} component using an HTML form control.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractControlHtmlButtonHydrator.java 98868 2012-02-29 11:10:57Z mark@IIZUKA.CO.UK $
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
