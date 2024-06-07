<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" type="text/css">
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="col-10 offset-sm-1 row justify-content-end mt-4">
		    <div class="d-flex align-items-center justify-content-end">
		    <a class="btn btn-success" href="AddTask.jsp" role="button"> + 追加</a>
		    </div>
		</div>
	</div>
	
	<div class="row mt-4">
	<div class="col-10 offset-1 bg-gray-300">
	<div class="container d-flex justify-content-center">
		<tbody>
	    <table class="table table-striped table table-hover shadow-sm p-3 mb-5 bg-body rounded" style="table-layout:fixed;width:100%;">
		   <thead class="thead-dark">
		       <tr>
				<th class="col-xs-2 text-center" style="width: 10%;"><a href="SortdServlet" class="btn">期限 ${requestScope['markd']}</a></th>
				<th class="col-xs-2 text-center" style="width: 10%;"><a href="SortpServlet" class="btn">優先度 ${requestScope['markp']}</a></th>
				<th class="col-xs-2 text-center btn-block"style="width: 15%;">タイトル</th>
				<th class="col-xs-4 text-center btn-block">内容</th>
				<th class="col-xs-2 text-center btn-block" style="width: 10%;">進捗</th>
				<th class="col-xs-2 text-center btn-block" style="width: 10%;"></th>
				<th class="col-xs-2 text-center btn-block" style="width: 10%;"></th>
		       </tr>
		   </thead>		
	                        	
		<!-- selectAll -->
		  <c:forEach var="a" items="${task}">
		         <tr>
				
		         <td class="text-center"><c:out value="${a.deadline}"></c:out></td>
		         <td class="text-center"><c:out value="${a.priority}"></c:out></td>
		         <td class="text-center" style="word-wrap:break-word;"><c:out value="${a.title}"></c:out></td>
		         <td class="text-center" style="word-wrap:break-word;"><c:out value="${a.content}"></c:out></td>
		         
		         <!-- 完了/未完了 -->
		         <c:if test="${a.completed eq 0}">
		         <td class="text-center"><a class="btn btn-danger" href="UpdateCompletedServlet?id=${a.id}&amp;completed=${a.completed}" role="button">未完了</a></td>
		         </c:if>
		         <c:if test="${a.completed eq 1}">
		         <td class="text-center"><a class="btn btn-primary" href="UpdateCompletedServlet?id=${a.id}&amp;completed=${a.completed}" role="button">完了</a></td>
		         </c:if>
		         <!-- 編集 -->
		         <td class="text-center"><a class="btn btn-warning" href="EditShowServlet?id=${a.id}" role="button">編集</a></td>
		         <!-- 削除 -->
		         <td class="text-center"><a class="btn btn-secondary" href="DeleteServlet?id=${a.id}" role="button">削除</a></td>
		         </tr>
		         
		   </c:forEach>
	    </table>
		</tbody>
	</div>
	</div>
	</div>
	    
<jsp:include page="Footer.jsp"></jsp:include>