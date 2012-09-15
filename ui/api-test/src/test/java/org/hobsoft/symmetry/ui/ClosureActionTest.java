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

import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.functor.Closure;
import org.hobsoft.symmetry.ui.test.DummyComponent;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests {@code ClosureAction}.
 * 
 * @author Mark Hobson
 * @see ClosureAction
 */
@RunWith(JMock.class)
public class ClosureActionTest
{
	// fields -----------------------------------------------------------------
	
	private final Mockery mockery = new JUnit4Mockery();

	// tests ------------------------------------------------------------------
	
	@Test
	public void actionPerformed()
	{
		@SuppressWarnings("unchecked")
		final Closure<ActionEvent> closure = mockery.mock(Closure.class);
		final ActionEvent event = new ActionEvent(new DummyComponent());

		mockery.checking(new Expectations() { {
			oneOf(closure).apply(event);
		} });
		
		ClosureAction action = new ClosureAction();
		action.setClosure(closure);
		
		action.actionPerformed(event);
	}
}
