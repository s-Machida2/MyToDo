<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="Header.jsp"></jsp:include>

<div class="container mt-5">
<div class="row">
<div class="col-6 offset-3">
	<form action="EditTaskServlet" method="post">    
    <div class="mb-3 row">
    <label for="title" class="col-sm-3 col-form-label">タイトル</label>
        <div class="col-sm-9">
            <input id="title" type="text" name="title" value="${edit.title}" class="form-control" maxlength="20" required placeholder="20文字以内で入力してください">
        </div>
    </div>

    <div class="mb-3 row">
    <label for="priority" class="col-sm-3 col-form-label">優先度</label>
    <div class="col-sm-9">
        <select id="priority" class="form-select" name="priority">
            <option value="3" ${edit.priority == '3' ? 'selected' : ''}>高</option>
            <option value="2" ${edit.priority == '2' ? 'selected' : ''}>中</option>
            <option value="1" ${edit.priority == '1' ? 'selected' : ''}>低</option>
        </select>
    </div>
	</div>

    <div class="mb-3 row">
        <label for="deadline" class="col-sm-3 col-form-label">期限</label>
        <div class="col-sm-9">
            <input id="deadline" type="date" value="${edit.deadline}" name="deadline" class="form-control">
        </div>
    </div>

    <div class="mb-3 row">
        <label for="content" class="col-sm-3 col-form-label">内容</label>
        <div class="col-sm-9">
            <textarea id="content" rows="3" name="content" class="form-control" maxlength="100" required placeholder="100文字以内で入力してください">${edit.content}</textarea>
        </div>
    </div>

    <div class="d-flex align-items-center justify-content-end">
        <a class="btn" href="TaskServlet" role="button">戻る</a>
	<button class="btn btn-success" type=submit value="${edit.id}" name="id">編集</button>
    </div>
                
	</form>
</div>
</div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>