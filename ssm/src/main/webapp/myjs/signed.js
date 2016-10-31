$(document).ready(function(){
	$("#mysign").createPage({
	    pageCount:$("#pagecount").val(),
	    current:1,
	    backFn:function(p){
	    	$.ajax({
	    		type : 'POST',
	    		url : 'signed/nextsignedinfo.do',
	    		dataType :'json',
	    		async:false, //这是重要的一步，防止重复提交的
	    		data : { "pagenum" : p },
	    		success : function(data) {
	    			if(data!=null || data.length>0){
	    				var htmlcontext = "";
	    				for(var obj in data){
	    					var count  = data[obj].studyfee+data[obj].studyfee;
	    					if(data[obj].condition=="已完成"){
	    						htmlcontext +="<tr class='success'>";
	    					}else if(data[obj].condition=="未收款"){
	    						htmlcontext +="<tr class='warning'>";
	    					}else if(data[obj].condition=="已收款"){
	    						htmlcontext +="<tr class='info'>";
	    					}
	    					htmlcontext +="<td>&nbsp;&nbsp;<input type='checkbox' value='"+data[obj].sid+"' name='complete_checkbox'></td>"
	    					htmlcontext +="<td>"+data[obj].sid+"</td>"
	    					htmlcontext +="<td>"+data[obj].stime+"</td>"
	    					htmlcontext +="<td>"+data[obj].sbusinesstype+"</td>"
	    					htmlcontext +="<td>"+data[obj].sale+"</td>"
	    					htmlcontext +="<td>"+data[obj].dept+"</td>"
	    					htmlcontext +="<td>"+data[obj].scustomername+"</td>"
	    					htmlcontext +="<td>"+data[obj].studyfee+"</td>"
	    					htmlcontext +="<td>"+data[obj].spacefee+"</td>"
	    					htmlcontext +="<td>"+data[obj].backfee+"</td>"
	    					htmlcontext +="<td>"+data[obj].depositfee+"</td>"
	    					htmlcontext +="<td>"+data[obj].depositfee+"</td>"
	    					htmlcontext +="<td>"+count+"</td>"
	    					htmlcontext +="<td>"+data[obj].condition+"</td></tr>"
	    				}
	    			}
	    			
	    			$("#customerinfotable").find("tbody").html(htmlcontext);
	    		}
	    		
	    	});
	    }
	});
	$("#ourmanament").createPage({
		pageCount:$("#ourmanagmentpages").val(),
		current:1,
		backFn:function(p){
			$.ajax({
				type : 'POST',
				url : 'signed/nextsignedinfo.do',
				dataType :'json',
				async:false, //这是重要的一步，防止重复提交的
				data : { "pagenum" : p },
				success : function(data) {
					if(data!=null || data.length>0){
						var htmlcontext = "";
						for(var obj in data){
							var count  = data[obj].studyfee+data[obj].studyfee;
							htmlcontext +="<td><input type='checkbox' value='"+data[obj].sid+"' name='complete_checkbox'></td>"
							htmlcontext +="<td>"+data[obj].sid+"</td>"
							htmlcontext +="<td>"+data[obj].stime+"</td>"
							htmlcontext +="<td>"+data[obj].sbusinesstype+"</td>"
							htmlcontext +="<td>"+data[obj].sale+"</td>"
							htmlcontext +="<td>"+data[obj].dept+"</td>"
							htmlcontext +="<td>"+data[obj].scustomername+"</td>"
							htmlcontext +="<td>"+data[obj].studyfee+"</td>"
							htmlcontext +="<td>"+data[obj].spacefee+"</td>"
							htmlcontext +="<td>"+data[obj].backfee+"</td>"
							htmlcontext +="<td>"+data[obj].depositfee+"</td>"
							htmlcontext +="<td>"+count+"</td>"
							htmlcontext +="<td>"+data[obj].sarea+"</td>"
							htmlcontext +="<td>"+data[obj].condition+"</td></tr>"
						}
					}
					$("#customerinfotable").find("tbody").html(htmlcontext);
				}
			});
		}
	});
	$("#allsign").createPage({
	    pageCount:$("#allsignpages").val(),
	    current:1,
	    backFn:function(p){
	    	$.ajax({
    		type : 'POST',
    		url : 'signed/nextallsigninfo.do',
    		dataType :'json',
    		async:false, //这是重要的一步，防止重复提交的
    		data : { "pagenum" : p },
    		success : function(data) {
    			if(data!=null || data.length>0){
    				var htmlcontext = "";
    				for(var obj in data){
    					var count  = data[obj].studyfee+data[obj].studyfee;
    					htmlcontext +="<tr><td><input type='checkbox' value='"+data[obj].sid+"' name='complete_checkbox'></td>"
    					htmlcontext +="<td>"+data[obj].sid+"</td>"
    					htmlcontext +="<td>"+data[obj].stime+"</td>"
    					htmlcontext +="<td>"+data[obj].sbusinesstype+"</td>"
    					htmlcontext +="<td>"+data[obj].sale+"</td>"
    					htmlcontext +="<td>"+data[obj].dept+"</td>"
    					htmlcontext +="<td>"+data[obj].scustomername+"</td>"
    					htmlcontext +="<td>"+data[obj].studyfee+"</td>"
    					htmlcontext +="<td>"+data[obj].spacefee+"</td>"
    					htmlcontext +="<td>"+data[obj].backfee+"</td>"
    					htmlcontext +="<td>"+data[obj].depositfee+"</td>"
    					htmlcontext +="<td>"+count+"</td>"
    					htmlcontext +="<td>"+data[obj].sarea+"</td>"
    					htmlcontext +="</tr>"
    				}
    			}
    			$("#allsigninfotable").find("tbody").html(htmlcontext);
    		}
    	});
	    }
    });
});

function addsigned(){
	$("#sale").val(username());
	$("#stime").val(nowDate());
}


function updatacustomerinfo(){
	
}

function delectsigninfo(){
	var cuscheckbox = $("input[name='signed_checkbox']:checkbox:checked");
	if(cuscheckbox.length>0){
		if (!confirm("确定要删除这"+cuscheckbox.length+"条签单信息吗，只允许删除未收款的签单")) {
			return false;
		}
		var checkboxarray ="";
		cuscheckbox.each(function(){ //由于复选框一般选中的是多个,所以可以循环输出 
			checkboxarray += $(this).val()+",";
		});
		window.location.href="signed/delectmultiple.do?str="+checkboxarray;
	}
}
function agree(){
	var cuscheckbox = $("input[name='agree_checkbox']:checkbox:checked");
	if(cuscheckbox.length>0){
		if (!confirm("确定要同意这"+cuscheckbox.length+"条申请信息吗")) {
			return false;
		}
		var checkboxarray ="";
		cuscheckbox.each(function(){ //由于复选框一般选中的是多个,所以可以循环输出 
			checkboxarray += $(this).val()+",";
		});
		
		window.location.href="signed/delectmultiple.do?str="+checkboxarray;
	}
}

function agree(){
	var cuscheckbox = $("input[name='agree_checkbox']:checkbox:checked");
	if(cuscheckbox.length>0){
		if (!confirm("确定要同意这"+cuscheckbox.length+"条申请信息吗")) {
			return false;
		}
		var checkboxarray ="";
		cuscheckbox.each(function(){ //由于复选框一般选中的是多个,所以可以循环输出 
			checkboxarray += $(this).val()+",";
		});
		window.location.href="signed/updateByStateid.do?str="+checkboxarray;
	}
}
