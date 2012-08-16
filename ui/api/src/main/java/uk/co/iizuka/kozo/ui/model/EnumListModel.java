/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/EnumListModel.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.model;

/**
 * List model that exposes the constants of an enum.
 * 
 * @author Mark Hobson
 * @version $Id: EnumListModel.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <E>
 *            the enum type
 */
class EnumListModel<E extends Enum<E>> implements ListModel<E>
{
	// fields -----------------------------------------------------------------
	
	private final Class<E> enumType;

	// constructors -----------------------------------------------------------
	
	public EnumListModel(Class<E> enumType)
	{
		this.enumType = enumType;
	}

	// ListModel methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getItem(int index)
	{
		return enumType.getEnumConstants()[index];
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount()
	{
		return enumType.getEnumConstants().length;
	}
}
