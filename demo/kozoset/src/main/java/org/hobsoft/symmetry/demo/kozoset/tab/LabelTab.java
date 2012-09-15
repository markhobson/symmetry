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
package org.hobsoft.symmetry.demo.kozoset.tab;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HtmlLabel;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.VBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public LabelTab()
	{
		// TODO: use chainable setters when supported
		
		Box box = new VBox(
			new GroupBox("Label",
				new Label("A Label can be a simple string like this."),
				new HtmlLabel(
					"Labels can also contain <abbr title=\"Extensible HyperText Markup Language\">XHTML</abbr> tags "
					+ "to:"
					+ "<ul>"
						+ "<li>"
							+ "<em>achieve</em>;"
							+ "<ul>"
								+ "<li>"
									+ "<code>simple</code>;"
									+ "<ul>"
										+ "<li><strong>styling</strong>.</li>"
									+ "</ul>"
								+ "</li>"
							+ "</ul>"
						+ "</li>"
					+ "</ul>"
				),
				new HtmlLabel("This can be used to provide hyperlinks to "
					+ "<a href=\"https://github.com/markhobson/symmetry\">cool places</a>."),
				new HtmlLabel("Unrecognised namespaces are also <format dir=\"c:\" xmlns=\"ant\">escaped accordingly"
					+ "</format>.")
			)
		);
		
		setText("Label");
		setComponent(box);
	}
}
