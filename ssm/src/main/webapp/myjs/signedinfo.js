$(document).ready(function(){
	$(".clickbythis").dblclick(function(){
		alert('mmm');
		$.ajax({
			type : 'POST',
    		url : 'signed/selectByPrimaryKey.do',
    		dataType :'json',
    		async:false, //这是重要的一步，防止重复提交的
    		data : { "sid" : sid},
    		success : function(data){
    			for(var obj in data){
    				$("input[name='sid']").sid(data[obj].sid);
    				$("input[name='stime']").text(data[obj].stime);
    				$("input[name='scustomername']").text(data[obj].scustomername);   				  				
    				$("input[name='speoplenum']").text(data[obj].speoplenum);
    				$("input[name='scustomergrade']").text(data[obj].scustomergrade);
    				$("input[name='sbusinesstype']").text(data[obj].sbusinesstype);
    				$("input[name='scustomerschool']").text(data[obj].scustomerschool);
    				$("input[name='scustomercollege']").text(data[obj].scustomercollege);
    				$("input[name='condition']").text(data[obj].condition);
    				$("input[name='dept']").text(data[obj].dept);
    				$("input[name='sale']").text(data[obj].sale);
    				$("input[name='sarea']").text(data[obj].sarea);
    				$("input[name='sremark']").text(data[obj].sremark);
    				
    			}
    		}
		})
		$("#SigneDetailsInfo").modal('show');
	});
});