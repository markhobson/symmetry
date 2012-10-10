/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/main/java/uk/co/iizuka/common/binding/kozo/DefaultFileChooserObservables.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo;

import javax.activation.DataSource;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.Observables;

import uk.co.iizuka.common.binding.kozo.KozoObservables.FileChooserObservables;
import uk.co.iizuka.kozo.ui.FileChooser;

/**
 * Default {@code FileChooserObservables} implementation.
 * 
 * @author Mark Hobson
 * @version $Id: DefaultFileChooserObservables.java 98458 2012-02-14 15:07:51Z mark@IIZUKA.CO.UK $
 * @see FileChooserObservables
 */
class DefaultFileChooserObservables extends DefaultComponentObservables implements FileChooserObservables
{
	// constructors -----------------------------------------------------------
	
	public DefaultFileChooserObservables(FileChooser fileChooser)
	{
		super(fileChooser);
	}
	
	// FileChooserObservables methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Observable<DataSource> file()
	{
		return Observables.bean(getComponent()).property(FileChooser.FILE_PROPERTY, DataSource.class);
	}
}
