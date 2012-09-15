/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTreeDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.xml.stax.filter.EventFilters;
import org.hobsoft.symmetry.ui.Tree;
import org.hobsoft.symmetry.ui.common.hydrate.HierarchicalComponentHydrator;
import org.hobsoft.symmetry.ui.event.TreeEvent;
import org.hobsoft.symmetry.ui.event.TreeExpansionListener;
import org.hobsoft.symmetry.ui.html.HtmlFilters;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.traversal.NullTreeVisitor;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHref;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.createFilteredWriter;

/**
 * Hydrator that dehydrates a {@code Tree} component to an HTML {@code <ul/>} tag.
 * 
 * @author Mark Hobson
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
