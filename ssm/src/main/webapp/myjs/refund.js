/**
 * @date 2016/10/25
 * @author 李敏
 * 财务操作的内容(收款,返款,退款,查看签单详情)
 */
$(document).ready(function(){
	$(".tcdPageCode:eq(0)").createPage({
	    pageCount:$("#pagecount").val(),
	    current:1,
	    backFn:function(p){}
	});
})

//弹出返款对话框
function showoutModel(){
	var signcheckbox = $("input[name='backmoney_checkbox']:checkbox:checked");
	if(signcheckbox.length!=1){
		alert("请选择单条数据操作！")
	}else{
		$("input[name='time']").val(nowDate());
		$("input[name='handler']").val(username());
		$.ajax({
			type : 'POST',
    		url : 'signed/onebackmoney.do',
    		dataType :'json',
    		async:false, //这是重要的一步，防止重复提交的
    		data : { "dbid" : signcheckbox.val()},
    		success : function(data){
    			
    			$("input[name='dbid']").val(signcheckbox.val());
    			for(var obj in data){
    				$("input[name='sid']").val(data[obj].sid);
    				$("#backmoneyform span:eq(2)").text(data[obj].dbemp);
    				$("#backmoneyform span:eq(3)").text(data[obj].scustomername);
    				$("#backmoneyform span:eq(4)").text(data[obj].scustomercardid);
    				$("input[name=amount]").val(data[obj].dbamount);
    				$("#backmoneyform span:eq(5)").text(data[obj].scustomerbankcardid);
    			}
    		}
		})
		$("#backmoneyinfo").modal('show');
	}
}

//弹出退款对话框
function showRefundModel(){
	var signcheckbox = $("input[name='refund_checkbox']:checkbox:checked");
	if(signcheckbox.length!=1){
		alert("请选择单条数据操作！")
	}else{
		$("#outtime").val(nowDate());
		$("#outhandler").val(username());
		$.ajax({
			type : 'POST',
    		url : 'signed/onebackmoney.do',
    		dataType :'json',
    		async:false, //这是重要的一步，防止重复提交的
    		data : { "dbid" : signcheckbox.val()},
    		success : function(data){
    			$("input[name='dbid']").val(signcheckbox.val());
    			for(var obj in data){
    				alert(data[obj].dbemp);
    				$("input[name='sid']").val(data[obj].sid);
    				$("#outmoneyform span:eq(2)").text(data[obj].dbemp);
    				$("#outmoneyform span:eq(3)").text(data[obj].scustomername);
    				$("#outmoneyform span:eq(4)").text(data[obj].scustomercardid);
    				$("#outamount").val(data[obj].dbamount);
    				$("#outmoneyform span:eq(5)").text(data[obj].scustomerbankcardid);
    			}
    		}
		})
		$("#outmoneyinfo").modal('show');
	}
}

//提交返款信息弹出确认的对话框
function identification(){
	if(!confirm("确定吗")){
		return false;
	}else{
		var amount = $("input[name='amount']").val();
		if(amount>0){
			$("input[name='amount']").val("-"+amount);
		}
		$("input[name='stateid']").val(10);
	}
}