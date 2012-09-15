/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/visitor/PrintNodeVisitor.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.visitor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * A node visitor that pretty prints every node to a writer.
 * 
 * @author Mark Hobson
 * @version $Id: PrintNodeVisitor.java 69819 2010-01-21 15:54:06Z mark@IIZUKA.CO.UK $
 */
public class PrintNodeVisitor extends AbstractNodeVisitor
{
	// constants --------------------------------------------------------------
	
	private static final String LINE_SEPARATOR_PROPERTY = "line.separator";
	
	// fields -----------------------------------------------------------------
	
	private final Appendable appendable;
	
	private final String newLine;
	
	private int depth;
	
	// constructors -----------------------------------------------------------
	
	public PrintNodeVisitor()
	{
		this(System.out);
	}
	
	public PrintNodeVisitor(PrintStream out)
	{
		this((Appendable) out);
	}
	
	public PrintNodeVisitor(OutputStream out)
	{
		this(new OutputStreamWriter(out));
	}
	
	public PrintNodeVisitor(Appendable appendable)
	{
		this.appendable = appendable;
		
		newLine = System.getProperty(LINE_SEPARATOR_PROPERTY);

		depth = 0;
	}
	
	// AbstractNodeVisitor methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean beginNode(Node node)
	{
		printNode(node);

		depth++;
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean visitNode(Node node)
	{
		printNode(node);
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean endNode(Node node)
	{
		depth--;
		
		return true;
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Pretty prints the specified node to the output stream.
	 * 
	 * @param node
	 *            the node to print
	 */
	protected void printNode(Node node)
	{
		// indent
		for (int i = 0; i < depth; i++)
		{
			print("  ");
		}

		// print node name
		print(node.getNodeName());
		
		// print node attributes
		NamedNodeMap attributes = node.getAttributes();
		if (attributes != null)
		{
			print(" ");
			printAttributes(attributes);
		}

		// print node value
		String value = node.getNodeValue();
		if (value != null)
		{
			print(" {");
			print(value);
			print("}");
		}

		// new line
		printNewLine();
	}
	
	/**
	 * Pretty prints the specified attributes to the output stream.
	 * 
	 * @param attributes
	 *            the attributes to print
	 */
	protected void printAttributes(NamedNodeMap attributes)
	{
		for (int i = 0; i < attributes.getLength(); i++)
		{
			Node node = attributes.item(i);
			
			if (i > 0)
			{
				print(" ");
			}
			
			print(node.getNodeName());
			print("=");
			print(node.getNodeValue());
		}
	}
	
	protected void print(String string)
	{
		try
		{
			appendable.append(string);
		}
		catch (IOException exception)
		{
			// ignore
		}
	}
	
	protected void printNewLine()
	{
		print(newLine);
	}
}
