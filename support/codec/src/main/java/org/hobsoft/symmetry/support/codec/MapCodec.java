/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.support.codec;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 
 * @author Mark Hobson
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
