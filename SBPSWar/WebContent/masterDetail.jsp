<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Master Bill - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Master Detail</h2>

    <s:actionerror />
	  <s:fielderror />

        <s:form action="updateMB" method="post" validate="true" >
        	<s:hidden name="masterBillId" value="%{mb.masterBillId}"/>
			<s:textfield name="billName" label="Bill Name" value="%{mb.billName}" />
			<s:textfield name="amount" label="Amount" value="%{mb.amount}"/>
			<sx:datetimepicker name="issueDate" value="%{mb.issueDate}" label="Issue Date (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />
			<sx:datetimepicker name="dueDate" value="%{mb.dueDate}" label="Due Date (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />
			 
			<s:submit method="update" key="Update Information" align="center" />			
		</s:form>
		
		
</body>
</html>