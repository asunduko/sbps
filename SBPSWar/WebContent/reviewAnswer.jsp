<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Review Response - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>
<body>
<h2>Review Response</h2>

    <s:actionerror />
	  <s:fielderror />
	    <s:form>
	    	
	    	<s:textarea label="The User" value="%{visitorInvitation.MUser.userName}" cols="25" wrap="true" readonly="true"/>
	    	
	    	<s:textarea label="Invited you and provided a response " value="%{visitorInvitation.MResponse}" cols="25" wrap="true" readonly="true"/>
	    	
	    	<s:textarea label="To your Challenge " value="%{visitorInvitation.IChallenge}" cols="25" wrap="true" readonly="true"/>
	    </s:form>

		<s:form action="reviewAnswerInvitation" method="post" validate="true">
        	<s:hidden name="invitationJoinId" value="%{invitationJoinId}"/>
			 
			 <s:radio id="approve" name="approve" list="{'Yes', 'No'}" label="Select Yes to approve answer, or no to indicate incorrect response"/>
			<s:submit method="reviewAnswer" key="Submit." align="center" />			
		</s:form>
		
</body>
</html>