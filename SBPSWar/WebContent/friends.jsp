<%@ page contentType="text/html; ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Friends - SBPS.com</title>
<sx:head parseContent="true"/> 
	<style type="text/css">

		@import url(style.css);

	</style>
</head>

<body>


<sx:tabbedpanel id="resultPanel" doLayout="false">
      <sx:div id="catalog" label="Catalog Friends" 
        executeScripts="true" loadingText="Loading..."
      	theme="ajax" labelposition="top" >
      	

		<h3>Confirmed Friends </h3>
			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Friends</th>
						
					</tr>
					<s:iterator value="approvedFriends" status="userStatus" >
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="userName" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		<h3> Friends I Invited (Unconfirmed) </h3>	
		
			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Friend's ID</th>
						<th>Action</th>
					</tr>
					<s:iterator value="invitedUsers" status="userStatus" >
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="IUser.userName" /></td>
							
							<s:if test="IChallenge!=null">
								<s:if test="MResponse!=null && isAccepted.equals('N')">
								  <td>Response not accepted yet. </td>
								</s:if>
								<s:else>
									 <td><a 
										href="javascript: respond('<s:property value="invitationJoinId" />')" >
										Answer Challenge.
										</a></td>
								</s:else>		
							</s:if>
							<s:else>
							 <td> No Challenge Created Yet.</td>
							</s:else>
						</tr>
					</s:iterator>
				</table>
			</div>
        <h3> Invited by Others (Unconfirmed) </h3>	
			<div class="content">
				<table class="userTable" cellpadding="5px">
					<tr class="even">	
						<th>Friends</th>
						<th>Action</th>
					</tr>
					<s:iterator value="invitedByUsers" status="userStatus" >
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="MUser.userName" /></td>
							<s:if test="IChallenge==null">
							 <td><a href="javascript: createChallenge('<s:property value="invitationJoinId" />')" >Create a Challenge.</a></td>
							</s:if>
							<s:elseif test="MResponse ==null">
							 <td>User hasn't resp. to challenge.</td>
							</s:elseif>
							<s:elseif test="MResponse !=null">
							 <td><a href="javascript: reviewAnswer('<s:property value="invitationJoinId" />')" >Review Response.</a></td>
							</s:elseif>
						</tr>
					</s:iterator>
				</table>
			</div>
      </sx:div>	  
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="respond" label="Respond to Challenges" errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				
				Respond to Challenges (Email to I).
	  </sx:div>
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="createChallenge" label="Create a Challenge" errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				
				Create Challenges (Email M)/ Approve Responses (Email M)/ Deny Responses (Email M)
	  </sx:div>
	  <sx:div theme="ajax" executeScripts="true" refreshOnShow="true"
				id="reviewAnswer" label="Respond To Inv." errorText="errorText"
				showLoadingText="false" loadingText="Loading...">
				
				Respond to invitation ...
	  </sx:div>	
	  
	  

</sx:tabbedpanel>
</body>
</html>