/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/HtmlDocument.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlDocument.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 */
public class HtmlDocument
{
	// types ------------------------------------------------------------------
	
	/**
	 * An external script.
	 */
	public static final class ExternalScript
	{
		private final String uri;
		
		private final String type;
		
		public ExternalScript(String uri, String type)
		{
			this.uri = uri;
			this.type = type;
		}
		
		public String getUri()
		{
			return uri;
		}
		
		public String getType()
		{
			return type;
		}
		
		@Override
		public int hashCode()
		{
			return (uri.hashCode() * 31) + type.hashCode();
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof ExternalScript))
			{
				return false;
			}
			
			ExternalScript externalScript = (ExternalScript) object;
			
			return uri.equals(externalScript.getUri())
				&& type.equals(externalScript.getType());
		}
		
		@Override
		public String toString()
		{
			// TODO: how best to represent type?
			return uri;
		}
	}
	
	/**
	 * An inline script.
	 */
	public static final class Script
	{
		private final String script;
		
		private final String type;
		
		public Script(String script, String type)
		{
			this.script = script;
			this.type = type;
		}
		
		public String getScript()
		{
			return script;
		}
		
		public String getType()
		{
			return type;
		}
		
		@Override
		public int hashCode()
		{
			return (script.hashCode() * 31) + type.hashCode();
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof Script))
			{
				return false;
			}
			
			Script that = (Script) object;
			
			return script.equals(that.getScript())
				&& type.equals(that.getType());
		}
		
		@Override
		public String toString()
		{
			// TODO: how best to represent type?
			return script;
		}
	}

	/**
	 * An external style.
	 */
	public static final class ExternalStyle
	{
		private final String uri;
		
		private final String type;
		
		public ExternalStyle(String uri, String type)
		{
			this.uri = uri;
			this.type = type;
		}
		
		public String getUri()
		{
			return uri;
		}
		
		public String getType()
		{
			return type;
		}
		
		@Override
		public int hashCode()
		{
			return (uri.hashCode() * 31) + type.hashCode();
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof ExternalStyle))
			{
				return false;
			}
			
			ExternalStyle externalStyle = (ExternalStyle) object;
			
			return uri.equals(externalStyle.getUri())
				&& type.equals(externalStyle.getType());
		}
		
		@Override
		public String toString()
		{
			// TODO: how best to represent type?
			return uri;
		}
	}
	
	/**
	 * An inline style.
	 */
	public static final class Style
	{
		private final String style;
		
		private final String type;
		
		public Style(String style, String type)
		{
			this.style = style;
			this.type = type;
		}

		public String getStyle()
		{
			return style;
		}
		
		public String getType()
		{
			return type;
		}
		
		@Override
		public int hashCode()
		{
			return (style.hashCode() * 31) + type.hashCode();
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof Style))
			{
				return false;
			}
			
			Style that = (Style) object;
			
			return style.equals(that.getStyle())
				&& type.equals(that.getType());
		}
		
		@Override
		public String toString()
		{
			// TODO: how best to represent type?
			return style;
		}
	}
	
	/**
	 * Document meta-data name.
	 */
	public static final class MetadataName
	{
		private final String name;
		
		private final boolean http;
		
		public MetadataName(String name, boolean http)
		{
			this.name = name;
			this.http = http;
		}
		
		public String getName()
		{
			return name;
		}
		
		public boolean isHttp()
		{
			return http;
		}
		
		@Override
		public int hashCode()
		{
			return (name.hashCode() * 31) + (http ? 1 : 0);
		}
		
		@Override
		public boolean equals(Object object)
		{
			if (!(object instanceof MetadataName))
			{
				return false;
			}
			
			MetadataName metadataName = (MetadataName) object;
			
			return name.equals(metadataName.getName())
				&& http == metadataName.isHttp();
		}
	}
	
	// constants --------------------------------------------------------------
	
	private static final String JAVASCRIPT_TYPE = "text/javascript";
	
	private static final String CSS_TYPE = "text/css";
	
	// fields -----------------------------------------------------------------
	
	private final List<String> comments;
	
	private final Set<ExternalScript> externalScripts;
	
	private final List<Script> scripts;
	
	private final Set<ExternalStyle> externalStyles;
	
	private final List<Style> styles;
	
	private final Map<MetadataName, String> metadataValuesByName;
	
	// constructors -----------------------------------------------------------
	
	public HtmlDocument()
	{
		comments = new ArrayList<String>();
		externalScripts = new LinkedHashSet<ExternalScript>();
		scripts = new ArrayList<Script>();
		externalStyles = new LinkedHashSet<ExternalStyle>();
		styles = new ArrayList<Style>();
		metadataValuesByName = new LinkedHashMap<MetadataName, String>();
	}
	
	// public methods ---------------------------------------------------------
	
	public void addComment(String comment)
	{
		comments.add(comment);
	}
	
	public List<String> getComments()
	{
		return Collections.unmodifiableList(comments);
	}
	
	public void addExternalJavaScript(String uri)
	{
		addExternalScript(uri, JAVASCRIPT_TYPE);
	}
	
	public void addExternalScript(String uri, String type)
	{
		externalScripts.add(new ExternalScript(uri, type));
	}
	
	public Set<ExternalScript> getExternalScripts()
	{
		return Collections.unmodifiableSet(externalScripts);
	}
	
	public void addJavaScript(String javaScript)
	{
		addScript(javaScript, JAVASCRIPT_TYPE);
	}
	
	public void addScript(String script, String type)
	{
		scripts.add(new Script(script, type));
	}
	
	public List<Script> getScripts()
	{
		return Collections.unmodifiableList(scripts);
	}
	
	public void addExternalCss(String uri)
	{
		addExternalStyle(uri, CSS_TYPE);
	}
	
	public void addExternalStyle(String uri, String type)
	{
		externalStyles.add(new ExternalStyle(uri, type));
	}
	
	public Set<ExternalStyle> getExternalStyles()
	{
		return Collections.unmodifiableSet(externalStyles);
	}
	
	public void addCss(String css)
	{
		addStyle(css, CSS_TYPE);
	}
	
	public void addStyle(String style, String type)
	{
		styles.add(new Style(style, type));
	}
	
	public List<Style> getStyles()
	{
		return Collections.unmodifiableList(styles);
	}
	
	public void addMetadata(String name, String content)
	{
		metadataValuesByName.put(new MetadataName(name, false), content);
	}
	
	public void addHttpMetadata(String httpEquiv, String content)
	{
		metadataValuesByName.put(new MetadataName(httpEquiv, true), content);
	}
	
	public Set<MetadataName> getMetadataNames()
	{
		return metadataValuesByName.keySet();
	}
	
	public String getMetadataValue(MetadataName name)
	{
		return metadataValuesByName.get(name);
	}
	
	public void add(HtmlDocument document)
	{
		for (String comment : document.getComments())
		{
			addComment(comment);
		}

		for (ExternalScript externalScript : document.getExternalScripts())
		{
			addExternalScript(externalScript.getUri(), externalScript.getType());
		}

		for (Script script : document.getScripts())
		{
			addScript(script.getScript(), script.getType());
		}

		for (ExternalStyle externalStyle : document.getExternalStyles())
		{
			addExternalStyle(externalStyle.getUri(), externalStyle.getType());
		}

		for (Style style : document.getStyles())
		{
			addStyle(style.getStyle(), style.getType());
		}
		
		for (MetadataName metadataName : document.getMetadataNames())
		{
			if (metadataName.isHttp())
			{
				addHttpMetadata(metadataName.getName(), document.getMetadataValue(metadataName));
			}
			else
			{
				addMetadata(metadataName.getName(), document.getMetadataValue(metadataName));
			}
		}
	}
}
