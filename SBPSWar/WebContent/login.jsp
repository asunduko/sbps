<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Login Page SBPS</title>
</head>

<body>
<h2>Login to SBPS</h2>
<s:actionerror />
<s:form action="executeLogin" method="post">
	<s:textfield name="username" label="Username" size="20" />
	<s:password name="password" label="Password" size="20" />
	<s:submit method="execute" label="Login" align="center" />
</s:form>
</body>
</html>

