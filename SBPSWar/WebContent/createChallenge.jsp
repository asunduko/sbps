<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Create Challenge - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Create a Challenge</h2>

    <s:actionerror />
	  <s:fielderror />
	    <s:form>    	
	    	<s:textarea label ="Challenge the User who's invited you to become friends" 
	    	   value="%{visitorInvitation.MUser.userName}" cols="25" wrap="true" readonly="true"/>
	    </s:form>

		<s:form action="createChallengeInvitation" method="post" validate="true" >
        	<s:hidden name="invitationJoinId" value="%{invitationJoinId}"/>
			 
			 <s:textarea name="challenge" label="Create a Challenge" cols="25" wrap="true"/>
			<s:submit method="createChallenge" key="Submit Challenge." align="center" />			
		</s:form>
		
</body>
</html>