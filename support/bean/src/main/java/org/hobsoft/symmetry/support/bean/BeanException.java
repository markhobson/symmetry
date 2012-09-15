/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/BeanException.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class BeanException extends RuntimeException
{
	// TODO: make abstract when all usages use more specific concrete subtype
	
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 8968706924206554337L;
	
	// constructors -----------------------------------------------------------
	
	public BeanException()
	{
		super();
	}
	
	public BeanException(String message)
	{
		super(message);
	}
	
	public BeanException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public BeanException(Throwable cause)
	{
		super(cause);
	}
}
