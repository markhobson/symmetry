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
package org.hobsoft.symmetry.util.lang;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ValuedRunnableTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void runnableValue()
	{
		final Object expected = new Object();
		ValuedRunnable runnable = new ValuedRunnable()
		{
			@Override
			public void run()
			{
				setValue(expected);
			}
		};
		runnable.run();
		Object actual = runnable.getValue();
		assertEquals(expected, actual);
	}
}
