/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlRadioHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.PropertyState;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.support.bean.Properties;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Radio;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.ToggleButtonGroup;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.DEHYDRATE_INITIALIZE;
import static org.hobsoft.symmetry.hydrate.HydrationPhase.REHYDRATE_PARAMETERS;
import static org.hobsoft.symmetry.ui.common.BeanRehydrationUtils.setProperty;
import static org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrators.form;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Radio} component using an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
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
