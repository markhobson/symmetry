<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://hobsoft.org/symmetry/taglib"%>
<jsp:useBean id="component" class="org.hobsoft.symmetry.demo.jsp.helloworld.HelloBean"/>
<jsp:useBean id="reflector" class="org.hobsoft.symmetry.ui.html.HtmlComponentReflector"/>

<s:component name="component" reflectorName="reflector"/>
