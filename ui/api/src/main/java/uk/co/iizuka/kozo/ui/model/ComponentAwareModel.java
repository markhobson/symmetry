/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/ComponentAwareModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

import uk.co.iizuka.kozo.ui.Component;

/**
 * 
 * @author Mark Hobson
 * @version $Id: ComponentAwareModel.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface ComponentAwareModel
{
	void setComponent(Component component);
}
