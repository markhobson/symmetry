/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/model/BeanTableModel.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.model;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public interface BeanTableModel<T> extends TableModel
{
	T getBeanAt(int row);
}
