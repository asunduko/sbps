<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Provide Answer - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Provide an answer to the challenge:</h2>

    <s:actionerror />
	  <s:fielderror />
	    <s:form>
	    	<s:textarea value="%{visitorInvitation.IChallenge}" cols="25" wrap="true" readonly="true"/>
	    </s:form>

		<s:form action="provideResponseInvitation" method="post" validate="true" >
        	<s:hidden name="invitationJoinId" value="%{invitationJoinId}"/>
			 
			 <s:textarea name="response" label="Provide Response" cols="25" wrap="true"/>
			<s:submit method="provideResponse" key="Submit Response" align="center" />			
		</s:form>
		
</body>
</html>