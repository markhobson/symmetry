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
package org.hobsoft.symmetry.support.xml.dom;

import java.text.CharacterIterator;

import javax.swing.text.Position.Bias;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DOMCharacterIterator implements CharacterIterator
{
	// fields -----------------------------------------------------------------
	
	private final NodeFilter filter;
	
	private TreeWalker walker;
	
	private int offset;
	
	// constructors -----------------------------------------------------------
	
	public DOMCharacterIterator(Node root)
	{
		this(root, null);
	}

	public DOMCharacterIterator(Node root, NodeFilter filter)
	{
		this.filter = filter;
		
		walker = createTreeWalker(root, filter);

		walker.firstChild();
		
		first();
	}

	// CharacterIterator methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public char first()
	{
		firstNode();

		offset = 0;
		
		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char last()
	{
		lastNode();
		
		offset = Math.max(0, getLength() - 1);
		
		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char current()
	{
		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char next()
	{
		if (offset < getLength())
		{
			offset++;
			
			if (offset == getLength())
			{
				if (walker.nextNode() != null)
				{
					offset = 0;
				}
			}
		}
		
		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char previous()
	{
		if (offset > 0)
		{
			offset--;
		}
		else
		{
			if (walker.previousNode() == null)
			{
				return DONE;
			}
			
			offset = getLength() - 1;
		}
		
		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char setIndex(int position)
	{
		if (position < 0)
		{
			throw new IllegalArgumentException("position: " + position + " < 0");
		}
		
		firstNode();
		
		int index = 0;
		boolean more = true;
		
		while (more && position >= index + getLength())
		{
			int length = getLength();
			
			if (walker.nextNode() == null)
			{
				if (position > index + length)
				{
					throw new IllegalArgumentException("position: " + position + " > " + (index + length));
				}
				
				more = false;
			}
			else
			{
				index += length;
			}
		}
		
		offset = position - index;

		return getChar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getBeginIndex()
	{
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getEndIndex()
	{
		Node currentNode = getNode();
		int currentOffset = offset;
		
		last();
		
		int endIndex = getIndex();
		
		if (getText() != null)
		{
			endIndex++;
		}
		
		walker.setCurrentNode(currentNode);
		offset = currentOffset;
		
		return endIndex;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getIndex()
	{
		Node currentNode = getNode();
		
		firstNode();
		
		int index = 0;
		
		while (getNode() != currentNode)
		{
			index += getLength();
			
			walker.nextNode();
		}
		
		walker.setCurrentNode(currentNode);

		return index + offset;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone()
	{
		try
		{
			DOMCharacterIterator iterator = (DOMCharacterIterator) super.clone();

			iterator.walker = createTreeWalker(walker.getRoot(), filter);
			iterator.walker.setCurrentNode(getNode());
			iterator.offset = getOffset();

			return iterator;
		}
		catch (CloneNotSupportedException exception)
		{
			throw new AssertionError();
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public Node getNode()
	{
		// TODO: rename to getContainer
		
		return getNode(Bias.Forward);
	}
	
	public Node getNode(Bias bias)
	{
		// TODO: rename to getContainer
		
		if (offset > 0 || bias == Bias.Forward)
		{
			return walker.getCurrentNode();
		}
		
		Node currentNode = walker.getCurrentNode();
		Node previousNode = walker.previousNode();
		walker.setCurrentNode(currentNode);
		
		return (previousNode != null) ? previousNode : currentNode;
	}
	
	public int getOffset()
	{
		return getOffset(Bias.Forward);
	}
	
	public int getOffset(Bias bias)
	{
		if (offset > 0 || bias == Bias.Forward)
		{
			return offset;
		}
		
		Node currentNode = walker.getCurrentNode();
		Node previousNode = walker.previousNode();
		walker.setCurrentNode(currentNode);
		
		return (previousNode != null) ? ((Text) previousNode).getLength() : offset;
	}
	
	public char setIndex(Node node, int offset)
	{
		if (node == null)
		{
			throw new IllegalArgumentException("node: null");
		}
		
		if (offset < 0)
		{
			throw new IllegalArgumentException("offset: " + offset + " < 0");
		}
		
		int endOffset = RangeUtils.getMaxOffset(node);
		
		if (offset > endOffset)
		{
			throw new IllegalArgumentException("offset: " + offset + " > " + endOffset);
		}
		
		walker.setCurrentNode(node);
		
		this.offset = Math.max(0, Math.min(offset, endOffset));
		
		return getChar();
	}
	
	// private methods --------------------------------------------------------
	
	private static TreeWalker createTreeWalker(Node root, NodeFilter filter)
	{
		NodeFilter compoundFilter = NodeFilters.and(filter, NodeFilters.not(NodeFilters.emptyCharacterData()));
		
		return TraversalUtils.getTreeWalker(root, NodeFilter.SHOW_TEXT, compoundFilter);
	}
	
	private void firstNode()
	{
		while (walker.previousNode() != null)
		{
			// loop
		}
	}
	
	private void lastNode()
	{
		while (walker.nextNode() != null)
		{
			// loop
		}
	}
	
	private int getLength()
	{
		Text text = getText();
		
		return (text != null) ? text.getLength() : 0;
	}
	
	private char getChar()
	{
		Text text = getText();
		
		if (text == null || offset == text.getLength())
		{
			return DONE;
		}
		
		return text.getData().charAt(offset);
	}
	
	private Text getText()
	{
		Node node = getNode();
		
		return TraversalUtils.isVisible(node, walker) ? (Text) node : null;
	}
}
