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
package org.hobsoft.symmetry.ui.html;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.hobsoft.symmetry.ui.ComponentVisitor;
import org.hobsoft.symmetry.ui.Window;

import com.google.common.base.Charsets;

/**
 * Visitor that dehydrates UI components to HTML.
 */
public class HtmlComponentVisitor implements ComponentVisitor<OutputStream, IOException>
{
	// ----------------------------------------------------------------------------------------------------------------
	// ComponentVisitor methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public void visit(Window window, OutputStream outputStream) throws IOException
	{
		Writer writer = newWriter(outputStream);
		writer.write("<html>");
		writer.flush();
	}

	@Override
	public void endVisit(Window window, OutputStream outputStream) throws IOException
	{
		Writer writer = newWriter(outputStream);
		writer.write("</html>");
		writer.flush();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static Writer newWriter(OutputStream outputStream)
	{
		return new OutputStreamWriter(outputStream, Charsets.UTF_8);
	}
}
