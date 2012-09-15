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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DefaultPropertyEditorErrorHandler implements PropertyEditorErrorHandler
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(DefaultPropertyEditorErrorHandler.class.getName());
	
	// PropertyEditorErrorHandler methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleError(PropertyEditor editor, Throwable exception)
	{
		if (editor instanceof PropertyEditorErrorHandler)
		{
			((PropertyEditorErrorHandler) editor).handleError(editor, exception);
		}
		else
		{
			// TODO: handle better
			LOG.log(Level.WARNING, "Unhandled property editor error", exception);
		}
	}
}
