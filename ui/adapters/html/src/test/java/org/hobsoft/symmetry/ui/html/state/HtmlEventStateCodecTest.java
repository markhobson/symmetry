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
package org.hobsoft.symmetry.ui.html.state;

import org.hobsoft.symmetry.state.EncodedState;
import org.hobsoft.symmetry.state.State;
import org.hobsoft.symmetry.state.StateException;
import org.hobsoft.symmetry.test.state.StubStateCodec;
import org.hobsoft.symmetry.ui.html.state.HtmlEventStateCodec.Parameters;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code HtmlEventStateCodec}.
 * 
 * @author Mark Hobson
 * @see HtmlEventStateCodec
 */
public class HtmlEventStateCodecTest
{
	// fields -----------------------------------------------------------------
	
	private StubStateCodec delegate;
	
	private HtmlEventStateCodec stateCodec;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		delegate = new StubStateCodec();
		
		stateCodec = new HtmlEventStateCodec(delegate);
	}

	// tests ------------------------------------------------------------------
	
	@Test
	public void encodeStateWhenNotEvent() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("x"), stateCodec.encodeState(createState(false)));
	}
	
	@Test
	public void encodeStateWhenEvent() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("document.location = 'x'"), stateCodec.encodeState(createState(true)));
	}

	@Test
	public void encodeStateWhenEventNotSpecified() throws StateException
	{
		delegate.setEncodedState(new EncodedState("x"));
		
		assertEquals(new EncodedState("x"), stateCodec.encodeState(new State()));
	}
	
	// private methods --------------------------------------------------------
	
	private static State createState(boolean event)
	{
		State state = new State();
		
		Parameters parameters = new Parameters();
		parameters.setEvent(event);
		state.setParameter("org.hobsoft.symmetry.ui.html.state.HtmlEventStateCodec$Parameters", parameters);
		
		return state;
	}
}
