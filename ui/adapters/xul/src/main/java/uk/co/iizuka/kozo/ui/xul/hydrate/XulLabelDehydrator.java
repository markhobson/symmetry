/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulLabelDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEmpty;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Label;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: XulLabelDehydrator.java 99566 2012-03-15 16:00:09Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public class XulLabelDehydrator<T extends Label> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T label, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("label");

			// TODO: need to reimplement flex now it is provided by the parent box, if any
//			int flex = label.getFlex();
//			writeAttributeIf(out, "flex", flex, flex > 0);
			
			writeAttributeIfNotEmpty(out, "value", label.getText());
			writeAttributeIfNotEmpty(out, "tooltiptext", label.getToolTip());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
}
