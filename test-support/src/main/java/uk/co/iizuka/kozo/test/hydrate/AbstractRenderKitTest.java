/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/test-support/src/main/java/uk/co/iizuka/kozo/test/hydrate/AbstractRenderKitTest.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.test.hydrate;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import uk.co.iizuka.kozo.hydrate.ComponentRenderKit;
import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.state.EncodedState;
import uk.co.iizuka.kozo.state.State;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.test.state.StubStateCodec;

/**
 * Provides support for testing component render kits.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractRenderKitTest.java 95640 2011-11-29 12:28:54Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type that this test tests
 */
public abstract class AbstractRenderKitTest<T>
{
	// fields -----------------------------------------------------------------
	
	private ByteArrayOutputStream out;
	
	private StubStateCodec stateCodec;
	
	private DehydrationContext dehydrationContext;
	
	private RehydrationContext rehydrationContext;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpAbstractRenderKitTest()
	{
		out = new ByteArrayOutputStream();
		stateCodec = new StubStateCodec();
		
		dehydrationContext = new DehydrationContext(new State(), null, out);
		dehydrationContext.set(StateCodec.class, stateCodec);
		
		rehydrationContext = new RehydrationContext();
		rehydrationContext.set(StateCodec.class, stateCodec);
		rehydrationContext.set(State.class, new State());
	}

	// protected methods ------------------------------------------------------
	
	protected final StubStateCodec getStateCodec()
	{
		return stateCodec;
	}
	
	protected final DehydrationContext getDehydrationContext()
	{
		return dehydrationContext;
	}
	
	protected final RehydrationContext getRehydrationContext()
	{
		return rehydrationContext;
	}
	
	// TODO: remove the need for this by not duplicating encoded state
	protected final void setEncodedState(EncodedState encodedState)
	{
		rehydrationContext.setEncodedState(encodedState);
		stateCodec.setEncodedState(encodedState);
	}
	
	// TODO: remove the need for this by improving EncodedState
	protected final EncodedState createEncodedState(String parameterName, Object... parameterValues)
	{
		return new EncodedState(createParameters(parameterName, parameterValues));
	}
	
	protected void dehydrate(T component) throws HydrationException
	{
		createRenderKit().dehydrate(component, dehydrationContext);
	}
	
	protected void rehydrate(T component) throws HydrationException
	{
		createRenderKit().rehydrate(component, rehydrationContext);
	}
	
	protected final void assertDehydrate(String expected, T component) throws HydrationException
	{
		dehydrate(component);

		// TODO: use a specified charset rather than the platform default
		assertEquals(expected, out.toString());
	}
	
	protected abstract ComponentRenderKit<T> createRenderKit();

	// private methods --------------------------------------------------------
	
	private static Map<String, List<Object>> createParameters(String parameterName, Object... parameterValues)
	{
		return Collections.singletonMap(parameterName, Arrays.asList(parameterValues));
	}
}
