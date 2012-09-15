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

import javax.activation.DataSource;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class FileChooser extends Component
{
	// constants --------------------------------------------------------------
	
	public static final String FILE_PROPERTY = "file";

	// fields -----------------------------------------------------------------
	
	private DataSource file;
	
	// constructors -----------------------------------------------------------
	
	public FileChooser()
	{
		// default constructor
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, FileChooser.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public DataSource getFile()
	{
		return file;
	}
	
	public void setFile(DataSource file)
	{
		DataSource oldFile = this.file;
		this.file = file;
		// TODO: fire property change event when state codec can handle it
//		firePropertyChange(FILE_PROPERTY, oldFile, this.file);
	}
}
