<%@ page contentType="text/html; ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Master Bills - SBPS.com</title>
<sx:head parseContent="true"/> 
	<style type="text/css">

		@import url(style.css);

	</style>
</head>
<body>
<h2>List of Master Bills </h2>
		

<sx:tabbedpanel id="resultPanel" doLayout="false">
      <sx:div id="catalog" label="Catalog MB" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" >

			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Bill Name</th>
						<th>Amount</th>
						<th>Action</th>
					</tr>
					<s:iterator value="mbs" status="userStatus" >
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property 
								value="billName" />
							</td>						
							<td><s:property value="amount" /></td>
							<td>
								<a href="javascript: showDetail('<s:property value="masterBillId" />')" >Edit MB/</a>
						    	<a href="javascript: assign('<s:property value="masterBillId" />')" >View/Split Bill</a>
						    </td>
							<!--<td><sx:bind id="ex2" href="%{#ajaxTest}" sources="button" targets="assign" events="onclick"/>
							<s:submit theme = "simple" label="Assign Amount" value="submit" id="button2" /></td>-->
						</tr>
					</s:iterator>
				</table>
			</div>
        
      </sx:div>
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="detail" label="Edit MB" errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				Please click "Edit" link back on the catalog page tab.
	  </sx:div>	
	  
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="assign" label="View/Split Bill" errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				Please, click on a "View/Split Bill" link back on the catalog page tab.
	  </sx:div>
	  
	  <!--<sx:div id="two" label="View All MBs" theme="ajax" href="showUserMBS" onselect="true" preload="false" >
        <s:if test="mbs.size() > 0">
			<div class="content">
			<table class="userTable" cellpadding="5px">
				<tr class="even">
					<th>Bill Name</th>
					<th>Amount</th>
				</tr>
				<s:iterator value="mbs" status="userStatus" >
					<tr
						class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
						<td><s:property value="billName" /></td>
						<td><s:property value="amount" /></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
	  </sx:div>-->
</sx:tabbedpanel>
</body>
</html>