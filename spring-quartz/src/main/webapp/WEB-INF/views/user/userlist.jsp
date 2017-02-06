<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

 <script type="text/javascript">
        $(function(){
        	alert("11");
        	$('#usergrid').datagrid({    
                url:'${path}/user/queryUser',    
                columns:[[    
                    {field:'username',title:'用户名',width:100},    
                    {field:'name',title:'姓名',width:100},    
                    {field:'sex',title:'性别',width:100,align:'right'}    
                ]]    
            }); 
        });
    </script>
    
    <div id="usergrid" >
    	
    </div>   

