/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlSpacerHydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import org.hobsoft.symmetry.ui.Spacer;
import org.hobsoft.symmetry.ui.common.hydrate.PhasedBeanHydrator;

/**
 * Phased hydrator that dehydrates and rehydrates a {@code Spacer} component using state alone.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlSpacerHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Spacer
 * @param <T>
 *            the spacer type this visitor can visit
 */
public class HtmlSpacerHydrator<T extends Spacer> extends PhasedBeanHydrator<T>
{
	// no-op
}
