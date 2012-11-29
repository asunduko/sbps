<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Register/Recover - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Register/Recover Form</h2>


<sx:tabbedpanel id="test" >
      <sx:div id="one" label="Register" theme="ajax" labelposition="top" >
      <s:actionerror />
	  <s:fielderror />
        <s:form action="addCustomer" method="post" validate="true">
			<s:textfield name="userName" label="User Name" />
			<s:password name="encrPswd" label="Password" />
			<s:select name="securityQ" list="{'Best Friend Name','Mother Maiden Name','City You were Born'}" headerKey=""
			headerValue="SequrityQuestion" label="Select a Security Question" />
			<s:textfield name="securityA" label="Security Answer" />
			<s:submit method="add" key="Register" align="center" />
		</s:form>
      </sx:div>

      <sx:div id="two" label="Recover Password" theme="ajax">
        <s:form action="recoverCustomer" method="post" validate="true">
			<s:textfield name="userName" label="User Name" />
			<s:select name="securityQ" list="{'Best Friend Name','Mother Maiden Name','City You were Born'}" headerKey=""
			headerValue="SequrityQuestion" label="Select a Security Question" />
			<s:textfield name="securityA" label="Security Answer" />
			<s:submit method="recover" key="Recover" align="center" />
		</s:form>
      </sx:div>
      

     </sx:tabbedpanel>


</body>
</html>