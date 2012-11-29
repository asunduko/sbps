<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>  
<html>
<head>
<title>Funding Option - SBPS.com</title>
<sx:head parseContent="true"/>  
</head>

<body>
<h2>Funding Option Form</h2>



      <s:actionerror />
	  <s:fielderror />
        <s:form action="fundingOption" method="post" validate="true">
        	<s:select id="fundingType" label="Choose Funding Option " name="fundingType" headerValue="Select Available" headerKey="-1"
	                    list="{'Bank'}" >                   
	    	</s:select>
			<s:textfield name="bankAccountNumber" label="Bank Account Number (digits only)" />
			<s:textfield name="personalAccountNumber" label="Personal Account Number (digits only)" />
			<s:checkbox name="authorized" fieldValue="true" label="Check to authorize this account as primary funding source."/>
			<s:submit method="addOption" key="Submit" align="center" />
		</s:form>


</body>
</html>

