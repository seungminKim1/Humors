<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="humor.Bean.*" %>
<%@ page import = "java.util.*" %>
<%
    MemberBoardBean board = (MemberBoardBean)request.getAttribute("boarddata");
	List rep = (List)request.getAttribute("replydata");
%>
<html>
<head>
    <title>제목학원</title>
</head>
<body>

<!-- 게시판 수정 -->

<table cellpadding="0" cellspacing="0">
    <tr align="center" valign="middle">
        <td colspan="5">제목학원</td>
    </tr>
       
    <tr>
        <td style="font-family:돋음; font-size:12" height="16">
            <div align="center">제 목&nbsp;&nbsp;</div>
        </td>
        <td style="font-family:돋음; font-size:12">
        <%=board.getBoard_subject()%>
        </td>
    </tr>

    <tr bgcolor="cccccc">
        <td colspan="2" style="height:1px;">
        </td>
    </tr>

    <tr>
        <td style="font-family:돋음; font-size:12">
            <div align="center">내 용</div>
        </td>
        <td style="font-family:돋음; font-size:12">
            <table border=0 width=490 height=250 style="table-layout:fixed">
                <tr>
                    <td valign=top style="font-family:돋음; font-size:12">
                    <%=board.getBoard_content()%>
                    <%if((board.getBoard_file() != null )) { %>
                    <img src = "./boardupload/<%=board.getBoard_file()%>" width = 500 height = 500 />
					<% } %>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td style="font-family:돋음; font-size:12">
            <div align="center">첨부파일</div>
        </td>
        <td style="font-family:돋음; font-size:12">
        <%if((board.getBoard_file()!=null)){ %>
        <a href="./boardupload/<%=board.getBoard_file()%>">
            <%=board.getBoard_file() %>
        </a>
        <%} %>
        </td>
    </tr>
    <tr bgcolor="cccccc">
        <td colspan="2" style="height:1px;"></td>
    </tr>
    <tr><td colspan="2">&nbsp;</td></tr>
    <tr align="center" valign="middle">
        <td colspan="5">
            <font size=2>
            <a href="./BoardReplyAction.bo?num=<%=board.getBoard_num() %>">
            [답변]
            </a>&nbsp;&nbsp;
            <a href="./BoardModify.bo?num=<%=board.getBoard_num() %>">
            [수정]
            </a>&nbsp;&nbsp;
            <a href="./BoardDeleteAction.bo?num=<%=board.getBoard_num() %>"
            >
            [삭제]
            </a>&nbsp;&nbsp;
            <a href="./LikeAddAction.bo?num=<%=board.getBoard_num() %>"
            >
            [좋아요]
            </a>
            &nbsp;&nbsp;
            <a href="./BoardList.bo">[목록]</a>&nbsp;&nbsp;
            </font>
        </td>
    </tr>
</table>
<% if (rep.size() != 0 ) { %>
	<% 	for(int i = 0;i<rep.size();i++ ) { 
		ReplyBean rp = (ReplyBean)rep.get(i);
	%>
<table>
	<tr>
		<td>
			<%=rp.getReply_id() %>
		</td>
		<td>
			<%=rp.getReply_content() %>
		</td>
	</tr>
</table>
	<%} %>
<%} %>
<form  name = "replyform" action="./ReplyAddAction.bo" method = "post">
	<table>
		<input type = "hidden" name = "board_num" value = <%=board.getBoard_num() %>>
		<input type = "hidden" name = "reply_id" value= <%=board.getBoard_id() %>>
		<tr>
			<td><textarea name = reply_content cols = "100" row = "2"></textarea></td>
			<td><input type = "submit" value = "등록"></td>
		</tr>
	</table>
</form>
</body>

</html>
