/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/FileChooser.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
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
