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

import org.hobsoft.symmetry.state.DelegatingStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.TextBox;

import static org.hobsoft.symmetry.support.bean.Properties.getDescriptor;

/**
 * Symmetry UI component state codec for HTML form data sets.
 * <p>
 * This codec decorator handles encoding and decoding state that is outside of a typical {@code StateCodec}'s control
 * as it is part of the HTML specification.  For example, HTML text input controls must always represent their value as
 * CDATA, even if {@code String} bean properties are encoded differently elsewhere.  Likewise, HTML checkbox controls
 * must always represent their value as some CDATA value for checked and {@code null} for unchecked.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/html4/interact/forms.html#h-17.13.3">HTML 4.01 Specification</a>
 */
public class HtmlFormStateCodec extends DelegatingStateCodec
{
	// constructors -----------------------------------------------------------
	
	public HtmlFormStateCodec(StateCodec delegate)
	{
		super(decorate(delegate));
	}
	
	// private methods --------------------------------------------------------
	
	private static StateCodec decorate(StateCodec delegate)
	{
		delegate = new HtmlCheckboxControlStateCodec(delegate, getDescriptor(CheckBox.class,
			Selectable.SELECTED_PROPERTY));
		
		delegate = new HtmlTextControlStateCodec(delegate, getDescriptor(TextBox.class, TextBox.TEXT_PROPERTY));
		
		return delegate;
	}
}
