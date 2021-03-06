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
package org.hobsoft.symmetry.spring.it;

import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.Text;
import org.hobsoft.symmetry.ui.Window;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC controller for integration tests.
 */
@Controller
@RequestMapping("/")
@ResponseBody
public class SpringController
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET, value = "window")
	public Component window()
	{
		return new Window();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "windowAndText")
	public Component windowAndText()
	{
		Window window = new Window();
		window.add(new Text("x"));
		return window;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "windowAndTextWithState")
	public Component windowAndTextWithState(WindowAndText window)
	{
		return window;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "textUnicode")
	public Component textUnicode()
	{
		Window window = new Window();
		window.add(new Text("\u20AC"));
		return window;
	}
}
