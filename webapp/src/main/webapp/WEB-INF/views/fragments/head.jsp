<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">

<!-- urls to css -->
<spring:url value="/resources/css/bootstrap.min.css"
	var="bootstrapMinCSS" />
<spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCSS" />
<spring:url value="/resources/css/main.css" var="mainCSS" />

<!-- Bootstrap -->
<link href="${bootstrapMinCSS}" type="text/css" rel="stylesheet" media="screen">
<link href="${fontAwesomeCSS}" type="text/css" rel="stylesheet" media="screen">
<link href="${mainCSS}" type="text/css" rel="stylesheet" media="screen">
</head>