<%@ page contentType="text/html; ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>My Bills - SBPS.com</title>
<sx:head parseContent="true"/> 
	<style type="text/css">

		@import url(style.css);

	</style>

</head>

<script>
window.review = function review(id){
	var widget=dojo.widget.byId("review");
	widget.href="<%=request.getContextPath()%>/reviewMyBills.action?assignedBillId="+id;
	dojo.widget.byId("resultPanel").selectChild("review");
	//alert ("This is a JavaScript ALERT box.");
};
</script>

	<body>

		<s:actionerror />
	  	<s:fielderror />
		
		<sx:div id="catalog" label="My Payments Catalog" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" href="myPaymentsList">		
		</sx:div>
		
	</body>
	
</html>


