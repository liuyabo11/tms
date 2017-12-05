<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="security" uri="http://www.springsecurity.org/jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加新闻</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/layer/theme/default/layer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/apply.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/color.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
     <%--Layer弹层--%>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/utf8-jsp/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/utf8-jsp/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/utf8-jsp/lang/zh-cn/zh-cn.js"></script>


	<script type="text/javascript">
		$(function () {
            $("#column").click(function () {
                layer.open({
                    type: 2,//风格
                    title: '栏目选择',
                    shadeClose: true,
                    shade: 0.6, //透明度
                    area: ['380px', '90%'],
                    content: '${pageContext.request.contextPath}/page/column/columntree.jsp', //iframe的url
                    scrollbar: true
                });
            });
        });

        /**
         * 图片预览
         * @param e
         */
        function changImg(e) {
            for (var i = 0; i < e.target.files.length; i++) {
                var file = e.target.files.item(i);
                if (!(/^image\/.*$/i.test(file.type))) {
                    continue; //不是图片 就跳出这一次循环
                }
                //实例化FileReader API
                var freader = new FileReader();
                freader.readAsDataURL(file);
                freader.onload = function (e) {
                    $("#myImg").attr("src", e.target.result);
                };
            }
        }


	</script>
</head>
 <div id="container" style="margin-top: 10px;">
	 <div class="server">
	 <h3 class="apply_title">基本信息</h3>
		 <form method="post" id="form1" action="${pageContext.request.contextPath}/news/addNews" enctype="multipart/form-data">
	 <table class="table" width="100%">

		 <tr>
			 <td class="first">所属栏目：</td>
			 <td>
				 <input class="easyui-textbox"  id="column" style="cursor: progress" placeHolder="点我查询" readonly="readonly"/>
				 <input type="hidden"   value="" id="columntype" name="columntype"/>
			 </td>
			 <td class="first">外部连接：</td>
			 <td class="input"><input class="easyui-textbox" name="outerlink" value=" " /></td>
		 </tr>

		 <tr>
			 <td class="first">文章标题<span class="xing">*</span>：</td>
			 <td class="input" colspan="3"><input class="easyui-textbox" type="text" name="title" value=" " /></td>
		 </tr>

		 <tr>
			 <td class="first">标题颜色<span class="xing">*</span>：</td>
			 <td class="input">
				 <select class="easyui-combobox" name="titlecolor">
					 <option value="color" >请选择</option>
					 <option  class="apply_civil" value="red">红色</option>
					 <option  class="apply_abroad" value="blue">蓝色</option>
				 </select>
			 </td>
			 <td class="first">权重/排序<span class="xing">*</span>：</td>
			 <td class="input"><input class="easyui-textbox" name="weight" value=" " /></td>

		 </tr>
		 <tr>
			 <td class="first">文章图片<span class="xing">*</span>：</td>
			 <td class="input">
				 <a href="javascript:;" class="file">
				 <input type="file"  id="file1" name="upload" accept="image/*" onchange="changImg(event)" style="width:50%;height: 30px">
			    </a><img id="myImg"  style="width:50px;height: 50px"/>
			 </td>
			 <td class="first">关键词<span class="xing">*</span>：</td>
			 <td  class="input"><input class="easyui-textbox" type="text" name="primaryword" value=" " /></td>

		 </tr>
		 <tr>
			 <td colspan="4" class="head">摘要</td>
		 </tr>
		 <tr>
			 <td  class="easyui-textbox"style="height:60px;" colspan="4">
				 <textarea id="summaryeditor" name="summary" rows="4" cols="100" style="resize:none;width:900px;"></textarea>
				 <script type="text/javascript">
                     UE.getEditor('summaryeditor');
				 </script>
			 </td>
		 </tr>
		 <tr>
			 <td colspan="4" class="head">正文</td>
		 </tr>
		 <tr>
			 <td class="easyui-textbox" style="height:60px;" colspan="4">
				 <textarea id="editor" name="content"  style="resize:none;width:900px;"></textarea>
					 <script type="text/javascript">
						 UE.getEditor('editor');
					 </script>
			 </td>
		 </tr>
		 <tr>
			 <td colspan="4" style="height:40px;">
				 <a href="javascript:$('#form1').submit();" class="easyui-linkbutton">暂存草稿</a>
				 <a href="javascript:;" class="easyui-linkbutton">发布</a>
				 <a href="javascript:;" class="easyui-linkbutton">关闭</a>
			 </td>
		 </tr>
	 </table>
		 </form>
 </div>
 </div>
</body>
</html>