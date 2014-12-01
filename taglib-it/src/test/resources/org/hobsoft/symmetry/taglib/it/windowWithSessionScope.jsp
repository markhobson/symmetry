<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://hobsoft.org/symmetry/taglib"%>
<jsp:useBean id="component" class="org.hobsoft.symmetry.taglib.it.WindowBean" scope="session"/>
<jsp:useBean id="reflector" class="org.hobsoft.symmetry.ui.html.HtmlComponentReflector" scope="session"/>
<s:component name="component" reflectorName="reflector"/>
