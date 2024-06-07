<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" type="text/css">
<script src="js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" style="margin-right: 80px;" href="TaskServlet">
            <img src="img.html/MyToDo.jpeg" width="150" height="50" style="margin-right: 10px;">
          </a>
        </div>
      </nav>

<div class="container mt-5">
<div class="row">
<div class="col-4 offset-4">

<h3>アカウント編集</h3>

	<form action="EditAccountServlet" method="post">
		<div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">name</label>
		    <input name="name" type="text" class="form-control" id="exampleInputPassword1" value="${ user.name }">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">e-mail</label>
		    <input name="e_mail" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${ user.e_mail }">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">password</label>
		    <input name="password" type="text" class="form-control" id="exampleInputPassword1" value="${ user.password }">
		  </div>
		  
		  <div class="d-flex align-items-center justify-content-end">
		  	  <a class="btn mr-2" href="TaskServlet" role="button">戻る</a>
		  	  <a class="btn btn-danger mr-2" href="DeleteAccountServlet?user_id=${ user.user_id }" role="button" data-bs-toggle="modal" data-bs-target="#deleteModal">削除</a>
			  <button type="submit" class="btn btn-success" value="${ user.user_id }" name="user_id">変更</button>
			  
			  <!-- Modal 削除ボタン -->
			<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="deleteModalLabel">アカウントを削除しますか？</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">キャンセル</button>
			        <a class="btn btn-danger mr-2" href="DeleteAccountServlet?user_id=${ user.user_id }" role="button">削除</a>
			      </div>
			    </div>
			  </div>
			</div>
		  </div>
	</form>
	
</div>
</div>
</div>
	    
<jsp:include page="Footer.jsp"></jsp:include>