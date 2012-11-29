<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/struts-tags"%>
<s:if test="#session.login != true">
<a href="<s:url action="addUserLink"/>" >Register</a><br>
</s:if>
<a href="<s:url action="linkLogin"/>" >Login</a><br>
<s:if test="#session.login == true">
	<dd>
		<a href="<s:url action="logOut"/>" >Log Out</a><br>
		<a href="<s:url action="masterChain"/>" >*Create Master Bill</a><br>
		<a href="<s:url action="myPaymentsListLink"/>" >*View/Pay Balances</a><br>
		<a href="<s:url action="friendsLink"/>" >Friends</a><br>
		<s:if test="#session.showall == true">
			<a href="<s:url action="masterListLink"/>" >View/Split Bills</a><br>
		</s:if>
	</dd>
</s:if>
<a href="<s:url action="inviteUserLink"/>" >Invite Friends</a><br>

