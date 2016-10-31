/**
	 * @author chen
	 * 提交 退返款的所有信息
	 * @param signed.jsp
	 * @return 
	 */
$(document).ready(function(){
	//生成分页按钮，并且能够查看分页后信息
	$("#myback").createPage({
	    pageCount:$("#pagecount").val(),
	    current:1,
	    backFn:function(p){
	    	$.ajax({
	    		type : 'POST',
	    		url : 'signed/Backinfo.do',
	    		dataType :'json',
	    		async:false, //这是重要的一步，防止重复提交的
	    		data : { "pagenum" : p},
	    		success : function(list) {
	    			if(list!=null || list.length>0){
	    				var htmlcontext = "";
	    				for(var obj in list){
	    					htmlcontext +="<tr><td>&nbsp;&nbsp;<input type='checkbox' value='"+list[obj].sid+"' name='Backcheckbox'></td>";
	    					htmlcontext +="<td>"+list[obj].dbid+"</td>";
	    					htmlcontext +="<td>"+list[obj].dbtime+"</td>";
	    					htmlcontext +="<td>"+list[obj].dbemp+"</td>";
	    					htmlcontext +="<td>"+list[obj].dbamount+"</td>";
	    					htmlcontext +="<td>"+list[obj].condition+"</td>";
	    					htmlcontext +="<td>"+list[obj].dbtype+"</td>";
	    					htmlcontext +="<td>"+list[obj].dbremark+"</td>";
	
	    				}
					}
	    			$("#backinfo").find("tbody").html(htmlcontext);
	    		}
	    	});
	    }
	});
	
})
//返款
function BackModel(){
	
	var signcheckbox = $("input[name^='signed_checkbox']:checkbox:checked");
	if(signcheckbox.length!=1){
		alert("请选择单条数据操作！")
		return false;
	}else{
		var d = new Date();
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		var handler =$.trim($(".navbar li:eq(0) a").text());
		handler = handler.substr(handler.indexOf('['),handler.length);
		$("input[name='time']").val(str);
		$("input[name='handler']").val(handler);
		$.ajax({
			type : 'POST',
    		url : 'signed/onesign.do',
    		dataType :'json',
    		async:false, 
    		data : { "sid" : signcheckbox.val()},
    		success : function(data){
    			for(var obj in data){
    				$("input[name='stateid']").val(data[obj].stateid);
    				$("input[name='customername']").val(data[obj].scustomername);
    				$("input[name='sid']").val(data[obj].sid);
    				$("input[name='sale']").val(data[obj].sale);    			
    				$("input[name='scustomercardid']").val(data[obj].scustomercardid);
    				$("input[name='scustomerbankcardid']").val(data[obj].scustomerbankcardid);
    				$("input[name='backfee']").val(data[obj].backfee);    				
    				//$("input[name='backfee']").text(data[obj].backfee);
    				$("input[name='remark']").text(data[obj].sremark);
    			}
    		}
		})
		$("#infoback").modal("show");	
	}
};
//退款
function ExitModel(){
	
	var signcheckbox = $("input[name^='signed_checkbox']:checkbox:checked");
	if(signcheckbox.length!=1){
		alert("请选择单条数据操作！")
		return false;
	}else{
		var d = new Date();
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		var handler =$.trim($(".navbar li:eq(0) a").text());
		handler = handler.substr(handler.indexOf('['),handler.length);
		$("input[name='time']").val(str);
		$("input[name='handler']").val(handler);
		$.ajax({
			type : 'POST',
    		url : 'signed/onesign.do',
    		dataType :'json',
    		async:false, 
    		data : { "sid" : signcheckbox.val()},
    		success : function(data){
    			for(var obj in data){
    				$("input[name='stateid']").val(data[obj].stateid);
    				$("input[name='customername']").val(data[obj].scustomername);
    				$("input[name='sid']").val(data[obj].sid);
    				$("input[name='sale']").val(data[obj].sale);    			
    				$("input[name='scustomercardid']").val(data[obj].scustomercardid);
    				$("input[name='scustomerbankcardid']").val(data[obj].scustomerbankcardid);
    				$("input[name='backfee']").val(data[obj].backfee);    				
    				//$("input[name='backfee']").text(data[obj].backfee);
    				$("input[name='remark']").text(data[obj].sremark);
    			}
    		}
		})
		$("#info").modal("show");	
	}
};
//提交事件。submitbyback 返款提交！待返款才可以申请返款，即statid=3的时候才可以！
function submitbyexit(){	
	var  statid=document.getElementById("stateid").value;//订单状态。
	if(statid!=2){//临时改的。
		alert("不能退款");
		return false; 		
	}	
	else{		
		alert("申请中，请等待");
		return true;			
	}
};

//提交事件。submitbyback 退款提交。退款前提是缴费了才可以退钱，statid=2的时候才可以退款。

function submitbyback(){
	
	var  statid=document.getElementById("stateid").value;//订单状态。
	alert(statid);
	if(statid!=3){
		alert("不能返款");
		return false; 	
		
	}else{
		alert("申请中，请等待");
		return true;				
	}	
};


