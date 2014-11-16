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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.hobsoft.symmetry.ui.Window;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code HtmlComponentVisitor}.
 */
public class HtmlComponentVisitorTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private HtmlComponentVisitor visitor;
	
	private ByteArrayOutputStream outputStream;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		visitor = new HtmlComponentVisitor();
		outputStream = new ByteArrayOutputStream();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void visitWindowWritesHtml() throws IOException
	{
		Window window = new Window();
		
		visitor.visit(window, outputStream);
		
		assertThat(toUtf8String(outputStream), is("<html><body>"));
	}
	
	@Test
	public void endVisitWindowWritesHtml() throws IOException
	{
		Window window = new Window();
		
		visitor.endVisit(window, outputStream);
		
		assertThat(toUtf8String(outputStream), is("</body></html>"));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static String toUtf8String(ByteArrayOutputStream outputStream) throws UnsupportedEncodingException
	{
		return outputStream.toString("UTF-8");
	}
}
