<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head parseContent="true"/> 
<title>Assign The Bill</title>
</head>
<body>
<s:actionerror />
	  <s:fielderror />
	<s:form action="createAssignmentSequence" >
		<s:select id="selectAssigneeElement" label="Assign to: " name="payer.userProfileId" headerValue="Select assignee" headerKey="-1"
	                    list="%{friends}" listValue="userName" 
	                    listKey="userProfileId" value="userProfileId" >                   
	    </s:select>
	    <s:hidden name="masterBill.masterBillId" value="%{mb.masterBillId}"/>
	   	<s:textfield name="billName" label="Bill Name" value="%{mb.billName}" readonly="true"/> 
		<s:textfield name="amount" label="Amount" />
		<sx:datetimepicker name="dueDate" label="Due Date (MM/dd/YYYY)" displayFormat ="MM-dd-yyyy" />	
		<sx:submit method="create" key="Assign" align="center"  targets="div"/>

	</s:form>

	<s:url id="url" value="/assignedBills.action" >
		<s:param name="masterBillId" value="%{mb.masterBillId}" />
	</s:url>
	<sx:div id="div" preload="true" href="%{#url}">
		<s:actionerror />
	  	<s:fielderror />
	</sx:div>
</body>



</html>