<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="javascript:void(0)">Profile Management</a></li>
	<li class="sau-item"><s:a action="personalData" namespace="/setting"><span class="glyphicon glyphicon-floppy-save"></span> &nbsp;Personal Data</s:a></li>
	<li class="sau-item"><s:a action="changePassword" namespace="/setting"><span class="glyphicon glyphicon-save"></span> &nbsp;Change Password</s:a></li>
	<li class="sau-item"><s:a action="template" namespace="/setting"><span class="glyphicon glyphicon-tasks"></span> &nbsp;Profile Template</s:a></li>
	<li class="active"><a href="javascript:void(0)">Photo Management</a></li>
	<li class="sau-item"><s:a action="photoManagement" namespace="/setting"><span class="glyphicon glyphicon-camera"></span> &nbsp;Photo Management</s:a></li>
	<li class="sau-item"><s:a action="profilePhotoStep1" namespace="/setting"><span class="glyphicon glyphicon-list-alt"></span> &nbsp;Profile Photo</s:a></li>
</ul>