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
          <div>
	          <a class="btn btn-success" href="LoginServlet" role="button">${user.name} (ログアウト)</a>
	          <a class="btn btn-success" href="showAccount.jsp" role="button">アカウント編集</a>
          </div>
        </div>
      </nav>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link fs-5" href="TaskServlet?user_id=${user.user_id}">全て</a>
        </li>
        <li class="nav-item">
            <a class="nav-link fs-5" href="DoingServlet?user_id=${user.user_id}">進行中</a>
        </li>
        <li class="nav-item">
            <a class="nav-link fs-5" href="DelayServlet?user_id=${user.user_id}">遅延中</a>
        </li>
        <li class="nav-item">
            <a class="nav-link fs-5" href="CompletedServlet?user_id=${user.user_id}">完了済み</a>
        </li>
      </ul>
