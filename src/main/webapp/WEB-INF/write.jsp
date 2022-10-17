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
<title>게시글 작성</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<h1>글작성</h1>
<div class="container">
    <table>
        <form name="f" action="/board/write" method="post" enctype="multipart/form-data"> <!--파일 업로드시 enctype 지정필요-->
            <tr>
                <td><h4>제목</h4></td>
            </tr>
            <tr>
                <td><input type='text' name='title' id="title" value=''></td>
            </tr>
            <tr>
                <td><h4>내용</h4></td>
            </tr>
            <tr>
                <td><input type='text' name='text' id="text" value='' style="width:1000px;height:200px;font-size:10px;"></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="write"/></td>

            </tr>
            <tr>
                <td><input name="file" type="file" multiple="multiple" value="file"/></td>
            </tr>
        </form>
    </table>
</div>

</body>
</html>