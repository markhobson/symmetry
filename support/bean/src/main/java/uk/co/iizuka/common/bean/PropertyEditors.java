/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/PropertyEditors.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import uk.co.iizuka.common.bean.editor.BooleanEditor;
import uk.co.iizuka.common.bean.editor.ByteEditor;
import uk.co.iizuka.common.bean.editor.CharacterEditor;
import uk.co.iizuka.common.bean.editor.DateEditor;
import uk.co.iizuka.common.bean.editor.DoubleEditor;
import uk.co.iizuka.common.bean.editor.EnumEditor;
import uk.co.iizuka.common.bean.editor.FloatEditor;
import uk.co.iizuka.common.bean.editor.IntegerEditor;
import uk.co.iizuka.common.bean.editor.LongEditor;
import uk.co.iizuka.common.bean.editor.NumberEditor;
import uk.co.iizuka.common.bean.editor.ShortEditor;
import uk.co.iizuka.common.bean.editor.StringEditor;
import uk.co.iizuka.common.bean.editor.UrlEditor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyEditors.java 100797 2012-04-25 14:00:11Z mark@IIZUKA.CO.UK $
 */
public final class PropertyEditors
{
	// TODO: rename methods to newXxx to reflect them always returning new instances?
	
	// constructors -----------------------------------------------------------
	
	private PropertyEditors()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static PropertyEditor forBoolean()
	{
		return new BooleanEditor();
	}
	
	public static PropertyEditor forByte()
	{
		return new ByteEditor();
	}
	
	public static PropertyEditor forCharacter()
	{
		return new CharacterEditor();
	}
	
	public static PropertyEditor forDouble()
	{
		return new DoubleEditor();
	}
	
	public static PropertyEditor forFloat()
	{
		return new FloatEditor();
	}
	
	public static PropertyEditor forInteger()
	{
		return new IntegerEditor();
	}
	
	public static PropertyEditor forLong()
	{
		return new LongEditor();
	}
	
	public static PropertyEditor forShort()
	{
		return new ShortEditor();
	}
	
	public static PropertyEditor forString()
	{
		return new StringEditor();
	}
	
	public static <E extends Enum<E>> PropertyEditor forEnum(Class<E> enumType)
	{
		return new EnumEditor<E>(enumType);
	}
	
	public static PropertyEditor forNumber()
	{
		return forNumber(NumberFormat.getInstance());
	}
	
	public static PropertyEditor forNumber(String pattern)
	{
		return forNumber(new DecimalFormat(pattern));
	}
	
	public static PropertyEditor forNumber(NumberFormat formatter)
	{
		return new NumberEditor(formatter);
	}
	
	public static PropertyEditor forDate()
	{
		return forDate(DateFormat.getInstance());
	}
	
	public static PropertyEditor forDate(String pattern)
	{
		return forDate(new SimpleDateFormat(pattern));
	}
	
	public static PropertyEditor forDate(DateFormat formatter)
	{
		return new DateEditor(formatter);
	}

	public static PropertyEditor forUrl()
	{
		return new UrlEditor();
	}
	
	public static PropertyEditor emptyStringToNull(PropertyEditor delegate)
	{
		return new EmptyStringPropertyEditor(delegate);
	}
}
