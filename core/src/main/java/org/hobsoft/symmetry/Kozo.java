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
