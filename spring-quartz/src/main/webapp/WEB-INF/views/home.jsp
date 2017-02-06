<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <%@include file="common/common.jsp" %>
    
    <script type="text/javascript">
 	 //content属性是添加整个url请求中完整的jsp页面，而href属性只引入url中的<body></body>中的内容，具体可
    	function toCenter(id,name){
    		if ($('#tab').tabs('exists', name)){
    	        $('#tab').tabs('select', name);
    	        
    	    } else{
    	    	$('#tab').tabs('add',{    
    	    	    title:name, 
    	    	    closable:true,
    	    	    href:'${path}/user/toUserList' ,
    	    	   // content:'${path}/user/toUserList'
    	    	}); 
    	    }
    	}
    </script>
</head>

<body class="easyui-layout">   
    <div data-options="region:'north'" style="height:100px;">
    	<%-- <%@include file="/top.jsp" %> --%>
    </div>   
 	 <div data-options="region:'west',title:'菜单'" style="width:200px;text-align: center;">
    	<a id="btn" href="javascript:void(0)" onclick="toCenter('1','用户管理')" style="margin: 5px;width:100%" >用户管理</a>  
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<div id="tab" class="easyui-tabs" style="width:100%;height:100%;">   
		    <div title="首页" style="padding:20px;display:none;">   
		        tab1    
		    </div>   
		</div> 
    </div>   
</body> 
	
</body>
</html>
