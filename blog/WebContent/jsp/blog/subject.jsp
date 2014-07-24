<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${blogSubjectList}" var="blogSubject">
	<table class="table">
		<tr><td></td><td>${blogSubject.subjectTitle}</td><td></td></tr>
	</table>
</c:forEach>