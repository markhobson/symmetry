/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractHtmlButtonDehydrator.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.Selectable;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Base hydrator for dehydrating a {@code Button} component to HTML.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractHtmlButtonDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractHtmlButtonDehydrator<T extends Button> extends NullHierarchicalComponentHydrator<T>
{
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getButtonCssClass(T button)
	{
		CssClassBuilder builder = new CssClassBuilder("button");
		
		if (!button.isEnabled())
		{
			builder.append("button-disabled");
		}
		
		if ((button instanceof Selectable) && ((Selectable) button).isSelected())
		{
			builder.append("button-selected");
		}
		
		return builder;
	}
}
