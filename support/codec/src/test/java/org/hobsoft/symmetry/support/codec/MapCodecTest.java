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

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.B;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests {@code MapCodec}.
 * 
 * @author Mark Hobson
 * @see MapCodec
 */
public class MapCodecTest
{
	// tests ------------------------------------------------------------------
	
	@Test(expected = IllegalArgumentException.class)
	public void newMapCodecWithOneSurjectiveMap() throws EncoderException
	{
		Map<A, B> map = map(new A("a1"), new B("b"), new A("a2"), new B("b"));
		new MapCodec<A, B>(map);
	}
	
	@Test
	public void encodeWithOneMap() throws EncoderException
	{
		Map<A, B> map = Collections.singletonMap(new A("a"), new B("b"));
		Codec<A, B> codec = new MapCodec<A, B>(map);
		assertEquals(new B("b"), codec.encode(new A("a")));
	}
	
	@Test
	public void encodeWithTwoMaps() throws EncoderException
	{
		Map<A, B> encodingMap = Collections.singletonMap(new A("a"), new B("b"));
		Codec<A, B> codec = new MapCodec<A, B>(encodingMap, null);
		assertEquals(new B("b"), codec.encode(new A("a")));
	}
	
	@Test
	public void encodeWhenMissing() throws EncoderException
	{
		Codec<A, B> codec = new MapCodec<A, B>(Collections.<A, B>emptyMap(), null);
		assertNull(codec.encode(new A("a")));
	}
	
	@Test
	public void decodeWithOneMap() throws DecoderException
	{
		Map<A, B> map = Collections.singletonMap(new A("a"), new B("b"));
		Codec<A, B> codec = new MapCodec<A, B>(map);
		assertEquals(new A("a"), codec.decode(new B("b")));
	}
	
	@Test
	public void decodeWithTwoMaps() throws DecoderException
	{
		Map<B, A> decodingMap = Collections.singletonMap(new B("b"), new A("a"));
		Codec<A, B> codec = new MapCodec<A, B>(null, decodingMap);
		assertEquals(new A("a"), codec.decode(new B("b")));
	}
	
	@Test
	public void decodeWhenMissing() throws DecoderException
	{
		Codec<A, B> codec = new MapCodec<A, B>(null, Collections.<B, A>emptyMap());
		assertNull(codec.decode(new B("b")));
	}
	
	// private methods --------------------------------------------------------
	
	private static <K, V> Map<K, V> map(K key1, V value1, K key2, V value2)
	{
		Map<K, V> map = new HashMap<K, V>();
		map.put(key1, value1);
		map.put(key2, value2);
		return map;
	}
}
