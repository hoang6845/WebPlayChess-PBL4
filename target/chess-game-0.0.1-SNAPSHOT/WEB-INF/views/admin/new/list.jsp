<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="UserURL" value="/admin-user"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>

<body>
	<div class="main-content">
		<form action='<c:url value ='/admin-user'></c:url>' id="formSubmit"
			method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href='<c:url value="/admin-home"></c:url>'>Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="widget-box table-filter">
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm bài viết'
										href='<c:url value="/admin-user?type=edit"/>'> <span>
											<i class="fa fa-plus-circle bigger-110 purple"></i>
									</span>
									</a>
									<button id="btnDelete"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa người dùng'>
										<i class="fa fa-trash-o bigger-110 pink"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<table class="table">
								<thead>
									<tr>
										<th><input type="checkbox" id="checkAll"></th>
										<th scope="col">STT</th>
										<th scope="col">Tên</th>
										<th scope="col">username</th>
										<th scope="col">password</th>
										<th scope="col">Thao tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${model.listModel}"
										varStatus="status">
										<tr>
											<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
											<th scope="row">${status.count+(model.page-1)*model.itemsInPage}</th>
											<td>${item.getFullname()}</td>
											<td>${item.username}</td>
											<td>${item.password}</td>
											<td>
											<c:url var="editURL" value = "/admin-user">
												<c:param name="type" value="edit"></c:param>
												<c:param name="id" value="${item.id}"></c:param>
											</c:url>
											<a class="btn btn-sm btn-primary btn-edit"
												data-toggle="tooltip" title="Cập nhật bài viết"
												href='${editURL}'><i class="fa fa-pencil-square-o"
													aria-hidden="true"></i> </a>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<nav aria-label="Page navigation">
								<ul class="pagination" id="pagination"></ul>
								<input type="hidden" name="page" id="page"> <input
									type="hidden" name="itemsInPage" id="itemsInPage"> <input
									type="hidden" name="sortName" id="sortName"> <input
									type="hidden" name="sortBy" id="sortBy"> <input
									type="hidden" name="type" id="type" value="list">
							</nav>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		var totalPage = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = ${model.itemsInPage};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPage,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					console.info(page + ' (from options)');
					if (currentPage != page) {
						$('#itemsInPage').val(limit);
						$('#page').val(page);
						$('#sortName').val('id');
						$('#sortBy').val('desc');
						$('#formSubmit').submit();
					}
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
		$("#btnDelete").click(function() {
			 console.log("Button clicked");
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
	            return $(this).val();
	        }).get();
			data['ids'] = ids;
			deleteNew(data);
		});
		 function deleteNew(data) {
		        $.ajax({
		            url: '${APIurl}',
		            type: 'DELETE',
		            contentType: 'application/json',
		            data: JSON.stringify(data),
		            success: function (result) {
		                window.location.href = "${UserURL}?type=list&page=1&itemsInPage=4&sortName=id&sortBy=desc";
		            },
		            error: function (error) {
		            	window.location.href = "${UserURL}?type=list&page=1&itemsInPage=4&sortName=id&sortBy=desc";

		            }
		        });
		    }
	</script>

</body>
</html>