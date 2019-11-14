<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "humor.Bean.*" %>
<%
	MemberBean member = (MemberBean)request.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<table border = "1" width = "300">
	<tr align = "center">
		<td>아이디 </td>
		<td><%=member.getMember_id() %></td>
	</tr>
	<tr align = "center">
		<td>비밀번호 </td>
		<td><%=member.getMember_pw() %></td>
	</tr>
	<tr align = "center">
		<td>이름 </td>
		<td><%=member.getMember_name() %></td>
	</tr>
	<tr align = "center">
		<td>나이 </td>
		<td><%=member.getMember_age() %></td>
	</tr>
	<tr>
		<td colspan = "2">
			<a href = "./MemberListAction.me">리스트로 돌아가기</a>
		</td>
	</tr>
</table>
</center>
</body>
</html>