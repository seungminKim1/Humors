<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "humor.Bean.*" %>
<%

    String id=(String)session.getAttribute("id");

    MemberBoardBean board=(MemberBoardBean)request.getAttribute("boarddata");

%>

 

<html>

<head>

    <title>제목학원</title>

    <script language="javascript">

    function replyboard(){

        boardform.submit();

    }

    </script>

</head>

<body>

<!-- 게시판 답변 -->

<form action="./BoardReplyView.bo" method="post" name="boardform">

<input type="hidden" name="board_num" value="<%=board.getBoard_num()%>">

<input type="hidden" name="board_re_ref" value="<%=board.getBoard_re_ref()%>">

<input type="hidden" name="board_re_lev" value="<%=board.getBoard_re_lev()%>">

<input type="hidden" name="board_re_seq" value="<%=board.getBoard_re_seq()%>">

<input type="hidden" name="board_id" value="<%=id %>">

 

<table cellpadding="0" cellspacing="0">

    <tr align="center" valign="middle">

        <td colspan="5">제목학원</td>

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

            <input name="board_subject" type="text" size="50"

                maxlength="100" value="Re: <%=board.getBoard_subject()%>"/>

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

    <tr bgcolor="cccccc">

        <td colspan="2" style="height:1px;">

        </td>

    </tr>

    <tr><td colspan="2">&nbsp;</td></tr>

    

    <tr align="center" valign="middle">

        <td colspan="5">

        <a href="javascript:replyboard()">[등록]</a>&nbsp;&nbsp;

        <a href="javascript:history.go(-1)">[뒤로]</a>

        </td>

    </tr>

</table>

</form>

<!-- 게시판 답변 -->

</body>

</html>