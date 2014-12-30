<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="javascript:void(0)">Contact</a></li>
	<li class="sau-item"><s:a action="send" namespace="/contact"><span class="glyphicon glyphicon-send"></span> &nbsp;Send Problem</s:a></li>
	<li class="sau-item"><s:a action="receive" namespace="/contact"><span class="glyphicon glyphicon-envelope"></span> &nbsp;Receive Message</s:a></li>
</ul>