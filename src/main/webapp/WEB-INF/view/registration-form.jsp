<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/custom-bs.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/jquery.fancybox.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/bootstrap-select.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/fonts/icomoon/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/fonts/line-icons/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/animate.min.css">

<!-- MAIN CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/assets/css/style.css">
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/bootstrap.bundle.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/isotope.pkgd.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/stickyfill.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/jquery.fancybox.min.js"></script>


<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/jquery.waypoints.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/jquery.animateNumber.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/owl.carousel.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/bootstrap-select.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/custom.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>

<body>

	<div>

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/register/processRegistrationForm"
						modelAttribute="crmUser" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>

									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}</div>

									</c:if>

								</div>
							</div>
						</div>

						<!-- User name -->
						<form:errors path="userName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="userName" placeholder="username (*)"
								class="form-control" />
						</div>

						<!-- Password -->
						<form:errors path="password" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<form:password path="password" placeholder="password (*)"
								class="form-control" />
						</div>

						<!-- Confirm Password -->
						<form:errors path="matchingPassword" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<form:password path="matchingPassword"
								placeholder="confirm password (*)" class="form-control" />
						</div>


						<!-- First name -->
						<form:errors path="firstName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="firstName" placeholder="first name (*)"
								class="form-control" />
						</div>

						<!-- Last name -->
						<form:errors path="lastName" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="lastName" placeholder="last name (*)"
								class="form-control" />
						</div>

						<!-- Email -->
						<form:errors path="email" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="email" placeholder="email (*)"
								class="form-control" />
						</div>

						<!-- Phone number -->
						<form:errors path="phoneNumber" cssClass="error" />
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<form:input path="phoneNumber" placeholder="phone number (*)"
								class="form-control" />
						</div>

						<!-- Role dropdown -->
						<div style="margin-bottom: 25px" class="input-group" id="role-input">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <select
								class="form-control" id="roleId1" name="roleId">
								<!-- Populating role option -->
								<option value="1" selected="selected" label="Job finder" />
								<option value="2" label="Recruiter" />
							</select>
						</div>

						<!-- Company dropdown -->
						<div style="margin-bottom: 25px; display: none;" class="input-group" id="company-input">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <select
								class="form-control" id="companyId1" name="companyId">
								<!-- Populating company option -->
								<c:forEach var="company" items="${companies}">
									<option value="${company.id }" label="${company.name }" />
								</c:forEach>
							</select>
						</div>
						<!-- new company input -->
						<div style="margin-bottom: 25px;display:none" class="input-group" id="new-company-input">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<input type="text" name="companyName" placeholder="new company name(*)"
								class="form-control" disabled="disabled" id="new-company-input-text"/>
						</div>
						
						<!-- show/hide company button -->
						<div style="margin-top: 10px;display: none;" class="form-group" id="new-company-btn" >
							<div class="col-sm-6 controls">
								<button type="button" class="btn btn-primary">Add new company</button>
							</div>
						</div>
						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>



					</form:form>

				</div>

			</div>

		</div>

	</div>
<script
	src="${pageContext.request.contextPath}/assets/user/assets/js/scripts.js"></script>
</body>
</html>