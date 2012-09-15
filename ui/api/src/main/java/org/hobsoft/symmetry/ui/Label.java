/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Label.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static com.google.common.base.Strings.nullToEmpty;

import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * A component that can display text, an image, or both.
 * 
 * @author Mark Hobson
 * @version $Id: Label.java 99066 2012-03-08 13:02:09Z mark@IIZUKA.CO.UK $
 */
public class Label extends Component
{
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the label's image.
	 */
	public static final String IMAGE_PROPERTY = "image";
	
	/**
	 * JavaBean property name that identifies a change in the label's text.
	 */
	public static final String TEXT_PROPERTY = "text";
	
	/**
	 * JavaBean property name that identifies a change in the label's tooltip.
	 */
	public static final String TOOL_TIP_PROPERTY = "toolTip";
	
	public static final String LABEL_FOR_PROPERTY = "labelFor";
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The text for this label.
	 */
	private String text;
	
	/**
	 * The image for this label.
	 */
	private Image image;
	
	/**
	 * The tooltip for this label.
	 */
	private String toolTip;
	
	private Component labelFor;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a label with no text or image.
	 * 
	 * @param styles
	 */
	public Label(Style... styles)
	{
		this("", styles);
	}
	
	/**
	 * Creates a label with the specified text.
	 * 
	 * @param text
	 *            the text for the label
	 * @param styles
	 */
	public Label(String text, Style... styles)
	{
		this(text, null, styles);
	}
	
	/**
	 * Creates a label with the specified image.
	 * 
	 * @param image
	 *            the image for the label
	 * @param styles
	 */
	public Label(Image image, Style... styles)
	{
		this("", image, styles);
	}
	
	/**
	 * Creates a label with the specified text and image.
	 * 
	 * @param text
	 *            the text for the label
	 * @param image
	 *            the image for the label
	 * @param styles
	 */
	public Label(String text, Image image, Style... styles)
	{
		setText(text);
		setImage(image);
		setStyles(styles);
		setToolTip("");
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, Label.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the text displayed by this label.
	 * 
	 * @return the text displayed by this label
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Sets the text displayed by this label.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param text
	 *            the text to be displayed by this label
	 */
	public void setText(String text)
	{
		String oldText = this.text;
		this.text = nullToEmpty(text);
		firePropertyChange(TEXT_PROPERTY, oldText, this.text);
	}
	
	public Closure<String> text()
	{
		return new Closure<String>()
		{
			@Override
			public void apply(String text)
			{
				setText(text);
			}
		};
	}
	
	/**
	 * Gets the image displayed by this label.
	 * 
	 * @return the image displayed by this label, or {@code null} if none
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Sets the image displayed by this label.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param image
	 *            the image to be displayed by this label, or {@code null} for none
	 */
	public void setImage(Image image)
	{
		Image oldImage = this.image;
		this.image = image;
		firePropertyChange(IMAGE_PROPERTY, oldImage, image);
	}
	
	/**
	 * Gets the tooltip text displayed by this label when the mouse hovers over it.
	 * 
	 * @return the tooltip text displayed by this label
	 */
	public String getToolTip()
	{
		return toolTip;
	}
	
	/**
	 * Sets the tooltip text displayed by this label when the mouse hovers over it.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param toolTip
	 *            the tooltip text to be displayed by this label
	 */
	public void setToolTip(String toolTip)
	{
		String oldToolTip = this.toolTip;
		this.toolTip = nullToEmpty(toolTip);
		firePropertyChange(TOOL_TIP_PROPERTY, oldToolTip, toolTip);
	}

	public Component getLabelFor()
	{
		return labelFor;
	}
	
	public void setLabelFor(Component labelFor)
	{
		Component oldLabelFor = this.labelFor;
		this.labelFor = labelFor;
		firePropertyChange(LABEL_FOR_PROPERTY, oldLabelFor, labelFor);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * Returns a string representation of this label and its properties.
	 * 
	 * @return a string representation of this label
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[text=" + getText() + "]";
	}
}
