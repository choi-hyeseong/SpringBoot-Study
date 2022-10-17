<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<style>
    table {
        width: 100%;
        border: 1px solid #444444;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #444444;
    }
</style>
<title>게시판 상세보기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body onload="onLoad()">

<div class="container">
    <table>
        <!--tr : 행 td : 열 -->
        <!--$형식으로 매핑되는 데이터는 컨트롤러 모델에서 replace 후 전송됨 (클라 모름)-->
        <tr>
            <td width="30">${board.id}</td>
            <td width="500">${board.title}</td>
            <td>${board.user.username}</td>
            <td>${board.writtenDate}</td>
        </tr>
        <tr>
            <td colspan="4" height="400">${board.text}</td>
        </tr>
    </table>
    <p></p>
    <button id="edit_btn">수정</button>
    <button id="del_btn">삭제</button>
</div>

<script>
    function onLoad() {
        if ("${board.user.username}" !== "${current_user}") { //문자열로 치환됐었지..
            //작성자와 보는사람이 일치하지 않을경우
            $("#edit_btn").hide();
            $("#del_btn").hide();
        }
        else {
            $("#del_btn").on("click", () => {
                $.ajax({
                    url: "/board/delete",
                    type: "POST",
                    async: false,
                    dataType: "text", //text로 해야 json인가 리턴값 확인되는듯
                    data: {
                        id: "${board.id}"
                    },
                    success: function (response) {
                        console.log(response);
                        alert("게시글 삭제 완료.")
                        location.href = "/board"
                    },

                })
            })
            $("#edit_btn").on("click", () => {
                location.href = "/board/edit?id=${board.id}"
            })
        }
    }
</script>
</body>
</html>