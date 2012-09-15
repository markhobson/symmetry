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

import org.hobsoft.symmetry.support.bean.AbstractBean;
import org.hobsoft.symmetry.ui.event.ActionEvent;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultAction extends AbstractBean implements Action
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	private String description;
	
	private Image image;
	
	private int mnemonic;
	
	private boolean enabled;
	
	// constructors -----------------------------------------------------------
	
	public DefaultAction()
	{
		this(null);
	}
	
	public DefaultAction(String name)
	{
		if (name != null)
		{
			setName(name);
		}
		
		setEnabled(true);
	}
	
	// Action methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMnemonic()
	{
		return mnemonic;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
	
	// ActionListener methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// no-op
	}
	
	// public methods ---------------------------------------------------------
	
	public void setName(String name)
	{
		String oldName = this.name;
		this.name = name;
		firePropertyChange(NAME_PROPERTY, oldName, name);
	}
	
	public void setDescription(String description)
	{
		String oldDescription = this.description;
		this.description = description;
		firePropertyChange(DESCRIPTION_PROPERTY, oldDescription, description);
	}
	
	public void setImage(Image image)
	{
		Image oldImage = this.image;
		this.image = image;
		firePropertyChange(IMAGE_PROPERTY, oldImage, image);
	}
	
	public void setMnemonic(int mnemonic)
	{
		int oldMnemonic = this.mnemonic;
		this.mnemonic = mnemonic;
		firePropertyChange(MNEMONIC_PROPERTY, oldMnemonic, mnemonic);
	}
	
	public void setEnabled(boolean enabled)
	{
		boolean oldEnabled = this.enabled;
		this.enabled = enabled;
		firePropertyChange(ENABLED_PROPERTY, oldEnabled, enabled);
	}
}
