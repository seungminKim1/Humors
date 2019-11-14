<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "humor.Bean.*" %>
<%

    String id=(String)session.getAttribute("id");

    MemberBoardBean board = (MemberBoardBean)request.getAttribute("boarddata");

%>

 

<html>

<head>

    <title>유머 게시판</title>

    <script type="text/javascript">

    function modifyboard(){

        modifyform.submit();

    }

    </script>

</head>

 

<body>

<!-- 게시판 수정 -->

<form action="BoardModifyAction.bo" method="post" name="modifyform">

<input type="hidden" name="board_num" value="<%=board.getBoard_num() %>">

<input type="hidden" name="board_id" value="<%=id %>">

 

<table cellpadding="0" cellspacing="0">

    <tr align="center" valign="middle">

        <td colspan="5">유머 게시판</td>

    </tr>

    <tr>

        <td height="16" style="font-family:돋음; font-size:12">

            <div align="center">제 목</div>

        </td>

        <td>

            <input name="board_subject" size="50" maxlength="100"

                value="<%=board.getBoard_subject()%>">

        </td>

    </tr>

    <tr>

        <td style="font-family:돋음; font-size:12">

            <div align="center">내 용</div>

        </td>

        <td>

            <textarea name="board_content" cols="67" rows="15">

            <%=board.getBoard_content() %>

            </textarea>

        </td>

    </tr>

    <%if(!(board.getBoard_file()==null)){ %>

    <tr>

        <td style="font-family:돋음; font-size:12">

            <div align="center">파일 첨부</div>

        </td>

        <td>

            &nbsp;&nbsp;<%=board.getBoard_file() %>

        </td>

    </tr>

    <%} %>

    

    <tr bgcolor="cccccc">

        <td colspan="2" style="height:1px;">

        </td>

    </tr>

    <tr><td colspan="2">&nbsp;</td></tr>

    

    <tr align="center" valign="middle">

        <td colspan="5">

            <font size=2>

            <a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;

            <a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;

            </font>

        </td>

    </tr>

</table>

</form>

<!-- 게시판 수정 -->

</body>

</html>

