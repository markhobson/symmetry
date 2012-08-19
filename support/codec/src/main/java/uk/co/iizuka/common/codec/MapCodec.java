/*
 * $HeadURL$
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.codec;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id$
 * @param <X>
 *            the object type that this codec can encode
 * @param <Y>
 *            the object type that this codec can decode
 */
class MapCodec<X, Y> implements Codec<X, Y>
{
	// fields -----------------------------------------------------------------
	
	private final Map<X, Y> encodingMap;
	
	private final Map<Y, X> decodingMap;
	
	// constants --------------------------------------------------------------
	
	public MapCodec(Map<X, Y> map)
	{
		this(map, inverse(map));
	}
	
	public MapCodec(Map<X, Y> encodingMap, Map<Y, X> decodingMap)
	{
		if (encodingMap == null)
		{
			encodingMap = Collections.emptyMap();
		}
		
		if (decodingMap == null)
		{
			decodingMap = Collections.emptyMap();
		}
		
		this.encodingMap = encodingMap;
		this.decodingMap = decodingMap;
	}
	
	// Encoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Y encode(X object) throws EncoderException
	{
		return encodingMap.get(object);
	}
	
	// Decoder methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public X decode(Y object) throws DecoderException
	{
		return decodingMap.get(object);
	}
	
	// private methods --------------------------------------------------------
	
	private static <K, V> Map<V, K> inverse(Map<K, V> map)
	{
		Map<V, K> inverseMap = new HashMap<V, K>();
		
		for (Entry<K, V> entry : map.entrySet())
		{
			K key = entry.getKey();
			V value = entry.getValue();
			
			if (inverseMap.put(value, key) != null)
			{
				throw new IllegalArgumentException("Map is not bijective: " + map);
			}
		}
		
		return inverseMap;
	}
}
