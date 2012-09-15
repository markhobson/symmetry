/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/core/src/test/java/uk/co/iizuka/kozo/util/io/test/SubclassSerializable.java $
 * 
 * (c) 2008 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.util.io.test;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: SubclassSerializable.java 93651 2011-10-06 20:39:46Z mark@IIZUKA.CO.UK $
 */
public class SubclassSerializable extends SimpleSerializable
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 8483519334298145022L;
	
	// fields -----------------------------------------------------------------
	
	private int age;
	
	// constructors -----------------------------------------------------------
	
	public SubclassSerializable(String name, int age)
	{
		super(name);
		
		setAge(age);
	}
	
	// public methods ---------------------------------------------------------
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode() * 31 + age;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof SubclassSerializable))
		{
			return false;
		}
		
		SubclassSerializable that = (SubclassSerializable) object;
		
		return super.equals(object) && age == that.getAge();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[name=" + getName() + ",age=" + age + "]";
	}
}
