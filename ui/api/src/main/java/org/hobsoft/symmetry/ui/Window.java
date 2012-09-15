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
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import static com.google.common.base.Strings.nullToEmpty;

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
