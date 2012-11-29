<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Invitation Page</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Invite a Friend</h2>



      <s:actionerror />
	  <s:fielderror />
        <s:form action="inviteJoin" method="post" validate="true">
        	
			<s:textfield name="email" label="email" />
			<s:submit method="inviteJoin" key="Submit" align="center" />
		</s:form>
     


</body>
</html>

