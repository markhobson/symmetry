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
package org.hobsoft.symmetry.support.xml.dtd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class StringScannerTest
{
	@Test
	public void emptyString()
	{
		StringScanner scanner = new StringScanner("", ",");
		assertFalse(scanner.hasNext());
	}

	@Test
	public void whitespaceString()
	{
		StringScanner scanner = new StringScanner("   ", ",");
		assertTrue(scanner.hasNext());
		assertEquals("   ", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void whitespaceStringIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   ", ",", true);
		assertFalse(scanner.hasNext());
	}

	@Test
	public void singleDelimiterString()
	{
		StringScanner scanner = new StringScanner(",", ",");
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void singleDelimiterStringWithWhitespace()
	{
		StringScanner scanner = new StringScanner("   ,   ", ",");
		assertTrue(scanner.hasNext());
		assertEquals("   ", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("   ", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void singleDelimiterStringWithWhitespaceIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   ,   ", ",", true);
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void stringStartsWithDelimiter()
	{
		StringScanner scanner = new StringScanner(",apple", ",");
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void stringStartsWithDelimiterWithWhitespace()
	{
		StringScanner scanner = new StringScanner("   ,   apple   ", ",");
		assertTrue(scanner.hasNext());
		assertEquals("   ", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("   apple   ", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void stringStartsWithDelimiterWithWhitespaceIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   ,   apple   ", ",", true);
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertFalse(scanner.hasNext());
	}

	@Test
	public void stringEndsWithDelimiter()
	{
		StringScanner scanner = new StringScanner("apple,", ",");
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void stringEndsWithDelimiterWithWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   ", ",");
		assertTrue(scanner.hasNext());
		assertEquals("   apple   ", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("   ", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void stringEndsWithDelimiterWithWhitespaceIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   ", ",", true);
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void noDelimiters()
	{
		StringScanner scanner = new StringScanner("apple,banana,carrot", "");
		assertTrue(scanner.hasNext());
		assertEquals("apple,banana,carrot", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void noDelimitersWithWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   banana   ,   carrot   ", "");
		assertTrue(scanner.hasNext());
		assertEquals("   apple   ,   banana   ,   carrot   ", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void noDelimitersWithWhitespaceIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   banana   ,   carrot   ", "", true);
		assertTrue(scanner.hasNext());
		assertEquals("apple   ,   banana   ,   carrot", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void multipleDelimiters()
	{
		StringScanner scanner = new StringScanner("apple,banana;carrot", ",;");
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("banana", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(";", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("carrot", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void multipleDelimitersWithWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   banana   ;   carrot   ", ",;");
		assertTrue(scanner.hasNext());
		assertEquals("   apple   ", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("   banana   ", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(";", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("   carrot   ", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void multipleDelimitersWithWhitespaceIgnoringWhitespace()
	{
		StringScanner scanner = new StringScanner("   apple   ,   banana   ;   carrot   ", ",;", true);
		assertTrue(scanner.hasNext());
		assertEquals("apple", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("banana", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals(";", scanner.next());
		assertTrue(scanner.hasNext());
		assertEquals("carrot", scanner.next());
		assertFalse(scanner.hasNext());
	}
	
	@Test
	public void hasNext()
	{
		StringScanner scanner = new StringScanner("apple,banana", ",");
		assertTrue(scanner.hasNext("apple"));
		assertEquals("apple", scanner.next());
		assertTrue(scanner.hasNext(","));
		assertEquals(",", scanner.next());
		assertTrue(scanner.hasNext("banana"));
		assertEquals("banana", scanner.next());
		assertFalse(scanner.hasNext("carrot"));
		assertFalse(scanner.hasNext());
	}
}
