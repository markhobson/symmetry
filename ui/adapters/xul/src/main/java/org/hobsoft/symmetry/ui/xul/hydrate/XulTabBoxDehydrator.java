/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulTabBoxDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.xul.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Tab;
import org.hobsoft.symmetry.ui.TabBox;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 */
public class XulTabBoxDehydrator<T extends TabBox> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T tabBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasTabs = !tabBox.getTabs().isEmpty();
		
		try
		{
			out.writeStartElement("tabbox");
			
			if (hasTabs)
			{
				out.writeStartElement("tabs");
				
				// TODO: remove the need for this traversal somehow
				for (Tab tab : tabBox.getTabs())
				{
					dehydrateTab(tab, context);
				}
				
				// tabs
				out.writeEndElement();
	
				out.writeStartElement("tabpanels");
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T tabBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasTabs = !tabBox.getTabs().isEmpty();
		
		try
		{
			if (hasTabs)
			{
				// tabpanels
				out.writeEndElement();
			}
			
			// tabbox
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected void dehydrateTab(Tab tab, HydrationContext context) throws HydrationException, XMLStreamException
	{
		// TODO: move to XulTabDehydrator somehow
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		out.writeEmptyElement("tab");
		
		writeAttributeIfNotEmpty(out, "label", tab.getText());
	}
}
