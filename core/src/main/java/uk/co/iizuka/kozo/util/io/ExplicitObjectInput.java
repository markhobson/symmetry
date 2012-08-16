/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/ExplicitObjectInput.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.IOException;
import java.io.ObjectInput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: ExplicitObjectInput.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public interface ExplicitObjectInput extends ObjectInput
{
	// TODO: genericise?
	
	Object readObject(Class<?> klass) throws ClassNotFoundException, IOException;
}
