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
package org.hobsoft.symmetry.test.hydrate;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hobsoft.symmetry.hydrate.ComponentRenderKit;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.test.state.StubStateCodec;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Provides support for testing component render kits.
 * 
 * @author Mark Hobson
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
