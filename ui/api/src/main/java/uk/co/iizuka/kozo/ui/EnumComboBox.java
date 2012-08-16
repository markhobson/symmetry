/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/EnumComboBox.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import uk.co.iizuka.kozo.ui.model.ListModels;
import uk.co.iizuka.kozo.ui.view.ListCellRenderers;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: EnumComboBox.java 89153 2011-06-21 15:35:18Z mark@IIZUKA.CO.UK $
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
