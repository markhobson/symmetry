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

import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.ui.event.ActionListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface Action extends ActionListener
{
	// constants --------------------------------------------------------------
	
	String NAME_PROPERTY = "name";
	
	String DESCRIPTION_PROPERTY = "description";
	
	String IMAGE_PROPERTY = "image";
	
	String MNEMONIC_PROPERTY = "mnemonic";
	
	String ENABLED_PROPERTY = "enabled";
	
	// public methods ---------------------------------------------------------
	
	void addPropertyChangeListener(PropertyChangeListener listener);
	
	void removePropertyChangeListener(PropertyChangeListener listener);
	
	String getName();
	
	String getDescription();
	
	Image getImage();
	
	int getMnemonic();
	
	boolean isEnabled();
}
