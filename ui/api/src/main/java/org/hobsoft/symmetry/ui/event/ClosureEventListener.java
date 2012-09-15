/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/ClosureEventListener.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.event;

import java.util.EventListener;
import java.util.EventObject;

import org.hobsoft.symmetry.ui.functor.Closure;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ClosureEventListener.java 86766 2011-04-11 15:06:48Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the event type
 */
public interface ClosureEventListener<T extends EventObject> extends EventListener
{
	Closure<? super T> getClosure();
}
