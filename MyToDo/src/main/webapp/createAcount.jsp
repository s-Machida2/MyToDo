<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" type="text/css">
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

<h3>アカウント作成</h3>

	<form action="CreateAcountServlet" method="post">
		<div class="mb-3">
		    <label for="name" class="form-label">name</label>
		    <input name="name" type="text" class="form-control" id="name">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">e-mail</label>
		    <input name="e_mail" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword" class="form-label">password</label>
		    <input name="password" type="password" class="form-control" id="exampleInputPassword">
		  </div>
		  
		  <div class="d-flex align-items-center justify-content-end">
		  	  <a class="btn" href="login.jsp" role="button">戻る</a>
			  <button type="submit" class="btn btn-success">アカウント作成</button>
		  </div>
	</form>
	
</div>
</div>
</div>
	    
<jsp:include page="Footer.jsp"></jsp:include>