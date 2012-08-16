/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Component.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static com.google.common.base.Preconditions.checkNotNull;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.nullHierarchicalVisitor;
import static uk.co.iizuka.kozo.ui.traversal.Visits.nullEndVisit;

import java.util.Arrays;
import java.util.EnumSet;

import com.googlecode.jtype.Generic;

import uk.co.iizuka.common.bean.AbstractBean;
import uk.co.iizuka.kozo.ui.functor.Closure;
import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Component.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 */
public abstract class Component extends AbstractBean
{
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the component's name.
	 */
	public static final String NAME_PROPERTY = "name";
	
	/**
	 * JavaBean property name that identifies a change in the component's parent.
	 */
	public static final String PARENT_PROPERTY = "parent";
	
	/**
	 * JavaBean property name that identifies a change in the component's transiency.
	 */
	public static final String TRANSIENT_PROPERTY = "transient";

	/**
	 * JavaBean property name that identifies a change in the component's visibility.
	 */
	public static final String VISIBLE_PROPERTY = "visible";
	
	public static final String STYLES_PROPERTY = "styles";
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The name of this component.
	 */
	private String name;
	
	/**
	 * The parent of this component.
	 */
	private Component parent;
	
	private boolean tranzient;
	
	private boolean visible;
	
	private EnumSet<Style> styles;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new {@code Component}.
	 */
	public Component()
	{
		tranzient = false;
		visible = true;
		styles = EnumSet.noneOf(Style.class);
	}
	
	// public methods ---------------------------------------------------------
	
	public abstract <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E;
	
	/**
	 * Gets the name of this component.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets the name of this component.
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(String name)
	{
		String oldName = this.name;
		this.name = name;
		firePropertyChange(NAME_PROPERTY, oldName, name);
	}
	
	/**
	 * Gets the parent of this component.
	 * 
	 * @return the parent of this component, or {@code null} if this component has not been added to another
	 *         component
	 */
	public Component getParent()
	{
		return parent;
	}
	
	/**
	 * Sets the parent of this component.
	 * <p>
	 * This method is intended to be used by container subclasses only.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param parent
	 *            the parent of this component
	 */
	public void setParent(Component parent)
	{
		Component oldParent = this.parent;
		this.parent = parent;
		firePropertyChange(PARENT_PROPERTY, oldParent, parent);
	}

	/**
	 * Gets whether this component's state is to be considered transient. When {@code true}, this means that the
	 * state of this component will not be preserved when persisting the user interface.
	 * 
	 * @return whether this component's state is to be considered transient
	 */
	public boolean isTransient()
	{
		return tranzient;
	}
	
	/**
	 * Sets whether this component's state is to be considered transient. When set to {@code true}, this means that
	 * the state of this component will not be preserved when persisting the user interface.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param tranzient
	 *            whether this component's state is to be considered transient
	 */
	public void setTransient(boolean tranzient)
	{
		boolean oldTransient = this.tranzient;
		this.tranzient = tranzient;
		firePropertyChange(TRANSIENT_PROPERTY, oldTransient, tranzient);
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisible(boolean visible)
	{
		boolean oldVisible = this.visible;
		this.visible = visible;
		firePropertyChange(VISIBLE_PROPERTY, oldVisible, visible);
	}
	
	public Closure<Boolean> visible()
	{
		return new Closure<Boolean>()
		{
			@Override
			public void apply(Boolean visible)
			{
				setVisible(Boolean.TRUE.equals(visible));
			}
		};
	}
	
	public Style[] getStyles()
	{
		// TODO: should use EnumSet rather than Style[] for more efficient serialization, but erasure loses element
		// type..
		
		return styles.toArray(new Style[styles.size()]);
	}
	
	public void setStyles(Style[] styles)
	{
		Style[] oldStyles = getStyles();
		this.styles.clear();
		this.styles.addAll(Arrays.asList(styles));
		firePropertyChange(STYLES_PROPERTY, oldStyles, getStyles());
	}
	
	public void addStyle(Style style)
	{
		if (styles.contains(style))
		{
			return;
		}
		
		Style[] oldStyles = getStyles();
		styles.add(style);
		firePropertyChange(STYLES_PROPERTY, oldStyles, getStyles());
	}
	
	public void removeStyle(Style style)
	{
		if (!styles.contains(style))
		{
			return;
		}
		
		Style[] oldStyles = getStyles();
		styles.remove(style);
		firePropertyChange(STYLES_PROPERTY, oldStyles, getStyles());
	}
	
	public boolean hasStyle(Style style)
	{
		return styles.contains(style);
	}
	
	// protected methods ------------------------------------------------------
	
	protected static <T extends Component, P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor,
		Class<T> componentType, T component, P parameter) throws E
	{
		return accept(visitor, Generic.get(componentType), component, parameter);
	}
	
	protected static <T extends Component, P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor,
		Generic<T> componentType, T component, P parameter) throws E
	{
		checkNotNull(visitor, "visitor cannot be null");
		
		HierarchicalComponentVisitor<T, P, E> subvisitor = visitor.visit(componentType, component, parameter);
		
		nullHierarchicalVisitor(subvisitor).visit(component, parameter);
		
		return nullEndVisit(nullHierarchicalVisitor(subvisitor).endVisit(component, parameter));
	}
}
