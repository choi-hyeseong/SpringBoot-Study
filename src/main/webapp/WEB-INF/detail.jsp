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
        <c:forEach var="file" items="${files}">
            <tr>
                <td colspan="4"><img width="1000" height="1000" src="http://127.0.0.1:8080/board/image?id=${file.id}">
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4" height="400">${board.text}</td>
        </tr>
    </table>
    <p></p>
    <button id="edit_btn">수정</button>
    <button id="del_btn">삭제</button>
    <p></p>
    <p>댓글</p>
    <table>
        <c:forEach var="reply" items="${replies}">
        <tr>
            <td width="100">${reply.username}</td>
            <td>${reply.text} <button id="delete_reply_${reply.id}" onclick="deleteReply(this.id)">X</button></td>
        </tr>
        </c:forEach>
    </table>
    <p></p>
    <input style="height: 100px; width: 1500px" type='text' name='text' id="text" value=""/>
    <p></p>
    <button id="reply_send" onclick="replySend()">댓글 달기</button>
</div>

<script>
    function onLoad() {
        if ("${board.user.username}" !== "${current_user}") { //문자열로 치환됐었지..
            //작성자와 보는사람이 일치하지 않을경우
            $("#edit_btn").hide();
            $("#del_btn").hide();
        } else {
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
    function replySend() { //json
        let data = {boardId: ${board.id}, text: $("#text").val()}
        $.ajax({
            url : "http://127.0.0.1:8080/board/reply/write",
            async: false,
            type: "post",
            data: JSON.stringify(data),
            contentType: "application/json",
            success : (res) => {
                window.location.reload(); //현재페이지 리로드
            },
            error : (request, status, error) => {
                alert("오류가 발생했습니다.");
            }
        })
    }
    function deleteReply(id) {
        let rep = parseInt(id.toString().replace("delete_reply_", ""));
        $.ajax({
                url: "/board/reply/delete",
                type: "DELETE",
                async: false,
                dataType: "text", //text로 해야 json인가 리턴값 확인되는듯
                data: {
                    id: rep
                },
                success: function (response) {
                    console.log(response);
                    alert("댓글 삭제 완료.")
                    location.reload();
                },

            })
    }
</script>
</body>
</html>