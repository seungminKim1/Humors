<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

    String id=(String)session.getAttribute("id");

%>

 

<html>

<head>

    <title></title>

    <script language="javascript">

    function addboard(){

        boardform.submit();

    }

    </script>

</head>

<body>

<!-- 게시판 등록 -->

<form action="./BoardAddAction.bo" method="post"

    enctype="multipart/form-data" name="boardform">

<input type="hidden" name="board_id" value="<%=id %>">

<table cellpadding="0" cellspacing="0">

    <tr align="center" valign="middle">

        <td colspan="5">게시글 작성</td>

    </tr>

    <tr>

        <td style="font-family:돋음; font-size:12" height="16">

            <div align="center">글쓴이</div>

        </td>

        <td>

            <%=id %>

        </td>

    </tr>

    <tr>

        <td style="font-family:돋음; font-size:12" height="16">

            <div align="center">제 목</div>

        </td>

        <td>

            <input name="board_subject" type="text" size="50" maxlength="100"

                value=""/>

        </td>

    </tr>

    <tr>

        <td style="font-family:돋음; font-size:12">

            <div align="center">내 용</div>

        </td>

        <td>

            <textarea name="board_content" cols="67" rows="15"></textarea>

        </td>

    </tr>

    <tr>

        <td style="font-family:돋음; font-size:12">

            <div align="center">파일 첨부</div>

        </td>

        <td>

            <input name="board_file" type="file"/>

        </td>

    </tr>

    <tr bgcolor="cccccc">

        <td colspan="2" style="height:1px;">

        </td>

    </tr>

    <tr><td colspan="2">&nbsp;</td></tr>

    <tr align="center" valign="middle">

        <td colspan="5">

            <a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;

            <a href="javascript:history.go(-1)">[back]</a>

        </td>

    </tr>

</table>

</form>

<!-- 게시판 등록 -->

</body>

</html>