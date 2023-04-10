<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Trang quyên góp</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="Free-Template.co" />
<link rel="shortcut icon" href="ftco-32x32.png">


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

</head>
<body id="top">

	<div id="overlayer"></div>
	<div class="loader">
		<div class="spinner-border text-primary" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>
	<div th:if="${msg}" class="toast" data-delay="1000"
		style="position: fixed; top: 100PX; left: 40PX; z-index: 2000; width: 300px">

	</div>

	<div class="site-wrap">

		<div class="site-mobile-menu site-navbar-target">
			<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close mt-3">
					<span class="icon-close2 js-menu-toggle"></span>
				</div>
			</div>
			<div class="site-mobile-menu-body"></div>
		</div>
		<!-- .site-mobile-menu -->


		<!-- NAVBAR -->
		<header class="site-navbar mt-3">
			<div class="container-fluid">
				<div class="row align-items-center">
					<div class="site-logo col-6">
						<a href="/">Website tìm việc làm</a>
					</div>
					<!-- button login/logout -->
					<div class="col-6 login-btn">
						<c:if test="${empty user}">
							<form action="loginPage">
								<button class="btn btn-outline-success my-2 my-sm-0" type=submit>Login</button>
							</form>
						</c:if>
						<c:if test="${not empty user}">
							<form:form action="${pageContext.request.contextPath}/logout"
								method="post">
								<button class="btn btn-outline-success my-2 my-sm-0" type=submit
									value=logout>Logout</button>
							</form:form>
						</c:if>
					</div>


				</div>
			</div>
		</header>

		<!-- HOME -->
		<section class="section-hero overlay inner-page bg-image"
			style="background-image: url('${pageContext.request.contextPath}/storage/avatar/11111111111111124.PNG');"
			id="home-section">
			<div class="container">
				<div class="row">
					<div class="col-md-7">
						<h1 class="text-white font-weight-bold">Trang chủ</h1>
						<c:if test="${not empty user}">
							<h2 class="text-white font-weight-bold">xin chào
								${user.firstName}</h2>
							<h2>
								Role(s):
								<security:authentication property="principal.authorities" />
							</h2>
							<form:form action="${pageContext.request.contextPath}/profile"
								method="post">
								<button class="btn btn-primary py-2" type=submit>Profile</button>
							</form:form>
						</c:if>
					</div>
				</div>
			</div>
		</section>
		<section class="site-section">
			<!-- Các công ty -->
			<div class="container">

				<div class="row mb-5 justify-content-center">
					<div class="col-md-7 text-center">
						<h2 class="section-title mb-2">Các công ty nổi bật</h2>
					</div>
				</div>
				<ul class="job-listings mb-5">
					<c:forEach items="${companies}" var="company">
						<li style="margin-bottom: 20px"
							class="job-listing d-block d-sm-flex pb-3 pb-sm-0 align-items-center ">
							<div
								class="job-listing-about d-sm-flex custom-width w-100 justify-content-between mx-4">
								<div class="job-listing-position custom-width  mb-6 mb-sm-0"
									style="padding: 10px; width: 400px">
									<!-- construct an detail link -->
									<c:url var="detailLink" value="userDonationDetail">
										<c:param name="id" value="#"></c:param>
									</c:url>
									<a href="${detailLink }" style="position: relative;">
										<h2>${company.name}</h2>
									</a> <strong>Phone number: ${company.phoneNumber }</strong>
								</div>
								<div class="job-listing-location mb-9 mb-sm-0 custom-width w-25"
									style="padding: 10px;">
									<span class="icon-room"></span> <span>Location</span><br>
									<strong>${company.address}</strong><br>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- Các việc làm nổi bật  -->
			<div class="container">

				<div class="row mb-5 justify-content-center">
					<div class="col-md-7 text-center">
						<h2 class="section-title mb-2">Các bài đăng về việc làm nổi
							bật</h2>
					</div>
				</div>
				<ul class="job-listings mb-5">
					<c:forEach var="post" items="${posts}">
						<li style="margin-bottom: 20px"
							class="job-listing d-block d-sm-flex pb-3 pb-sm-0 align-items-center ">
							<div
								class="job-listing-about d-sm-flex custom-width w-100 justify-content-between mx-4">
								<div class="job-listing-position custom-width  mb-3 mb-sm-0"
									style="padding: 10px; width: 250px">
									<!-- construct an detail link -->
									<c:url var="detailLink" value="userDonationDetail">
										<c:param name="id" value="#"></c:param>
									</c:url>
									<a href="${detailLink }" style="position: relative;">
										<h2>${post.title }</h2>
									</a> <strong>${post.jobType.name}</strong>
								</div>
								<div class="job-listing-location mb-9 mb-sm-0 custom-width w-25"
									style="padding: 10px;">
									<span class="icon-room"></span> <span>${post.company.address }</span><br>
									<strong>${post.company.name }</strong><br>
								</div>
								<div class="job-listing-meta custom-width w-20">
									<p style="margin-top: 20px"
										class="btn btn-primary py-2 updateBtn" data-toggle="modal"
										aria-labelledby="exampleModalLabel" id="#exampleModal.id">Apply
										job</p>

								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- Các danh mục nổi bật -->
			<div class="container">

				<div class="row mb-5 justify-content-center">
					<div class="col-md-7 text-center">
						<h2 class="section-title mb-2">Các danh mục nổi bật</h2>
					</div>
				</div>
				<ul class="job-listings mb-5">
					<c:forEach var="category" items="${categories}">
						<li style="margin-bottom: 20px"
							class="job-listing d-block d-sm-flex pb-3 pb-sm-0 align-items-center ">
							<div
								class="job-listing-about d-sm-flex custom-width w-100 justify-content-between mx-4">
								<div class="job-listing-position custom-width  mb-3 mb-sm-0"
									style="padding: 10px; width: 250px">
									<!-- construct an detail link -->
									<c:url var="detailLink" value="userDonationDetail">
										<c:param name="id" value="#"></c:param>
									</c:url>
									<a href="${detailLink }" style="position: relative;">
										<h2>${category.categoryName }</h2>
									</a> <strong>${category.countPost}</strong>
								</div>
								<div class="job-listing-location mb-9 mb-sm-0 custom-width w-25"
									style="padding: 10px;">
									<span class="icon-room"></span> <span>${post.company.address }</span><br>
									<strong>${post.company.name }</strong><br>
								</div>
								<div class="job-listing-meta custom-width w-20">
									<p style="margin-top: 20px"
										class="btn btn-primary py-2 updateBtn" data-toggle="modal"
										aria-labelledby="exampleModalLabel" id="#exampleModal.id">Quyên
										góp</p>
									<p
										style="margin-top: 20px; background-color: white !important;"
										class="btn py-2">
										<span style="color: white">Apply job</span>
									</p>

								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>

	</div>
	<script
		src="${pageContext.request.contextPath}/assets/user/assets/js/JQuery3.3.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/user/assets/js/scripts.js"></script>
</body>
</html>