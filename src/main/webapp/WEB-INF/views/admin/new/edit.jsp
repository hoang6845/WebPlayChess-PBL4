<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user"/>
<c:url var ="UserURL" value="/admin-user"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin-home"></c:url>'>Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa người dùng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">
  										${messageResponse}
									</div>
						</c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Vai trò</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="roleCode" name="roleCode">
                                        <c:if test="${empty model.role}">
                                            <option value="">Chọn loại role insert</option>
                                            <c:forEach var="item" items="${ROLE}">
                                                <option value="${item.codeRole}">${item.nameRole}</option>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.role}">
                                            <option value="">Chọn loại role edit</option>
                                            <c:forEach var="item" items="${ROLE}">
                                                <option value="${item.codeRole}" <c:if test="${item.codeRole == model.role.codeRole}">selected="selected"</c:if>>
                                                        ${item.nameRole}
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên người dùng </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="fullname" name="fullname" required value="${model.fullname}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Username</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="username" name="username" value="${model.username}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mật khẩu</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="password" name="password" placeholder="${model.password}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                              <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Email</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="email" name="email" value="${profile.email}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Description</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="description" name="description" style="width: 100%;min-height: 120px">${profile.description}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật người dùng" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm người dùng" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$('#btnAddOrUpdateNew').click(function (e) {
		  e.preventDefault();
		 /*  var title = ${'#title'}.val();
		  var thumbnail = ${'#thumbnail'}.val();
		  var shortDescription = ${'#shortDescription'}.val();
		  var content = ${'#content'}.val();
		  var categoryCode = ${'#categoryCode'}.val(); */
		  var data = {};
	        var formData = $('#formSubmit').serializeArray();
	        $.each(formData, function (i, v) {
	        	   data[""+v.name+""] = v.value;
	        	   console.log(v);
	        });
	        var id = $('#id').val();
	        if (id == ""  ) {
	            addNew(data);
	        } else {
	            updateNew(data);
	        }
	})
	 function addNew(data) {
		console.log(data);
		if (data.username==''||data.password==''||data.email==''||data.fullname==''||data.roleCode=='') alert("Vui lòng điền đủ các trường bắt buộc");
		else {
			$.ajax({
	            url: '${APIurl}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            dataType: 'json',
	            success: function (result) {
	            	console.log(result);
	            	if (result.type=='success'){
	            		alert("Thêm thành công");
		            	window.location.href = "${UserURL}?type=list&page=1&itemsInPage=4&sortName=id&sortBy=desc&message=AddSuccess";
	            	}else if(result.type=='fail'){
	            		alert("username đã tồn tại");
	            	}
	            },
	            error: function (error) {
	            	window.location.href = "${UserURL}?type=list&maxPageItem=2&page=1&message=error_system";
	            	 console.log(error);
	            }
	        });
		}
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
             	console.log(result);
            	if (result.type=='success'){
            		alert("Sửa thành công");
	            	window.location.href = "${UserURL}?type=list&page=1&itemsInPage=4&sortName=id&sortBy=desc&message=AddSuccess";
            	}else if(result.type=='fail'){
            		alert("username đã tồn tại");
            	}
            },
            error: function (error) {
            	//window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            	 console.log(error);
            }
        });
    }
</script>
</body>
</html>