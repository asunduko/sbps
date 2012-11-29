
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="rerender==false">
	 <s:if test="assignedBills.size()>0">
			
	 </s:if>
	</s:if>
<body>
<s:if test="assignedBills.size()>0">
<h3>Bills associated with Master Bill <s:property value="masterBill.billName"/> </h3>
	<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Assignee Name</th>
						<th>Amount</th>
						<th>Due Date</th>
						<th>Status</th>
						<th>Settlement Date</th>
					</tr>
					<s:iterator value="assignedBills" status="userStatus" >
							<tr
								class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
								<td><s:property 
									value="payer.userName" />
								</td>						
								<td><s:property value="amount" /></td>
								<td><s:property value="dueDate" /></td>
								<td><s:property value="humanReadableStatus" /></td>		
								<td>
								<s:if test="settlementDate == null">
								 Funds have not been transfered yet.
								</s:if>
								<s:else>
								 <s:property value="settlementDate" />
								</s:else>
							</td>						
						   </tr>
					</s:iterator>
				</table>
	</div>
</s:if>	
<s:else>

No Assigned Bills Yet.

</s:else>
</body>	