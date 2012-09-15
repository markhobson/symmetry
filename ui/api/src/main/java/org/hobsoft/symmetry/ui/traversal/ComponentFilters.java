/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ComponentFilters.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ComponentUtils;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ComponentFilters.java 100646 2012-04-23 10:00:25Z mark@IIZUKA.CO.UK $
 */
public final class ComponentFilters
{
	// constants --------------------------------------------------------------
	
	private static final ComponentFilter ACCEPT = new ComponentFilter()
	{
		@Override
		public boolean accept(Component component)
		{
			return true;
		}
	};
	
	private static final ComponentFilter REJECT = new ComponentFilter()
	{
		@Override
		public boolean accept(Component component)
		{
			return false;
		}
	};
	
	private static final ComponentFilter VISIBLE = new ComponentFilter()
	{
		@Override
		public boolean accept(Component component)
		{
			return component.isVisible();
		}
	};

	// constructors -----------------------------------------------------------
	
	private ComponentFilters()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static ComponentFilter accept()
	{
		return ACCEPT;
	}
	
	public static ComponentFilter reject()
	{
		return REJECT;
	}
	
	public static ComponentFilter visible()
	{
		return VISIBLE;
	}
	
	public static ComponentFilter not(final ComponentFilter filter)
	{
		checkNotNull(filter, "filter cannot be null");
		
		return new ComponentFilter()
		{
			@Override
			public boolean accept(Component component)
			{
				return !filter.accept(component);
			}
		};
	}
	
	public static ComponentFilter and(ComponentFilter... filters)
	{
		return and(Arrays.asList(filters));
	}
	
	public static ComponentFilter and(final Iterable<? extends ComponentFilter> filters)
	{
		return new ComponentFilter()
		{
			@Override
			public boolean accept(Component component)
			{
				for (ComponentFilter filter : filters)
				{
					if (!filter.accept(component))
					{
						return false;
					}
				}
				
				return true;
			}
		};
	}
	
	public static ComponentFilter or(ComponentFilter... filters)
	{
		return or(Arrays.asList(filters));
	}
	
	public static ComponentFilter or(final Iterable<? extends ComponentFilter> filters)
	{
		return new ComponentFilter()
		{
			@Override
			public boolean accept(Component component)
			{
				for (ComponentFilter filter : filters)
				{
					if (filter.accept(component))
					{
						return true;
					}
				}
				
				return false;
			}
		};
	}
	
	public static ComponentFilter type(final Class<? extends Component> type)
	{
		checkNotNull(type, "type cannot be null");
		
		return new ComponentFilter()
		{
			@Override
			public boolean accept(Component component)
			{
				return type.isInstance(component);
			}
		};
	}
	
	public static ComponentFilter ancestor(final ComponentFilter filter)
	{
		return new ComponentFilter()
		{
			@Override
			public boolean accept(Component component)
			{
				return (ComponentUtils.getAncestor(component, filter) != null);
			}
		};
	}
}
