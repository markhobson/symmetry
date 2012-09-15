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

import org.hobsoft.symmetry.state.NullStateCodec;
import org.junit.Test;

/**
 * Tests {@code HtmlFormStateCodec}.
 * 
 * @author Mark Hobson
 * @see HtmlFormStateCodec
 */
public class HtmlFormStateCodecTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void newHtmlFormStateCodec()
	{
		new HtmlFormStateCodec(new NullStateCodec());
	}
}
