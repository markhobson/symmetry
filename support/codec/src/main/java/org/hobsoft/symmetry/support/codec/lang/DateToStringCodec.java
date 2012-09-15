/*
 * $HeadURL: https://svn.iizuka.co.uk/common/codec/tags/1.2.0-beta-1/src/main/java/uk/co/iizuka/common/codec/lang/DateToStringCodec.java $
 * 
 * (c) 2010 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.codec.lang;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.DecoderException;
import org.hobsoft.symmetry.support.codec.EncoderException;

/**
 * Codec that encodes a date to a string and vice-versa.
 * 
 * @author Mark Hobson
 */
class DateToStringCodec implements Codec<Date, String>
{
	// TODO: move to util package as per java.util.Date?
	
	// constants --------------------------------------------------------------
	
	public static final DateToStringCodec INSTANCE = new DateToStringCodec();
	
	private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat();
	
	// fields -----------------------------------------------------------------
	
	private final DateFormat dateFormat;
	
	// constructors -----------------------------------------------------------
	
	public DateToStringCodec()
	{
		this((DateFormat) null);
	}
	
	public DateToStringCodec(String pattern)
	{
		this(new SimpleDateFormat(pattern));
	}
	
	public DateToStringCodec(DateFormat dateFormat)
	{
		if (dateFormat == null)
		{
			dateFormat = DEFAULT_DATE_FORMAT;
		}
		
		this.dateFormat = dateFormat;
	}
	
	// Encoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public String encode(Date date) throws EncoderException
	{
		if (date == null)
		{
			return String.valueOf(date);
		}
		
		return dateFormat.format(date);
	}
	
	// Decoder methods ----------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public Date decode(String string) throws DecoderException
	{
		if (string == null)
		{
			return null;
		}
		
		ParsePosition position = new ParsePosition(0);
		
		Date value = dateFormat.parse(string, position);
		
		// also throw exception if all input not consumed
		if (value == null || position.getIndex() != string.length())
		{
			throw new DecoderException("Cannot parse date: " + string);
		}
		
		return value;
	}
}
