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
package org.hobsoft.symmetry.demo.kozoset;

import org.hobsoft.symmetry.demo.kozoset.tab.ButtonTab;
import org.hobsoft.symmetry.demo.kozoset.tab.InputTab;
import org.hobsoft.symmetry.demo.kozoset.tab.LabelTab;
import org.hobsoft.symmetry.demo.kozoset.tab.LayoutTab;
import org.hobsoft.symmetry.demo.kozoset.tab.TableTab;
import org.hobsoft.symmetry.demo.kozoset.tab.TreeTab;
import org.hobsoft.symmetry.demo.kozoset.tab.WizardTab;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.Window;


/**
 * The KozoSet demo.
 * 
 * @author Mark Hobson
 */
public class KozoSetWindow extends Window
{
	// constructors -----------------------------------------------------------
	
	public KozoSetWindow()
	{
		setTitle("KozoSet");
		
		add(
			new Label("KozoSet 0.6.0-SNAPSHOT"),
			new TabBox(
				new LabelTab(),
				new ButtonTab(),
				new InputTab(),
				new LayoutTab(),
				new TableTab(),
				new TreeTab(),
				new WizardTab()
			)
		);
		
		setVisible(true);
	}
}
