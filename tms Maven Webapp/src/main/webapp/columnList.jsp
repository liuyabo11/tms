<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
    
<%@taglib prefix="rbac" uri="http://www.springsecurity.org/jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>栏目管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    window.onload=function () {
    	openListActionDialog();

    };
    
    //打开一个新的窗口
    function openTab(text,url,iconCls){
    	alert(text);
    	alert(url);
    	alert(iconCls);
        if(parent.$("#tabs").tabs("exists",text)){
            parent.$("#parent.tabs").tabs("select",text);
        }else{
            var content="<iframe frameborder=0 scrolling='scroll' style='width:100%;height:100%' src='${pageContext.request.contextPath}"+url+"'></iframe>";
            parent.$("#tabs").tabs("add",{
                title:text,
                iconCls:iconCls,
                closable:true,
                content:content
            });
        }
    }
    
    //显示二级节点下的内容
    function showcontent(checkNode){
    	alert(checkNode.id);
        $('#column').datagrid({
            title:checkNode.text,//布局的标题名称
            iconCls:'icon-save', //图标样式
            width:'100%', //宽度
            //height:450, //高度
            nowrap: true,
            striped: true,
            url:'${pageContext.request.contextPath}/news/findNewsByColumn?columntype='+checkNode.id,
            idField:'information_code',
            pageSize:2,
            pageList: [2,5,10],
            frozenColumns:[[
                {field:'ck',checkbox:true},
                {title:'编号',field:'information_code',width:80,sortable:true},
                {title:'标题',field:'title',width:80,sortable:true}

            ]],
            columns:[[//Column是一个数组对象，它的每个元素也是一个数组。元素数组的元素是一个配置对象，它定义了每个列的字段。
                      //formatter：处理json对象数据，使用了一个自定义的匿名函数，入参了一个value，代表的是不能直接触碰对象属性json对象
                      {field:'column',title:'栏目',width:200,formatter: function(value){return value.columnname;}},
                      {field:'user',title:'创建者',width:200,formatter: function(value,row,index){return value.username;}},
                      {field:'update_date',title:'更新时间',width:200,formatter: function(value,row,index){
                                    return new Date(value).Format("yyyy-MM-dd hh:mm:ss");}},
                      {field:'status',title:'状态',width:200},
                      {field:'action',title:'操作',width:200,formatter: formatAction  	}
            ]],
            pagination:true, //研究：rows: 每页显示的数据量 page:第几页
            rownumbers:true,
            singleSelect:false,
            fitColumns:false,
            toolbar:[{
                text:'添加',
                iconCls:'icon-add',
                handler:function(){
                    //open1();
                   openAddNewsDialog();
                    //openNewsAddDialog();
                }
            },{
         	   id: 'btnDelete',
                text:'批量删除',
                iconCls:'icon-remove',
                disabled:false,
                handler:function(){
                    doDelete();
                }
            },{
                text:'修改',
                iconCls:'icon-edit',
                disabled:false,
                handler:function(){
                    openUserModifyDialog();
                }
            }]
        });
    }
    
  //打开添加新闻的页面
  function openAddNewsDialog(){
      //1.获取当前选中行对象
      //var row=$('#column').datagrid('getSelected');
      openTab("添加新闻","/articleForm.jsp","icon-user");
  }
    
    //拼接了一个超链接
    function formatAction(){
        return "<a href='javascript:openListActionDialog()'>修改</a>";
    }

    //打开修改新闻的页面
    function openListActionDialog(){
        //1.获取当前选中行对象
        var row=$('#column').datagrid('getSelected');
        openTab("修改新闻","${pageContext.request.contextPath}/news/goNewsPage?nid="+row['information_code'],"icon-user");
    }
    
    
    //format
    Date.prototype.Format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
    
    //获取所有的栏目 拼接到tree树上
    function openListActionDialog() {
    	 //拼接tree树
        $("#tt").tree({
            url: '${pageContext.request.contextPath}/col/getAllColumnsJson',
            animate:true,//是否有动画
            cascadeCheck:false,
            //checkbox:true,内容前面的小方框
            loadFilter ://过滤数据
                function(data) { //data;从Server端响应回来的数据
                	  //将后台返回的数据  修改成easyui需要的格式  id  text
                    change(data);
                    //图标的设定
                    $.each(data,function (i,v){
                        v.iconCls="icon-folder";
                    });
                    return data;
                },
             //给二级节点  设置点击事件
             onClick:function(checkNode){//checkNode选中的节点
                 showcontent(checkNode);
             }  
        });
    	 /* tree end */
    }
    
   
    
  //读懂：工具来用
    function change(data){
   	//当前节点没有子节点的时候 就把属性名给修改成text
       if(!data.length){
       	//因为easyui中 节点的值必须为text 所有要将name转换成text
           data.text=data.columnname;
       	   data.id=data.syscode;
           if(data.children){
               change(data.children);
           }
       }else{
           $.each(data,function (i,v){
               change(v);
           });
       }
    }
    
    
</script>
</head>


<body style="margin: 1px">

<div class="easyui-layout" style="width:1127px;height:600px;">
    <div region="west" split="true" title="内容发布" style="width:270px;">
        <ul id="tt">
        
        </ul>
    </div>
    <div id="content" region="center" title="栏目列表" style="padding:5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a href="${pageContext.request.contextPath}/articleForm.jsp" class="easyui-linkbutton" iconCls="icon-add">新增</a>
        <table id="column">
        
        </table>
    </div>
</div>

</body>
</html>