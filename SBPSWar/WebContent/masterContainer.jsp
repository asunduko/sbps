<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Master Container - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>
<script>

window.showDetail = function showDetail(id) {
	var widget=dojo.widget.byId("detail");
	widget.href="<%=request.getContextPath()%>/displayMB.action?masterBillId="+id;
	dojo.widget.byId("resultPanel").selectChild("detail");
	//alert ("This is a JavaScript ALERT box.");
};

window.assign = function assign(id){
	var widget=dojo.widget.byId("assign");
	widget.href="<%=request.getContextPath()%>/selectThenAssignSequence.action?masterBillId="+id;
	dojo.widget.byId("resultPanel").selectChild("assign");
	//alert ("This is a JavaScript ALERT box.");
};
</script>
<body>

	
<sx:div id="catalog" label="Catalog MB" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" href="showUserMBS">		
		
</sx:div>


</body>
</html>