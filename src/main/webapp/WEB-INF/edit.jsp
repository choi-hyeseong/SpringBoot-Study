<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<style>
    table {
        width: 100%;
        border: 1px solid #444444;
        border-collapse: separate;
        border-spacing: 0 0px;
    }

</style>
<title>게시글 수정</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<h1>글수정</h1>
<div class="container">
    <table>
        <!--폼 안쓰고 ajax-->
        <tr>
            <td><h4>제목</h4></td>
        </tr>
        <tr>
            <td><input type='text' name='title' id="title" value='${board.title}'></td>
        </tr>
        <tr>
            <td><h4>내용</h4></td>
        </tr>
        <tr>
            <td>
                <input type='text' name='text' id="text" value='${board.text}' style="width:1000px;height:200px;font-size:10px;">
            </td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="edit" onclick="onEdit()"/></td> <!--제출용 버튼-->
        </tr>
    </table>
</div>

<script>
    function onEdit() {
        $.ajax({
            url: "/board/edit",
            type: "PUT",
            async: false,
            dataType: "text", //text로 해야 json인가 리턴값 확인되는듯
            data: {
                id: "${board.id}",
                title: $("#title").val(),
                text: $("#text").val()
            },
            success: function (response) {
                console.log(response);
                alert("게시글 수정 완료.")
                location.href = "/board"
            },

        })
    }
</script>

</body>
</html>