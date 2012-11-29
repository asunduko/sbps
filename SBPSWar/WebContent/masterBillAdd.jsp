<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Master Bill - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Master Bill Form</h2>

    <s:actionerror />
	  <s:fielderror />
        <s:form action="addMaster" method="post" validate="true">
        	<s:hidden name="beneficiary.userProfileId" value="4"/>
			<s:textfield name="billName" label="Bill Name" />
			<s:textfield name="amount" label="Amount" />
			<sx:datetimepicker name="issueDate" label="Issue Date (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />
			<sx:datetimepicker name="dueDate" label="Due Date (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />
			<s:submit method="add" key="Submit" align="center" />			
		</s:form>
 
</body>
</html>