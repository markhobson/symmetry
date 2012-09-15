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
package org.hobsoft.symmetry.ui.html;

import javax.xml.stream.XMLStreamException;

import org.hobsoft.symmetry.xml.test.AbstractStaxTest;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@code HtmlUtils}.
 * 
 * @author Mark Hobson
 * @see HtmlUtils
 */
public class HtmlUtilsTest extends AbstractStaxTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void writeToolTipWithNull() throws XMLStreamException
	{
		getXMLStreamWriter().writeEmptyElement("p");
		HtmlUtils.writeToolTip(getXMLStreamWriter(), null);
		getXMLStreamWriter().writeEndDocument();
		
		assertXml("<p/>");
	}
	
	@Test
	public void writeToolTipWithEmptyString() throws XMLStreamException
	{
		getXMLStreamWriter().writeEmptyElement("p");
		HtmlUtils.writeToolTip(getXMLStreamWriter(), "");
		getXMLStreamWriter().writeEndDocument();
		
		assertXml("<p/>");
	}
	
	@Test
	public void writeToolTipWithString() throws XMLStreamException
	{
		getXMLStreamWriter().writeEmptyElement("p");
		HtmlUtils.writeToolTip(getXMLStreamWriter(), "text");
		getXMLStreamWriter().writeEndDocument();
		
		assertXml("<p title=\"text\"/>");
	}
	
	@Test
	public void isHtmlFragmentWithHtmlFragment()
	{
		assertTrue(HtmlUtils.isHtmlFragment("<a>link</a>"));
	}
	
	@Test
	public void isHtmlFragmentWithNotWellFormedHtml()
	{
		assertTrue(HtmlUtils.isHtmlFragment("<p>"));
	}
	
	@Test
	public void isHtmlFragmentWithHtmlDocument()
	{
		assertFalse(HtmlUtils.isHtmlFragment("<html><body>a</body></html>"));
	}
	
	@Test
	public void isHtmlFragmentWithHtmlDocumentAndDoctype()
	{
		assertFalse(HtmlUtils.isHtmlFragment("<!DOCTYPE html><html><body>a</body></html>"));
	}
	
	@Test
	public void isHtmlFragmentWithHtmlDocumentAndDoctypeAndExternalId()
	{
		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
			+ "<html><body>a</body></html>";
		
		assertFalse(HtmlUtils.isHtmlFragment(html));
	}
	
	@Test
	public void writeHtmlFragment() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a>link</a>");
		
		assertXml("<a>link</a>");
	}
	
	@Test
	public void writeHtmlFragmentNestedElements() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<p><a>link</a></p>");
		
		assertXml("<p><a>link</a></p>");
	}
	
	@Test
	public void writeHtmlFragmentPrecedingText() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "text<a>link</a>");
		
		assertXml("text<a>link</a>");
	}
	
	@Test
	public void writeHtmlFragmentFollowingText() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a>link</a>text");
		
		assertXml("<a>link</a>text");
	}
	
	// TODO: fix
	@Ignore
	@Test(expected = XMLStreamException.class)
	public void writeHtmlFragmentNotWellFormed() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a>link");
	}
	
	@Test
	public void writeHtmlFragmentInHtmlDefaultNamespace() throws XMLStreamException
	{
		getXMLStreamWriter().setDefaultNamespace("http://www.w3.org/1999/xhtml");
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a>link</a>");
		
		assertXml("<a>link</a>");
	}
	
	@Test
	public void writeHtmlFragmentInHtmlDefaultNamespaceAndElement() throws XMLStreamException
	{
		getXMLStreamWriter().writeStartElement("html");
		getXMLStreamWriter().writeDefaultNamespace("http://www.w3.org/1999/xhtml");
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a>link</a>");
		getXMLStreamWriter().writeEndElement();
		
		assertXml("<html xmlns=\"http://www.w3.org/1999/xhtml\"><a>link</a></html>");
	}
	
	// TODO: fix
	@Ignore
	@Test
	public void writeHtmlFragmentWrongNamespace() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a xmlns=\"uri0/\">link</a>");
		
		assertXml("&lt;a xmlns=\"uri0/\"&gt;link&lt;/a&gt;");
	}
	
	@Test
	public void writeHtmlFragmentExplicitNamespace() throws XMLStreamException
	{
		HtmlUtils.writeHtmlFragment(getXMLStreamWriter(), "<a xmlns=\"http://www.w3.org/1999/xhtml\">link</a>");
		
		// TODO: expect no superfluous xmlns
		assertXml("<a xmlns=\"http://www.w3.org/1999/xhtml\">link</a>");
	}
}
