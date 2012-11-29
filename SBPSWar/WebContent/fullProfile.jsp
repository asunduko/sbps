<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Full Profile - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Full Profile Form</h2>



      <s:actionerror />
	  <s:fielderror />
        <s:form action="masterChain" method="post" validate="true">
        	<s:hidden name="fullProfile" value ="full"/>
			<s:textfield name="firstName" label="First Name" />
			<s:textfield name="lastName" label="Last Name" />
			<s:textfield name="ssn" label="SSN (XXX-XX-XXXX)" />
			<sx:datetimepicker name="dob" label="DOB (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />
			<s:submit method="fullProfile" key="Submit" align="center" />
		</s:form>
     


</body>
</html>

