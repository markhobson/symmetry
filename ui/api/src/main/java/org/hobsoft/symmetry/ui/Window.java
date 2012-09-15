/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Window.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static com.google.common.base.Strings.nullToEmpty;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Window extends Box
{
	// constants --------------------------------------------------------------
	
	public static final String TITLE_PROPERTY = "title";
	
	public static final String IMAGE_PROPERTY = "image";
	
	private static final String DEFAULT_TITLE = "";
	
	private static final Orientation DEFAULT_ORIENTATION = Orientation.VERTICAL;
	
	// fields -----------------------------------------------------------------
	
	private String title;
	
	private Image image;
	
	// constructors -----------------------------------------------------------
	
	public Window()
	{
		this(DEFAULT_TITLE);
	}
	
	public Window(String title)
	{
		this(title, DEFAULT_ORIENTATION);
	}
	
	public Window(Orientation orientation)
	{
		this(DEFAULT_TITLE, orientation);
	}
	
	public Window(String title, Orientation orientation)
	{
		super(orientation);
		
		setTitle(title);
		setVisible(false);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, Window.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		String oldTitle = this.title;
		this.title = nullToEmpty(title);
		firePropertyChange(TITLE_PROPERTY, oldTitle, title);
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setImage(Image image)
	{
		Image oldImage = this.image;
		this.image = image;
		firePropertyChange(IMAGE_PROPERTY, oldImage, image);
	}
}
