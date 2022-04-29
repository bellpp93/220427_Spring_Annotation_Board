<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼 페이지 => insertUser.jsp</title>
<style>
#div_box {
   position: absolute;
   top: 10%;
   left: 40%;
}
</style>
</head>
<body>
	<div id="div_box">
		<h1>회원가입</h1>
		<form name="userForm" method="POST" action="insertUser.do">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">아이디</td>
					<td align="left"><input type="text" name="id"/></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">비밀번호</td>
					<td align="left"><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">이름</td>
					<td align="left"><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">직무</td>
					<td align="left"><input type="text" name="role"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="회원가입"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시쓰기"/>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>