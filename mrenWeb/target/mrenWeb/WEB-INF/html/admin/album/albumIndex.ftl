<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<title></title>
<!-- <base href="/mrenWeb/"> -->
<link rel="stylesheet" type="text/css"
	href="${base}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${rc.contextPath}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="/mrenWeb/css/easyui/demo.css">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript" src="/mrenWeb/js/jquery.min.js"></script>
<script type="text/javascript" src="/mrenWeb/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', 'New User');
		$('#fm').form('clear');
		url = 'save_user.php';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = 'update_user.php?id=' + row.id;
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : '/mrenWeb/photos/createAlbum.do',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.code == 200) {
					alert("相册创建成功");
					// $('#dlg').dialog('close');  close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				} else {
					alert("相册创建失败");
					$.messager.show({
						title : 'Error',
						msg : result.message
					});
				}
			}
		});
	}
	function removeUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to remove this user?', function(r) {
						if (r) {
							$.post('remove_user.php', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.msg
									});
								}
							}, 'json');
						}
					});
		}
	}
</script>
</head>
<body>
	<h2>${base}${rc.contextPath}</h2>
	<div id="p" class="easyui-panel" title="Basic Panel"
		style="width: 700px; height: 240px; padding: 10px;">
		<div class="ftitle">创建相册</div>
		<form id="fm" method="get" novalidate>
			<div class="fitem">
				<label>图片路径</label> <input name="imageUrl"
					class="easyui-validatebox" required="true" style="width: 400px">
			</div>
			<div class="fitem">
				<label>标题:</label> <input name="title" class="easyui-validatebox"
					required="true" style="width: 400px">
			</div>
			<div class="fitem">
				<label>来源:</label> <input name="source" class="easyui-validatebox"
					required="true" style="width: 400px">
			</div>
		</form>
		<div id="ft" style="padding: 5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					onclick="saveUser()">保存</a> 
		</div>
	</div>
	
	<p></p>
	<table id="dg" title="相册列表" class="easyui-datagrid"
		style="width: 700px; height: 250px" url="/mrenWeb/photos/getLatestPhotoAlbum.do"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="title" width="50">标题</th>
				<th field="uuid" width="50">uuid</th>
				<th field="imageUrl" width="50">图片路径</th>
				<th field="source" width="50">来源</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>
	</div>
	
	
	
	<!-- 弹窗 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>First Name:</label>
				<input name="" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Last Name:</label>
				<input name="lastname" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>Phone:</label>
				<input name="phone">
			</div>
			<div class="fitem">
				<label>Email:</label>
				<input name="email" class="easyui-validatebox" validType="email">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	

</body>
</html>