/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/test/java/uk/co/iizuka/kozo/ui/html/hydrate/DummyDataSource.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyDataSource.java 98452 2012-02-14 15:00:23Z mark@IIZUKA.CO.UK $
 */
public class DummyDataSource implements DataSource
{
	// DataSource methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException
	{
		return new ByteArrayInputStream(new byte[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getOutputStream() throws IOException
	{
		throw new IOException("Dummy data source is read-only");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return "application/octet-stream";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "dummy";
	}
}
