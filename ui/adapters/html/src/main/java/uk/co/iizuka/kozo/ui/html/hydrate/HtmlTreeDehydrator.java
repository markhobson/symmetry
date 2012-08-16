/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTreeDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeHref;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.createFilteredWriter;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.common.xml.stax.filter.EventFilters;
import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Tree;
import uk.co.iizuka.kozo.ui.common.hydrate.HierarchicalComponentHydrator;
import uk.co.iizuka.kozo.ui.event.TreeEvent;
import uk.co.iizuka.kozo.ui.event.TreeExpansionListener;
import uk.co.iizuka.kozo.ui.html.HtmlFilters;
import uk.co.iizuka.kozo.ui.model.TreePath;
import uk.co.iizuka.kozo.ui.traversal.NullTreeVisitor;

/**
 * Hydrator that dehydrates a {@code Tree} component to an HTML {@code <ul/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTreeDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Tree
 * @param <T>
 *            the tree type this visitor can visit
 */
public class HtmlTreeDehydrator<T extends Tree>
	extends NullTreeVisitor<T, HydrationContext, HydrationException>
	implements HierarchicalComponentHydrator<T>
{
	// TODO: add href fragments for node expansion/collapsion
	
	// types ------------------------------------------------------------------
	
	/**
	 * Dehydration context parameters for this hydrator.
	 */
	public static final class Parameters
	{
		// fields -----------------------------------------------------------------
		
		private String collapseImageUri;
		
		private String expandImageUri;
		
		// public methods ---------------------------------------------------------
		
		public String getCollapseImageUri()
		{
			return collapseImageUri;
		}
		
		public void setCollapseImageUri(String collapseImageUri)
		{
			this.collapseImageUri = collapseImageUri;
		}
		
		public String getExpandImageUri()
		{
			return expandImageUri;
		}
		
		public void setExpandImageUri(String expandImageUri)
		{
			this.expandImageUri = expandImageUri;
		}
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T tree, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("ul");
			writeClass(out, getTreeCssClass(tree));
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
	public EndVisit endVisit(T tree, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// ul
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	// TreeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNode(T tree, HydrationContext context, TreePath path) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean expanded = tree.isPathExpanded(path);
		boolean leaf = tree.getModel().isLeaf(path.getNode());
		
		try
		{
			out.writeStartElement("li");
			writeClass(out, getTreeNodeCssClass(tree, path));
			
			if (!leaf)
			{
				out.writeStartElement("a");
				writeClass(out, getTreeNodeControlCssClass(tree, path));
				
				String listenerMethodName = expanded
					? TreeExpansionListener.TREE_COLLAPSED_METHOD
					: TreeExpansionListener.TREE_EXPANDED_METHOD;
				
				writeHref((DehydrationContext) context, Tree.TREE_EXPANSION_EVENT, listenerMethodName,
					new TreeEvent(tree, path));
				
				out.writeEmptyElement("img");
				
				out.writeAttribute("src", getImageUrl(tree, context, path));
				out.writeAttribute("alt", expanded ? "-" : "+");
				out.writeAttribute("title", "");
				
				// a
				out.writeEndElement();
			}
			
			boolean hasListeners = (tree.getTreeSelectionListenerCount() > 0);
			
			if (hasListeners)
			{
				out.writeStartElement("a");
				
				writeHref((DehydrationContext) context, Tree.TREE_SELECTION_EVENT, new TreeEvent(tree, path));
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		EventFilter filter = EventFilters.not(HtmlFilters.BLOCK);
		context.push(XMLStreamWriter.class, createFilteredWriter(out, filter));

		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitNodeChildren(T tree, HydrationContext context, TreePath path) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("ul");
			writeClass(out, getTreeNodeChildrenCssClass(tree, path));
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
	public EndVisit endVisitNodeChildren(T tree, HydrationContext context, TreePath path) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			// ul
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitNode(T tree, HydrationContext context, TreePath path) throws HydrationException
	{
		context.pop(XMLStreamWriter.class);
		
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean hasListeners = (tree.getTreeSelectionListenerCount() > 0);

		try
		{
			if (hasListeners)
			{
				// a
				out.writeEndElement();
			}
			
			// li
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_SIBLINGS;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getTreeCssClass(T tree)
	{
		return new CssClassBuilder("tree");
	}
	
	protected CssClassBuilder getTreeNodeCssClass(T tree, TreePath path)
	{
		boolean selected = tree.isPathSelected(path);
		boolean leaf = tree.getModel().isLeaf(path.getNode());
		
		if (leaf && !selected)
		{
			return null;
		}
		
		CssClassBuilder builder = new CssClassBuilder();
		
		if (selected)
		{
			builder.append("selected");
		}
		
		if (!leaf)
		{
			builder.append("container");
		}
		
		return builder;
	}
	
	protected CssClassBuilder getTreeNodeControlCssClass(T tree, TreePath path)
	{
		return new CssClassBuilder("control");
	}
	
	protected CssClassBuilder getTreeNodeChildrenCssClass(T tree, TreePath path)
	{
		return new CssClassBuilder("tree");
	}
	
	protected String getImageUrl(T tree, HydrationContext context, TreePath path)
	{
		boolean expanded = tree.isPathExpanded(path);
		Parameters parameters = context.get(Parameters.class);
		
		return expanded ? parameters.getCollapseImageUri() : parameters.getExpandImageUri();
	}
}
