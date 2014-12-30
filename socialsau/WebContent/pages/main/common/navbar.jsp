<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-brand">
			<s:a action="main" namespace="/"
				cssStyle="color: #999; font-weight: bold; text-decoration: none;">
				<span class="glyphicon glyphicon-leaf"></span> &nbsp;Social SAU
				</s:a>
		</div>
		
		<c:if test="${sessionScope['user'].role == 'user'}">
			<ul class="nav navbar-nav navbar-right">
				<li><s:a action="%{#session.user.urlProfile}" namespace="/profile">Profile</s:a></li>
				<li><s:a action="all" namespace="/friend">Friends</s:a></li>
				<%-- <li><s:a action="send" namespace="/contact">Contact </s:a></li> --%>
				<li class="dropdown">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><s:a action="personalData" namespace="/setting">Settings</s:a></li>
						<li class="divider"></li>
						<li><s:a action="logout" namespace="/">Logout</s:a></li>
					</ul>
				</li>
			</ul>
		</c:if>
		
		<c:if test="${sessionScope['user'].role == 'admin'}">
			<ul class="nav navbar-nav navbar-right">
				<%-- <s:url var="url" action="userProfile" namespace="/profile">
					<s:param name="userId">${sessionScope['user'].userId}</s:param>
				</s:url> --%>
				<li><s:a action="%{#session.user.urlProfile}" namespace="/profile">Profile</s:a></li>
				<li><s:a action="all" namespace="/friend">Friends</s:a></li>
				<%-- <li><s:a action="userContact" namespace="/admin">Users Contact</s:a></li> --%>
				<li><s:a action="all" namespace="/tutorial">Management</s:a></li>
				<li class="dropdown">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><s:a action="personalData" namespace="/setting">Settings</s:a></li>
						<li class="divider"></li>
						<li><s:a action="logout" namespace="/">Logout</s:a></li>
					</ul>
				</li>
			</ul>
		</c:if>
	</div>
</div>

