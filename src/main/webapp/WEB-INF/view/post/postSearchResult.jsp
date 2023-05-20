<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Danh sách bài viết</title>

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
	<nav class="bg-light p-2">
		<a href="${pageContext.request.contextPath}/homepage" role="button"
			class="btn btn-primary">Về trang chủ</a>
		<h3 class="text-center">Kết quả tìm kiếm</h3>
	</nav>
	<c:if test="${not empty message}">
		<div class="alert alert-primary" role="alert">${message}</div>
	</c:if>
	<section>
	<div class="container d-flex flex-column align-items-center">

		
				<form action="${pageContext.request.contextPath}/search">
					
					<div class="input-group mb-5" style="width: 800px">
						<input type="text" name="searchQuery" class="form-control"
							placeholder="Tìm kiếm">
						<button class="btn btn-primary" type="submit">Tìm
							kiếm</button>
						<input style="margin-left:40px;" type="radio" name="type" value="0" <c:if test="${type==0}">checked="checked"</c:if>>		
						<label>Công việc</label>		
						<input style="margin-left:40px;" type="radio" name="type" value="1" <c:if test="${type==1}">checked="checked"</c:if>>	
						<label >Công ty</label>	
						<input style="margin-left:40px;" type="radio" name="type" value="2"  <c:if test="${type==2}">checked="checked"</c:if>>	
						<label>Địa điểm</label>	
						
					</div>
				</form>
		
		
	</div>
	</section>
	
	
	<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="close-btn">&times;</div>
    
  </div>

</div>

	<main>
		<div
			class="container d-flex align-items-center justify-content-center flex-column">

			<c:forEach items="${posts}" var="post">
				<div class="row p-2 align-items-center item-border"
					style="width: 900px">

					<div class="col-lg">
						<p>${post.jobType.name}</p>
						<h4>${post.title}</h4>
						<span class="fas fa-building"></span><span>${post.company.name}</span><span
							class="icon-room"></span><span>${post.company.address}</span>
					</div>
					<div class="col-sm">
						
							<button class="btn btn-primary float-right mx-2" id="apply-modal-btn">Apply Job</button>
						
					</div>
				</div>
			</c:forEach>
			<ul class="pagination">
				<c:forEach var="j" begin="1" step="1" end="${maxPage}">
					<!-- contruct a pagin link -->
					<c:url var="pageLink" value="/search">
						<c:param name="page" value="${j}"></c:param>
						<c:param name="searchQuery" value="${searchQuery}"></c:param>
						<c:param name="type" value="${type}"></c:param>
					</c:url>
					<!-- display page link -->
					<c:if test="${page == j}">
						<li><a href="${pageLink}" class="page-link disabled"
							style="background-color: beige">${j}</a></li>
					</c:if>
					<c:if test="${page != j}">
						<li><a href="${pageLink}" class="page-link">${j}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>

	</main>
	<script> 
	// Get the modal
	var modal = document.getElementById("myModal");

	// Get the button that opens the modal
	var btn = document.getElementById("apply-modal-btn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close-btn")[0];

	// When the user clicks on the button, open the modal
	btn.onclick = function() {
	  modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
	</script>
</body>
</html>