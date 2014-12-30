<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="javascript:void(0)">Tutorial Management</a></li>
	<li class="sau-item"><s:a action="all" namespace="/tutorial"><span class="glyphicon glyphicon-book"></span> &nbsp;Tutorials Library</s:a></li>
	<li class="sau-item"><s:a action="createTutorial" namespace="/tutorial"><span class="glyphicon glyphicon-floppy-saved"></span>	&nbsp;Create Tutorial</s:a></li>
	<li class="active"><a href="javascript:void(0)">Group Management</a></li>
	<li class="sau-item"><s:a action="createGroup" namespace="/group"><span class="glyphicon glyphicon-floppy-save"></span>	&nbsp;Create Group</s:a></li>
	<li class="sau-item"><s:a action="changeGroup" namespace="/group"><span class="glyphicon glyphicon-wrench"></span>	&nbsp;Change Group</s:a></li>
	<li class="sau-item"><s:a action="deleteGroup" namespace="/group"><span class="glyphicon glyphicon-trash"></span>	&nbsp;Delete Group</s:a></li>
	<li class="active"><a href="../main">Back ...</a></li>
</ul>