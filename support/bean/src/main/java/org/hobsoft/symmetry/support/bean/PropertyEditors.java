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
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.hobsoft.symmetry.support.bean.editor.BooleanEditor;
import org.hobsoft.symmetry.support.bean.editor.ByteEditor;
import org.hobsoft.symmetry.support.bean.editor.CharacterEditor;
import org.hobsoft.symmetry.support.bean.editor.DateEditor;
import org.hobsoft.symmetry.support.bean.editor.DoubleEditor;
import org.hobsoft.symmetry.support.bean.editor.EnumEditor;
import org.hobsoft.symmetry.support.bean.editor.FloatEditor;
import org.hobsoft.symmetry.support.bean.editor.IntegerEditor;
import org.hobsoft.symmetry.support.bean.editor.LongEditor;
import org.hobsoft.symmetry.support.bean.editor.NumberEditor;
import org.hobsoft.symmetry.support.bean.editor.ShortEditor;
import org.hobsoft.symmetry.support.bean.editor.StringEditor;
import org.hobsoft.symmetry.support.bean.editor.UrlEditor;

/**
 * 
 * 
 * @author Mark Hobson
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
