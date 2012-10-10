/*
 * $HeadURL: https://svn.iizuka.co.uk/common/binding/kozo/tags/1.0.0-beta-1/src/test/java/uk/co/iizuka/common/binding/kozo/test/TestObservableListeners.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.binding.kozo.test;

import org.hobsoft.entangle.Observable;
import org.hobsoft.entangle.ObservableEvent;
import org.hobsoft.entangle.ObservableListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Ignore;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TestObservableListeners.java 97729 2012-01-11 16:17:41Z mark@IIZUKA.CO.UK $
 */
// TODO: remove @Ignore when SUREFIRE-482 fixed
@Ignore
public final class TestObservableListeners
{
	// TODO: share with common-binding's version when centralised
	
	// constructors -----------------------------------------------------------
	
	private TestObservableListeners()
	{
		throw new AssertionError();
	}

	// public methods ---------------------------------------------------------
	
	public static <T> ObservableListener<T> mockObservableListener(Mockery mockery)
	{
		// cannot safely mock generic types
		@SuppressWarnings("unchecked")
		ObservableListener<T> listener = mockery.mock(ObservableListener.class);

		return listener;
	}
	
	public static <T> ObservableListener<T> mockObservableListenerWithValueChanged(Mockery mockery,
		Observable<T> expectedSource, T expectedOldValue, T expectedNewValue)
	{
		final ObservableListener<T> listener = mockObservableListener(mockery);
		
		final ObservableEvent<T> expectedEvent = new ObservableEvent<T>(expectedSource, expectedOldValue,
			expectedNewValue);
		
		mockery.checking(new Expectations() { {
			oneOf(listener).valueChanged(expectedEvent);
		} });
		
		return listener;
	}
}
