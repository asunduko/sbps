<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<!--<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Lists of All Friends </title>
	</head>

	<body>
		<h3>Lists of All Friends </h3>
		<hr/>
		<s:action name="friendAction" executeResult="true"/>
		<hr/>
		
		
	</body>
	
</html>


-->


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
	dojo.widget.byId("resultsPanel").selectChild("detail");
};

window.assign = function assign(id){
	var widget=dojo.widget.byId("assign");
	widget.href="<%=request.getContextPath()%>/selectAndAssign.action?masterBillId="+id;
	dojo.widget.byId("resultsPanel").selectChild("assign");
};
</script>


<script type='text/javascript'>

function formValidator(){

	var approveYes = document.getElementById('approveYes');
	var approveNo = document.getElementById('approveNo');
	var approve = document.getElementById('approve');
	alert("approve :" + approveNo.value); 

	  if (approveYes == null){
		  alert("Please, choose an option before submitting.");
		  window.location.href("friendAction");
		  return false;
	  }else if(approveNo == null){
		  alert("Please, choose an option before submitting.");
		  window.location.href("friendAction");
		  return false;
	  }	  	  

	return true;	
}


</script>
<body>

	
<sx:div id="catalog" label="Catalog MB" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" href="friendAction">		
		
</sx:div>
<s:form action="reviewAnswerInvitation" method="post" validate="true" onsubmit="return formValidator()">
        	<s:hidden name="invitationJoinId" value="%{invitationJoinId}"/>
			 
			 <s:radio id="approve" name="approve" list="{'Yes', 'No'}" label="Select Yes to approve answer, or no to indicate incorrect response"/>
			<s:submit method="reviewAnswer" key="Submit." align="center" />			
		</s:form>

</body>
</html>