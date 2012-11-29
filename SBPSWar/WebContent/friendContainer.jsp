<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Master Container - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<script>

window.respond = function respond(id) {
	var widget=dojo.widget.byId("respond");
	widget.href="<%=request.getContextPath()%>/respondInvitation.action?invitationJoinId="+id;
	dojo.widget.byId("resultPanel").selectChild("respond");
	//alert ("This is a JavaScript ALERT box.");
};

window.createChallenge = function createChallenge(id){
	var widget=dojo.widget.byId("createChallenge");
	widget.href="<%=request.getContextPath()%>/createChallengePrepareInvitation.action?invitationJoinId="+id;
	dojo.widget.byId("resultPanel").selectChild("createChallenge");
	//alert ("This is a JavaScript ALERT box.");
};

window.reviewAnswer = function reviewAnswer(id){
	var widget=dojo.widget.byId("reviewAnswer");
	widget.href="<%=request.getContextPath()%>/reviewAnswerPrepareInvitation.action?invitationJoinId="+id;
	dojo.widget.byId("resultPanel").selectChild("reviewAnswer");
	//alert ("This is a JavaScript ALERT box.");
};
</script>

<script type='text/javascript'>

function formValidator(){

	var prResp = document.getElementById('prResp');


	  if (prResp.value.length == 0){
		  alert("Response was not provided.");
		  return "friendAction";
		  
	  } 	  

	  return "provideResponseInvitation";	
}


</script>

<body>

<s:actionerror />
	  <s:fielderror />	
<sx:div id="catalog" label="Catalog Friends" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" href="friendAction">		
		
</sx:div>


</body>
</html>