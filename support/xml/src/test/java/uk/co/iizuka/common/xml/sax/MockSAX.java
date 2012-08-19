/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/test/java/uk/co/iizuka/common/xml/sax/MockSAX.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.xml.sax;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.lib.legacy.ClassImposteriser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.ext.LexicalHandler;

/**
 * Provides a builder to generate mock SAX handlers that expect a series of SAX events to be invoked. These handlers can
 * then be used to receive SAX events and verify that they were invoked as expected.
 * 
 * This class is typically used to test classes that produce SAX events.
 * 
 * @author Mark Hobson
 * @version $Id: MockSAX.java 69822 2010-01-21 17:57:20Z mark@IIZUKA.CO.UK $
 */
public class MockSAX extends DefaultHandler2
{
	// classes ----------------------------------------------------------------
	
	private static class AttributesEqual extends TypeSafeMatcher<Attributes>
	{
		// fields -------------------------------------------------------------
		
		private Attributes expected;
		
		// constructors -------------------------------------------------------
		
		public AttributesEqual(Attributes expected)
		{
			this.expected = expected;
		}
		
		// TypeSafeMatcher methods --------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean matchesSafely(Attributes actual)
		{
			try
			{
				SAXAssert.assertEquals(expected, actual);
				return true;
			}
			catch (AssertionError error)
			{
				return false;
			}
		}
		
		// SelfDescribing methods ---------------------------------------------

		/**
		 * {@inheritDoc}
		 */
		public void describeTo(Description description)
		{
			description.appendText("[");
			
			for (int i = 0; i < expected.getLength(); i++)
			{
				if (i > 0)
				{
					description.appendText(" ");
				}
				
				description.appendValue(expected.getQName(i));
				description.appendText("=");
				description.appendValue(expected.getValue(i));
			}
			
			description.appendValue("]");
		}
	}
	
	// fields -----------------------------------------------------------------
	
	private final Mockery context;
	
	private final Sequence sequence;
	
	private final DefaultHandler2 mock;
	
	// constructors -----------------------------------------------------------
	
	public MockSAX()
	{
		context = new Mockery();
		context.setImposteriser(ClassImposteriser.INSTANCE);
		
		sequence = context.sequence("sequence");
		mock = context.mock(DefaultHandler2.class);
	}
	
	// ContentHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		context.checking(new Expectations() { {
			one(mock).startDocument(); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		context.checking(new Expectations() { {
			one(mock).endDocument(); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startPrefixMapping(final String prefix, final String uri) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).startPrefixMapping(prefix, uri); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endPrefixMapping(final String prefix) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).endPrefixMapping(prefix); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
		throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).startElement(with(equal(uri)), with(equal(localName)), with(equal(qName)),
				with(attributesEq(attributes)));
			inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).endElement(uri, localName, qName); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).characters(ch, start, length); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).ignorableWhitespace(ch, start, length); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processingInstruction(final String target, final String data) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).processingInstruction(target, data); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void skippedEntity(final String name) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).skippedEntity(name); inSequence(sequence);
		} });
	}
	
	// LexicalHandler methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDTD(final String name, final String publicId, final String systemId) throws SAXException
	{
		context.checking(new Expectations() { {
			one(mock).startDTD(name, publicId, systemId); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDTD() throws SAXException
	{
		context.checking(new Expectations() { {
			one(mock).endDTD(); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startEntity(final String name) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).startEntity(name); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endEntity(final String name) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).endEntity(name); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startCDATA() throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).startCDATA(); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endCDATA() throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).endCDATA(); inSequence(sequence);
		} });
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void comment(final char[] ch, final int start, final int length) throws SAXException
	{
		context.checking(new Expectations() { {
			atLeast(1).of(mock).comment(ch, start, length); inSequence(sequence);
		} });
	}
	
	// public methods ---------------------------------------------------------
	
	public ContentHandler getContentHandler()
	{
		return getDefaultHandler2();
	}
	
	public LexicalHandler getLexicalHandler()
	{
		return getDefaultHandler2();
	}
	
	public void verify()
	{
		context.assertIsSatisfied();
	}
	
	// private methods ------------------------------------------------------

	private DefaultHandler2 getDefaultHandler2()
	{
		return mock;
	}
	
	private static Matcher<Attributes> attributesEq(Attributes attributes)
	{
		return new AttributesEqual(attributes);
	}
}
