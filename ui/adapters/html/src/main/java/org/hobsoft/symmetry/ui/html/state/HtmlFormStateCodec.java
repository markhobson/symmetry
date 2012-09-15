/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HtmlFormStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import org.hobsoft.symmetry.state.DelegatingStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.ui.CheckBox;
import org.hobsoft.symmetry.ui.Selectable;
import org.hobsoft.symmetry.ui.TextBox;

import static org.hobsoft.symmetry.support.bean.Properties.getDescriptor;

/**
 * Kozo UI component state codec for HTML form data sets.
 * <p>
 * This codec decorator handles encoding and decoding state that is outside of a typical {@code StateCodec}'s control
 * as it is part of the HTML specification.  For example, HTML text input controls must always represent their value as
 * CDATA, even if {@code String} bean properties are encoded differently elsewhere.  Likewise, HTML checkbox controls
 * must always represent their value as some CDATA value for checked and {@code null} for unchecked.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlFormStateCodec.java 98453 2012-02-14 15:00:35Z mark@IIZUKA.CO.UK $
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
