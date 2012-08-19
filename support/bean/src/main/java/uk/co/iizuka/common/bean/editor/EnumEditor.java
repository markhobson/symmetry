/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/EnumEditor.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EnumEditor.java 97934 2012-01-23 12:21:34Z mark@IIZUKA.CO.UK $
 * @param <E>
 *            the enum type
 */
public class EnumEditor<E extends Enum<E>> extends PropertyEditorSupport2
{
	// fields -----------------------------------------------------------------
	
	private final Class<E> enumType;

	// constructors -----------------------------------------------------------
	
	public EnumEditor(Class<E> enumType)
	{
		this.enumType = enumType;
	}

	// PropertyEditor methods -------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? value.toString() : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		E value = (text != null) ? Enum.valueOf(enumType, text) : null;
		
		setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return toStrings(enumType.getEnumConstants());
	}
	
	// private methods --------------------------------------------------------
	
	private static String[] toStrings(Object[] array)
	{
		String[] strings = new String[array.length];
		
		for (int i = 0; i < array.length; i++)
		{
			strings[i] = array[i].toString();
		}
		
		return strings;
	}
}
