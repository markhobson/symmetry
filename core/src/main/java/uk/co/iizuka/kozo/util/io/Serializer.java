/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/Serializer.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.util.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Serializer.java 93651 2011-10-06 20:39:46Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the type that this serializer can read and write
 */
public interface Serializer<T>
{
	T readObject(ObjectInput in) throws IOException, ClassNotFoundException;
	
	void writeObject(ObjectOutput out, T object) throws IOException;
}
