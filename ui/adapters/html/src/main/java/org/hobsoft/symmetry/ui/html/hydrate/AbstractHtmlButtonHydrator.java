/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractHtmlButtonHydrator.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Button;

/**
 * Phased base hydrator for dehydrating and rehydrating a {@code Button} component using HTML.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractHtmlButtonHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractHtmlButtonHydrator<T extends Button> extends HtmlLabelHydrator<T>
{
	// no-op
}
