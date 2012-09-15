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
package org.hobsoft.symmetry.ui.html.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hobsoft.symmetry.ui.html.HtmlDocument;
import org.hobsoft.symmetry.ui.html.HtmlDocument.ExternalScript;
import org.hobsoft.symmetry.ui.html.HtmlDocument.ExternalStyle;
import org.hobsoft.symmetry.ui.html.HtmlDocument.Script;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Custom assertions for {@code HtmlDocument}.
 * 
 * @author Mark Hobson
 * @see HtmlDocument
 */
public final class HtmlDocumentAssert
{
	// constants --------------------------------------------------------------
	
	private static final String JAVASCRIPT_TYPE = "text/javascript";
	
	private static final String CSS_TYPE = "text/css";

	// constructors -----------------------------------------------------------
	
	private HtmlDocumentAssert()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	public static void assertExternalJavaScripts(HtmlDocument actual, String... expectedUris)
	{
		List<ExternalScript> actualExternalScripts = new ArrayList<ExternalScript>(actual.getExternalScripts());
		
		List<ExternalScript> expectedExternalScripts = toExternalJavaScripts(expectedUris);
		
		assertEquals("External JavaScripts", expectedExternalScripts, actualExternalScripts);
	}
	
	public static void assertExternalJavaScript(String expectedUri, HtmlDocument actual)
	{
		Set<ExternalScript> actualExternalScripts = actual.getExternalScripts();
		
		ExternalScript expectedExternalScript = new ExternalScript(expectedUri, JAVASCRIPT_TYPE);
		
		assertTrue("Expected external JavaScript:<" + expectedUri + "> but was:<" + actualExternalScripts + ">",
			actualExternalScripts.contains(expectedExternalScript));
	}
	
	public static void assertJavaScripts(HtmlDocument actual, String... expectedJavaScripts)
	{
		List<Script> actualScripts = actual.getScripts();
		
		List<Script> expectedScripts = toJavaScripts(expectedJavaScripts);
		
		assertEquals("JavaScripts", expectedScripts, actualScripts);
	}
	
	public static void assertJavaScript(String expectedJavaScript, HtmlDocument actual)
	{
		List<Script> actualScripts = actual.getScripts();
		
		Script expectedScript = new Script(expectedJavaScript, JAVASCRIPT_TYPE);
		
		assertTrue("Expected JavaScript:<" + expectedScript + "> but was:<" + actualScripts + ">",
			actualScripts.contains(expectedScript));
	}
	
	public static void assertExternalCss(String expectedUri, HtmlDocument actual)
	{
		Set<ExternalStyle> actualExternalStyles = actual.getExternalStyles();
		
		ExternalStyle expectedExternalStyle = new ExternalStyle(expectedUri, CSS_TYPE);
		
		assertTrue("Expected external CSS:<" + expectedUri + "> but was:<" + actualExternalStyles + ">",
			actualExternalStyles.contains(expectedExternalStyle));
	}
	
	// private methods --------------------------------------------------------
	
	private static List<ExternalScript> toExternalJavaScripts(String... uris)
	{
		return toExternalScripts(JAVASCRIPT_TYPE, uris);
	}
	
	private static List<ExternalScript> toExternalScripts(String type, String... uris)
	{
		List<ExternalScript> externalScripts = new ArrayList<ExternalScript>();
		
		for (String uri : uris)
		{
			externalScripts.add(new ExternalScript(uri, type));
		}
		
		return externalScripts;
	}
	
	private static List<Script> toJavaScripts(String... uris)
	{
		return toScripts(JAVASCRIPT_TYPE, uris);
	}
	
	private static List<Script> toScripts(String type, String... uris)
	{
		List<Script> scripts = new ArrayList<Script>();
		
		for (String uri : uris)
		{
			scripts.add(new Script(uri, type));
		}
		
		return scripts;
	}
}
