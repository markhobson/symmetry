/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/EnumComboBox.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.model.ListModels;
import org.hobsoft.symmetry.ui.view.ListCellRenderers;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <E>
 *            the enum type
 */
public class EnumComboBox<E extends Enum<E>> extends ComboBox<E>
{
	// constructors -----------------------------------------------------------
	
	public EnumComboBox(Class<E> enumType)
	{
		setModel(ListModels.forEnum(enumType));
		setListCellRenderer(ListCellRenderers.<E>forEnum());
	}
}
