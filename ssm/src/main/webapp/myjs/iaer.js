$(document).ready(function(){
	$(".tcdPageCode:eq(0)").createPage({
	    pageCount:$("#pagecount").val(),
	    current:1,
	    backFn:function(p){
	    	$.ajax({
	    		type : 'POST',
	    		url : 'signed/nextiaer.do',
	    		dataType :'json',
	    		async:false, //这是重要的一步，防止重复提交的
	    		data : { "pagenum" : p },
	    		success : function(list) {
	    			if(list!=null || list.length>0){
	    				var htmlcontext = "";
	    				for(var obj in list){
	    					
	    					if(list[obj].type == "收入"){
	    						htmlcontext +="<tr class='success'><td>"+list[obj].time+"</td>";
		    					htmlcontext +="<td>"+list[obj].remark+"</td>";
	    						htmlcontext +="<td>"+list[obj].amount+"</td><td></td>";
	    						htmlcontext +="<td>"+list[obj].handler+"</td>";
		    					htmlcontext +="<td>"+list[obj].sid+"</td></tr>";
	    						
	    					}else if(list[obj].type == "支出"){
	    						htmlcontext +="<tr class='danger'><td>"+list[obj].time+"</td>";
		    					htmlcontext +="<td>"+list[obj].remark+"</td>";
	    						htmlcontext +="<td></td><td>"+list[obj].amount+"</td>";
	    						htmlcontext +="<td>"+list[obj].handler+"</td>";
		    					htmlcontext +="<td>"+list[obj].sid+"</td></tr>";
	    					}
	    					
	    					
	    				}
					}
	    			$("#iaerinfotable").find("tbody").html(htmlcontext);
	    		}
	    	});
	    }
	});
})