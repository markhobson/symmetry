/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/main/java/uk/co/iizuka/kozo/util/io/SerializerFactory.java $
 * 
 * (c) 2009 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface SerializerFactory
{
	<T> Serializer<T> getSerializer(Class<T> type);
}
