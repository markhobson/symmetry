/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/HydrationPhase.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

/**
 * 
 * 
 * @author Mark Hobson
 */
public enum HydrationPhase
{
	// TODO: change to interface with DehydrationPhase and RehydrationPhase enum implementations?
	
	DEHYDRATE_INITIALIZE,
	DEHYDRATE,
	REHYDRATE_PROPERTIES,
	REHYDRATE_PARAMETERS,
	REHYDRATE_EVENTS;
}
