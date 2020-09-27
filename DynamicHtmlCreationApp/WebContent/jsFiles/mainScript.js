/*Global Variables*/
var JSON_VALUE_POPULATE=[];
var JSON_VALUE_POPULATE_OBJ=[];
/*Global Variables*/
function onloadOfApplication(){
	clearAllFields();
}

function callToServer(){
	$.ajax(
			{
				type: "POST",
				url: "/DynamicHtmlCreationApp/ServletInitializer",
				async: true,
				data:  $("form").serialize(),
				success: function(result)
				{
					JSON_VALUE_POPULATE=JSON.parse(result);
					//populate("form", JSON_VALUE_POPULATE);
					if(JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA!=undefined){
						sortTable();
						stopLoader();
					}
					if(JSON_VALUE_POPULATE.UPDATE_STATUS_KEY==true){
						alert("UPDATE WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
					if(JSON_VALUE_POPULATE.INSERT_STATUS_KEY==true){
						alert("INSERT WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
					if(result=="true"){
						console.log(result);
						stopLoader();
					}
					else{
						stopLoader();
					}
					stopLoader();
				},
				error : function(result)
				{
					alert("ERROR");
					stopLoader();
				}
			});
}
function stopLoader()
{
	$("#loader").animate({opacity: '0.0'}, 280,loaderHide());
}
function loaderHide(){
	setTimeout(function(){$("#loader").hide();}, 300);
}
function startLoader()
{
	$("#loader").css('opacity', '1');
	$("#loader").show(100);
}
function populate(frm, data) {
    $.each(data, function(key, value) {  
        var ctrl = $('[name='+key+']', frm);  
        switch(ctrl.prop("type")) {
            case "radio": case "checkbox":   
                ctrl.each(function() {
                    //if($(this).attr('value') == value) $(this).attr("checked",value);
                	$("input[name="+key+"][value=" + value + "]").prop('checked', true);
                });   
                break;  
            default:
                ctrl.val(value); 
        }  
    });  
}
function checkRadioBtnInp(){
	var inputName=$("input[name=txtGenerationType]:checked").val();
	if(inputName=="FETCH_COMPLETE_DATA"){
		if(JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA==null || JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA==undefined || JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA==""){
			$("#allInputFields").hide();
			clearAllFields();
			callToServer();
			$("#btnSubmit1").hide();
		}
		else{
			$("#allInputFields").hide();
			clearAllFields();
			sortTable();
			$("#btnSubmit1").hide();
			stopLoader();
		}
	}
	else if(inputName=="NEW_FILE_GENERATION"){
		$("#allInputFields").show();
		$("#txtSno").hide();
		$("#SnoLabel").hide();
		$('#myTable').hide();
		$("#txtSno").val("");
		$("#btnSubmit1").show();
		stopLoader();
	}
	else if(inputName=="EDIT_FILE"){
		$("#txtSno").show();
		$("#SnoLabel").show();
		$("#allInputFields").show();
		$("#btnSubmit1").show();
		stopLoader();
	}
	else{
		$("#allInputFields").hide();
		$('#myTable').hide();
		$("#btnSubmit1").show();
		clearAllFields();
		stopLoader();
	}
}

function sortTable() {
	$('#headerId').empty();
	$('#bodyId').empty();
	$('#myTable').show();
	JSON_VALUE_POPULATE_OBJ=[];

	var arrOfSec=["admit-card","other-links","latest-results","latest-jobs"];
	$thead = $('#myTable').find('thead');
	$tbody = $('#myTable').find('tbody');
	for (var i = 0; i < arrOfSec.length; i++) {
		var lenOfinnerArr=JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA[arrOfSec[i]];
		for (var j = 0; j < lenOfinnerArr.length; j++) {
			JSON_VALUE_POPULATE_OBJ.push(JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA[arrOfSec[i]][j]);
		}
	}
	JSON_VALUE_POPULATE_OBJ.sort(function(a, b){
	    return a.txtSno - b.txtSno;
	});
	
	var keys = Object.keys(JSON_VALUE_POPULATE_OBJ);
	$thead.append('<th>Select</th>');
	for(var i=0; i<keys.length; i++){
		var key=keys[i];
		var count=0;
		var columnsToShow=5;
		$tbody.append('<tr>');
		$.each(JSON_VALUE_POPULATE_OBJ[key],function(key, value){
			if(count<columnsToShow)
			{
				if(i==0)
					$thead.append('<th>'+key+'</th>');
				if(count==0)
					$tbody.append('<td><button type="button" class="edit1 btn-Edit" onclick="EditButtonClick('+i+',\''+key+'\','+value+');">Edit</button></td>');
				$tbody.append('<td>'+value+'</td>');
				count++;
			}
	    });
	}
}
function EditButtonClick(arrPosition,key,value){
	
	console.log(arrPosition+":"+":"+key+":"+value);
	populate("form", JSON_VALUE_POPULATE_OBJ[arrPosition]);
	$('#myTable').hide();
	$('#allInputFields').show();
	$('#txtSno').prop("readonly",true);
	option5.click();
//	$("input[name=txtGenerationType]").removeClass("active");
//	$("#option5").addClass("active");
}
function clearAllFields(){
	$("select").val("");
	$("textarea").val("");
	$("input:text").val("");
}
function callToServerCheck(){
	var ids=['txtName','txtKeyword','txtUrl','txtSection','txtContent'];
	var flag=false;
	for (var i = 0; i < ids.length; i++) {
		if($("#"+ids[i]).val()=="" && $("#"+ids[i]).is(":visible")){
			alert("Field is empty::"+ids[i]);
			flag=true;
			stopLoader();
			break;
		}
	}
	if(!flag){
		callToServer();
		//stopLoader();
	}
}