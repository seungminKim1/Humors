<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="humor.Bean.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%

    String id=null;

    if(session.getAttribute("id")!=null){

        id=(String)session.getAttribute("id");

    }
    List boardList=(List)request.getAttribute("boardlist");
    int listcount=((Integer)request.getAttribute("listcount")).intValue();
    
    int nowpage=((Integer)request.getAttribute("page")).intValue();

    int maxpage=((Integer)request.getAttribute("maxpage")).intValue();

    int startpage=((Integer)request.getAttribute("startpage")).intValue();

    int endpage=((Integer)request.getAttribute("endpage")).intValue();
	
%>

<html>

<head>
    <title>유머 게시판</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 게시판 리스트 -->
<h5>${id} 님 환영합니다.</h5>
<table>
    <tr align="center" valign="middle">
        <td colspan="4">유머 게시판</td>
        <td align=right>
            <font size=2>글 개수 : ${listcount }</font>
        </td>
    </tr>
    <tr align="center" valign="middle" bordercolor="#333333">
        <td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26">
            <div align="center">번호</div>
        </td>
        <td style="font-family:Tahoma;font-size:8pt;" width="50%">
            <div align="center">제목</div>
        </td>
        <td style="font-family:Tahoma;font-size:8pt;" width="15%">
            <div align="center">작성자</div>
        </td>
        <td style="font-family:Tahoma;font-size:8pt;" width="17%">
            <div align="center">날짜</div>
        </td>
        <td style="font-family:Tahoma;font-size:8pt;" width="5%">
            <div align="center">조회수</div>
        </td>
        <td style="font-family:Tahoma;font-size:8pt;" width="5%">
            <div align="center">추천수</div>
        </td>
    </tr>
    <%
        for(int i=0;i<boardList.size();i++){
            MemberBoardBean bl=(MemberBoardBean)boardList.get(i);
    %>
    <tr align="center" valign="middle" bordercolor="#333333"
        onmouseover="this.style.backgroundColor='F8F8F8'"
        onmouseout="this.style.backgroundColor=''">
        <td height="23" style="font-family:Tahoma;font-size:10pt;">
            <%=bl.getBoard_num()%>
        </td>        
        <td style="font-family:Tahoma;font-size:10pt;">
            <div align="left">
            <%if(bl.getBoard_re_lev()!=0){ %>
                <%for(int a=0;a<=bl.getBoard_re_lev()*2;a++){ %>
                &nbsp;
                <%} %>
 				▶
            <%}else{ %>
             	  ▶
            <%} %>
            <a href="./BoardDetailAction.bo?num=<%=bl.getBoard_num()%>">
                <%=bl.getBoard_subject()%>
            </a>
            </div>
      	</td>      
       <td style="font-family:Tahoma;font-size:10pt;">

            <div align="center"><%=bl.getBoard_id()%></div>

        </td>

        <td style="font-family:Tahoma;font-size:10pt;">

            <div align="center"><%=bl.getBoard_date() %></div>

        </td>    

        <td style="font-family:Tahoma;font-size:10pt;">

            <div align="center"><%=bl.getBoard_readcount()%></div>

        </td>
        <td style="font-family:Tahoma;font-size:10pt;">

            <div align="center"><%=bl.getBoard_like()%></div>
        </td>

    </tr>

    <%} %>

    <tr align=center height=20>

        <td colspan=7 style=font-family:Tahoma;font-size:10pt;>

            <%if(nowpage<=1){ %>

            [pre]&nbsp;

            <%}else{ %>

            <a href="./BoardList.bo?page=<%=nowpage-1 %>">[pre]</a>&nbsp;

            <%} %>

            

            <%for(int a=startpage;a<=endpage;a++){

                if(a==nowpage){%>

                [<%=a %>]

                <%}else{ %>

                <a href="./BoardList.bo?page=<%=a %>">[<%=a %>]</a>

                &nbsp;

                <%} %>

            <%} %>

            

            <%if(nowpage>=maxpage){ %>

            [next]

            <%}else{ %>

            <a href="./BoardList.bo?page=<%=nowpage+1 %>">[next]</a>

            <%} %>

        </td>

    </tr>

    <tr align="right">

        <td colspan="5">

            <%if(id!=null && id.equals("admin")){%>

                <a href="./MemberListAction.me">[management]</a>

            <%}%>

             <a href="./BoardWrite.bo">[write]</a>
			 <a href = "./MemberLogout.me">[logout]</a>
        </td>

    </tr>

</table>

</body>

</html>
