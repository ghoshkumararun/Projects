<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<style type="text/css">
	table, th, td {
		text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/contact/common/contact-sidebar.jsp"%>
			</div>
			
			<form class="form-horizontal">
				<div class="form-group">
					<div class="col-md-8">
						<div class="form-horizontal">
							<p class="text-muted" style="font-size: 24px; font-weight: bold; margin-bottom: 20px;">Contact <i class="glyphicon glyphicon-send text-muted"></i></p>
							<div class="form-group">
								<div class="col-md-4">
									<select id="status" class="form-control">
										<option value="enabled" selected>Enabled</option>
										<option value="disabled">Disabled</option>
									</select>
								</div>
							</div>
							<hr>
							<table class="table table-hover">
								<thead style="background-color: #ddd;">
									<tr>
										<th>No.</th>
										<th>Contact detail</th>
										<th>Time</th>
										<th>Answer</th>
									</tr>
								</thead>
								<tbody class="table-receive">
									<c:forEach var="contact" items="${contacts}" varStatus="myStatus">
										<tr>
											<td style="width: 10%">${myStatus.index + 1}</td>
											<td style="width: 50%; text-align: left;">${contact.contactDetail}</td>
											<td style="width: 30%;"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"  value="${contact.contactCreate}" /></td>
											<td style="width: 10%;"><a href="answerProblem?contactId=${contact.contactId}"><span class="glyphicon glyphicon-eye-open"></span></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).on('change', '#status', function() {
			// alert($(this).val());
			$.getJSON("<s:url action='loadContactAdmin' namespace='/json' />", {status: $(this).val()}, function(data) {
				$('.table-receive').html('');
				// console.log(data);
				// check null
				if ($.isEmptyObject(data)) {
					$('.table-receive').html('<tr><td colspan="6"><h4 class="text-muted">ไม่พบข้อมูล</h4></td></tr>');
				}
				$.each(data, function(idx, obj) {
					$('.table-receive').append(
						'<tr>' +
							'<td style="width: 10%">' + (idx + 1) + '</td>' +
							'<td style="width: 50%; text-align: left;">' + obj.contactDetail + '</td>' +
							'<td style="width: 30%;">' + obj.contactCreateStr  + '</td>' +
							'<td style="width: 10%;">' + '<a href="answerQuestion?contactId=' + obj.contactId + '"><span class="glyphicon glyphicon-eye-open"></span></a>' + '</td>' + 
						'</tr>'
					);
				});
			});
		});
	</script>
</body>
</html>