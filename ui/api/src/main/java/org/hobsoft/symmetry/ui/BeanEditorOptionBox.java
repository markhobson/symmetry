/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/BeanEditorOptionBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.event.OptionEvent;
import org.hobsoft.symmetry.ui.event.OptionListener;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the bean type
 */
public class BeanEditorOptionBox<T> extends OptionBox
{
	// fields -----------------------------------------------------------------
	
	private BeanEditor<T> editor;

	// types ------------------------------------------------------------------
	
	private class BeanEditorOptionListener implements OptionListener
	{
		// OptionListener methods ---------------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void formCancelled(OptionEvent event)
		{
			editor.rollback();
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void formFinished(OptionEvent event)
		{
			if (!editor.commit())
			{
				event.consume();
			}
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public BeanEditorOptionBox(BeanEditor<T> editor)
	{
		this.editor = editor;
		setComponent(editor);
		addOptionListener(new BeanEditorOptionListener());
	}
	
	// public methods ---------------------------------------------------------
	
	public BeanEditor<T> getBeanEditor()
	{
		return editor;
	}
}
