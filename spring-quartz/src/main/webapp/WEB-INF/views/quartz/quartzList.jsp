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
 	 	$(function(){
 	 		initQuartz();
 	 	});
 	 	
 	 	function initQuartz(){
 	 		$("#quartzGrid").datagrid({
 	 			url:'${path}/quartz/queryQuartz',
 	 			title:'定时任务列表',
 	 		  /*   pagination: true, */
 	 		    singleSelect:true,
 	 		    checkOnSelect:true,
 	 		    pageSize:15,
 	 		    remoteSort:false,
 	 		    rownumbers:true,
 	 		    striped:true,
 	 		    width:'auto',
 	 		    idField:'jobId',
 	 		    columns:[[
 	 		        {field:'jobId',title:'ID',hidden:true},
 	 		        {field:'jobName',title:'任务名称',width:"10%",sortable:true},
 	 			    {field:'jobGroup',title:'任务组',width:"10%",sortable:true},
 	 			    {field:'cronExpression',title:'任务运行时间',width:"20%",sortable:true},
 	 		        {field:'jobStatus',title:'任务状态',width:"10%",align:'center',sortable:true,
 	 		            formatter:function(value,row,index){
 	 		                if (value  == 1){
 	 		                    return '<samp style="color: green;">启用</samp>';
 	 		                } else if(value  == 0) {
 	 		                    return '<samp style="color: red;">禁用</samp>';
 	 		                }else if(value  == 2) {
	 		                    return '<samp style="color: red;">删除</samp>';
	 		                }
 	 		            }
 	 		        },
 	 		        {field:'desc',title:'任务描述',width:"20%",sortable:true},
 	 		    ]],
 	 		    onDblClickRow:function(rowIndex, rowData){
 	 		    	//双击调用查看详情
 	 		    	//showUserInfo(rowData);
 	 		    }
 	 		});
 	 		
 	 		/* //设置分页控件 
 	 	    var p = $('#quartzGrid').datagrid('getPager'); 
 	 	    $(p).pagination({ 
 	 	        pageSize: 15,//每页显示的记录条数，默认为15
 	 	        pageList: [5,10,15,20,50],//可以设置每页记录条数的列表 
 	 	        beforePageText: '第',//页数文本框前显示的汉字 
 	 	        afterPageText: '页    共 {pages} 页', 
 	 	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
 	 	    });  */
	 	}
    </script>
</head>

<body>   
     <div id="quartzGrid" > </div>   
</body> 
	
</body>
</html>
