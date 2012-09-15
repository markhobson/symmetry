/*
 * $HeadURL$
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.support.codec.test.A;
import org.hobsoft.symmetry.support.codec.test.B;
import org.junit.Test;

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
