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
package org.hobsoft.symmetry.ui.traversal;

import java.util.Arrays;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.ComponentUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * 
 * @author Mark Hobson
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
