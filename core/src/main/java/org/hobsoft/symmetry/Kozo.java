/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/Kozo.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry;

import java.io.PrintStream;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Kozo implements Runnable
{
	// constants --------------------------------------------------------------
	
	private static final PrintStream ERR = System.err;
	
	// fields -----------------------------------------------------------------
	
	private final Object component;
	
	private final PeerManager peerManager;
	
	// constructors -----------------------------------------------------------
	
	public Kozo(String componentClassName, String peerManagerClassName) throws KozoException
	{
		try
		{
			component = getInstance(componentClassName, Object.class);
			peerManager = getInstance(peerManagerClassName, PeerManager.class);
		}
		catch (ClassNotFoundException exception)
		{
			throw new KozoException(exception);
		}
		catch (InstantiationException exception)
		{
			throw new KozoException(exception);
		}
		catch (IllegalAccessException exception)
		{
			throw new KozoException(exception);
		}
	}
	
	// Runnable ---------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run()
	{
		peerManager.registerComponent(component);
	}
	
	// private methods --------------------------------------------------------
	
	private static <T> T getInstance(String className, Class<T> instanceClass) throws ClassNotFoundException,
		InstantiationException, IllegalAccessException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> klass = classLoader.loadClass(className);
		Object object = klass.newInstance();
		return instanceClass.cast(object);
	}
	
	// main -------------------------------------------------------------------
	
	public static void main(String[] args) throws KozoException
	{
		if (args.length != 2)
		{
			ERR.println("Usage: java " + Kozo.class.getName() + " <component-class> <peer-manager-class>");
			System.exit(1);
		}
		
		String componentClassName = args[0];
		String peerManagerClassName = args[1];
		
		new Kozo(componentClassName, peerManagerClassName).run();
	}
}
