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
