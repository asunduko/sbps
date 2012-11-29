<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Admin My Bill - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>

    <s:actionerror />
	  <s:fielderror />
		<h3>Master Bill Detail.</h3>
		<hr color="red" width="255" align="left"/>
		<div class="content">
		<table class="userTable" cellpadding="5px">
					<tr>	
						<td class="even">Master Bill ID:</td>
						<td class="odd"><s:property value="ab.masterBill.masterBillId" /></td>
					</tr>
					<tr>	
						<td class="even">Bill Name:</td>	
						<td class="odd"><s:property value="ab.masterBill.billName" /></td>
					</tr>
					<tr>	
						<td class="even">Total Bill Amount:</td>	
						<td class="odd"><s:property value="ab.masterBill.amount" /></td>
					</tr>
					<tr>	
						<td class="even">Issue Date:</td>
						<td class="odd"><s:property value="ab.masterBill.issueDate" /></td>	
					</tr>
					<tr>	
						<td class="even">Due Date: </td>	
						<td class="odd"><s:property value="ab.masterBill.dueDate" /> </td>
					</tr>		        	
		</table>
		</div>		
		
		
		<h3> All Bills associated with Master Bill <s:property value="ab.masterBill.billName"/> </h3>
		<hr color="red" width="255" align="left"/>
		<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Assignee Name</th>
						<th>Amount</th>
						
					</tr>
					<s:iterator value="slaveBills" status="userStatus" >
										<tr
											class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
											<td><s:property 
												value="payer.userName" />
											</td>						
											<td><s:property value="amount" /></td>
											
										</tr>
					</s:iterator>
				</table>
		</div>
		
		<h3> Review and take action</h3>
		<hr color="red" width="255" align="left"/>
		<div class="content">
		<s:form action="evaluateAssignment" >
		    <s:hidden name="assignedBillId" value="%{ab.assignedBillId}"/>
		   	<s:textfield name="billName" label="Bill Name" value="%{ab.masterBill.billName}" readonly="true"/> 
			<s:textfield name="amount" label="Amount" value="%{ab.amount}" readonly="true"/>
			<s:textfield name="dueDate" label="Due Date" value="%{ab.dueDate}" readonly="true"/>
			<s:radio id="approve" name="approve" list="{'Y', 'N', 'Disp.'}" label="Yes to initiate payment, Dispute amount,No to leave unpaid "/>
			
			<s:submit method="evaluateAssignment" key="Submit" align="center"/>
		</s:form>
		</div>
		
</body>
</html>
