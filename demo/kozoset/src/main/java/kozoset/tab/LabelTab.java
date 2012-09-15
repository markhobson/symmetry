/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/demo/kozoset/src/main/java/kozoset/tab/LabelTab.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package kozoset.tab;

import org.hobsoft.symmetry.ui.Box;
import org.hobsoft.symmetry.ui.GroupBox;
import org.hobsoft.symmetry.ui.HtmlLabel;
import org.hobsoft.symmetry.ui.Label;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.VBox;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class LabelTab extends Tab
{
	// constructors -----------------------------------------------------------
	
	public LabelTab()
	{
		// TODO: use chainable setters when supported
		
		Box box = new VBox(
			new GroupBox("Label",
				new Label("A Label can be a simple string like this."),
				new HtmlLabel(
					"Labels can also contain <abbr title=\"Extensible HyperText Markup Language\">XHTML</abbr> tags "
					+ "to:"
					+ "<ul>"
						+ "<li>"
							+ "<em>achieve</em>;"
							+ "<ul>"
								+ "<li>"
									+ "<code>simple</code>;"
									+ "<ul>"
										+ "<li><strong>styling</strong>.</li>"
									+ "</ul>"
								+ "</li>"
							+ "</ul>"
						+ "</li>"
					+ "</ul>"
				),
				new HtmlLabel("This can be used to provide hyperlinks to <a href=\"http://www.iizuka.co.uk/\">cool "
					+ "places</a>."),
				new HtmlLabel("Unrecognised namespaces are also <format dir=\"c:\" xmlns=\"ant\">escaped accordingly"
					+ "</format>.")
			)
		);
		
		setText("Label");
		setComponent(box);
	}
}
