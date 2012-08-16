/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlRadioHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static uk.co.iizuka.kozo.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static uk.co.iizuka.kozo.ui.common.BeanRehydrationUtils.setProperty;
import static uk.co.iizuka.kozo.ui.html.hydrate.HtmlComponentHydrators.form;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import java.beans.PropertyDescriptor;

import uk.co.iizuka.common.bean.Properties;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.PropertyState;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.state.StateException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.Selectable;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.common.hydrate.PhasedBeanHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Radio} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlRadioHydrator.java 98868 2012-02-29 11:10:57Z mark@IIZUKA.CO.UK $
 * @see Radio
 * @param <T>
 *            the component type this visitor can visit
 */
public class HtmlRadioHydrator<T extends Radio> extends PhasedBeanHydrator<T>
{
	// types ------------------------------------------------------------------
	
	private static class ParameterRehydrator<T extends Radio> extends NullHierarchicalComponentHydrator<T>
	{
		// TODO: fold into BeanRehydrationUtils somehow when simplified
		// TODO: this is not html-specific?
		
		// HierarchicalComponentVisitor methods -----------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Visit visit(T radio, HydrationContext context) throws HydrationException
		{
			Component parent = radio.getParent();

			// TODO: simplify this
			if (parent instanceof ToggleButtonGroup)
			{
				EncodedState encodedState = ((RehydrationContext) context).getEncodedState();
				StateCodec stateCodec = context.get(StateCodec.class);
				
				try
				{
					String parameterName = stateCodec.encodeBean(parent);
					Object parameterValue = encodedState.getParameterValue(parameterName);
					String encodedBean = (parameterValue != null) ? parameterValue.toString() : null;

					if (encodedBean != null && stateCodec.decodeBean(encodedBean) == radio)
					{
						PropertyDescriptor descriptor = Properties.getDescriptor(Radio.class,
							Selectable.SELECTED_PROPERTY);
						setProperty(new PropertyState(radio, descriptor, true));
					}
				}
				catch (StateException exception)
				{
					throw new HydrationException(exception);
				}
			}
			
			return SKIP_CHILDREN;
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public HtmlRadioHydrator()
	{
		setDelegate(DEHYDRATE_INITIALIZE, form(true, false));
		
		setDelegate(DEHYDRATE, new HtmlRadioDehydrator<T>());
		
		setDelegate(REHYDRATE_PARAMETERS, new ParameterRehydrator<T>());
	}
}
