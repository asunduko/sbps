<%@ page contentType="text/html; ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>My Bills - SBPS.com</title>
<sx:head parseContent="true"/> 
	<style type="text/css">

		@import url(style.css);

	</style>

</head>

<script>
window.review = function review(id){
	var widget=dojo.widget.byId("review");
	widget.href="<%=request.getContextPath()%>/reviewMyBills.action?assignedBillId="+id;
	dojo.widget.byId("resultPanel").selectChild("review");
	//alert ("This is a JavaScript ALERT box.");
};
</script>


<body>

		<h2>Lists of All Payments </h2>
		<br>
<sx:tabbedpanel id="resultPanel" doLayout="false">
      <sx:div id="bills" label="My Bills" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" >
      	
      	<h3> My Approved Bills </h3>	
		
			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Amount</th>
						<th>Due Date</th>
						<th>Status</th>
						<th>Settlement Date</th>
					</tr>
					<s:iterator value="myBills" status="userStatus" >
						<s:if test="humanReadableStatus.equals('Approved') || humanReadableStatus.equals('Funds Transferred')">
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">

							<td><s:property value="amount" /></td>
							<td><s:property value="dueDate" /></td>
							<td>
								<s:property value="humanReadableStatus" />
							</td>
							<td>
								<s:if test="settlementDate == null">
								 Funds have not been transfered yet.
								</s:if>
								<s:else>
								 <s:property value="settlementDate" />
								</s:else>
							</td>
						</tr>
						</s:if>
					</s:iterator>
				</table>
			</div>
      	
		<h3> My Unapproved Bills </h3>	
		
			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Master Detail</th>
						<th>Amount</th>
						<th>Due Date</th>
						<th>Status</th>
					</tr>
					<s:iterator value="myBills" status="userStatus" >
						<s:if test="humanReadableStatus.equals('Assigned') || humanReadableStatus.equals('Disputed')">
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">

							 <td><a 
								href="javascript: review('<s:property value="assignedBillId" />')" >
								Admin.
								</a></td>

							<td><s:property value="amount" /></td>
							<td><s:property value="dueDate" /></td>
							<td>	
								<s:property value="humanReadableStatus" />
							</td>
						</tr>
						</s:if>
					</s:iterator>
				</table>
			</div>
      </sx:div>
	  
	  
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="review" label="Admin My Bills" errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				
				Pick a bill to admin.
	  </sx:div>
	  

</sx:tabbedpanel>

</body>
</html>
