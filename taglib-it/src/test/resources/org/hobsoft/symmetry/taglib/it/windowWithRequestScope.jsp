<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://hobsoft.org/symmetry/taglib"%>
<jsp:useBean id="component" class="org.hobsoft.symmetry.taglib.it.WindowBean" scope="request"/>
<jsp:useBean id="reflector" class="org.hobsoft.symmetry.ui.html.HtmlComponentReflector" scope="request"/>
<s:component name="component" reflectorName="reflector"/>
