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

import java.util.Map;

/**
 * Factory class for building codecs.
 * 
 * @author Mark Hobson
 */
public final class Codecs
{
	// constructors -----------------------------------------------------------
	
	private Codecs()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static <X, Y> Codec<X, Y> forMap(Map<X, Y> map)
	{
		return new MapCodec<X, Y>(map);
	}
	
	public static <X, Y> Codec<X, Y> forMap(Map<X, Y> encodingMap, Map<Y, X> decodingMap)
	{
		return new MapCodec<X, Y>(encodingMap, decodingMap);
	}
}
