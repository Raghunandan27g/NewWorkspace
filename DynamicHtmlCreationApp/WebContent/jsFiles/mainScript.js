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
						alertMsg("UPDATE WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
					if(JSON_VALUE_POPULATE.INSERT_STATUS_KEY==true){
						alertMsg("INSERT WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
					if(JSON_VALUE_POPULATE.COMPLETE_STATUS_KEY==true){
						alertMsg("COMPLETE GENERATION WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
					if(JSON_VALUE_POPULATE.HOMEPAGE_STATUS_KEY==true){
						alertMsg("HOMEPAGE GENERATION WAS SUCCESSFULL");
						$("#myTable").hide();
						stopLoader();
					}
						stopLoader();
				},
				error : function(result)
				{
					alertMsg("ERROR");
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

	//var arrOfSec=["admit-card","other-links","latest-results","latest-jobs"];
	$thead = $('#myTable').find('thead');
	$tbody = $('#myTable').find('tbody');
	Object.keys(JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA).forEach(function(key) {
		//console.log(key);
		var lenOfinnerArr=JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA[key];
		for (var j = 0; j < lenOfinnerArr.length; j++) {
			JSON_VALUE_POPULATE_OBJ.push(JSON_VALUE_POPULATE.FETCH_COMPLETE_DATA[key][j]);
		}
	});

	
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
			alertMsg("Field is empty::"+ids[i]);
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
function alertMsg(msg){
	createModal("ALERT",msg,"alert");
}

function createModal(title, message, type) {  
    customModal(title, message, type);  
 }  
function customModal(head, body, type) {
 if(type == 'prompt') {  
    $('#modal-head').html('<h4 class="modal-title">'+head+'</h4>');  
    $('#modal-body').html('<div class="row">   <label class="col-sm-3">Name</label> <div class="col-md-9">  '+
        +' <input class="form-control" type="text" id="name-input">       </div>       </div>');  
 $('#modal-footer').html('       <button type="button" class="btn btn-primary" id="done-btn">Done</button>  '+
   +'  <button type="button" class="btn btn-danger" id="cancel-btn">Cancel</button>'); 
      $('#custom-modal').modal('show');  
       $('#cancel-btn').on('click', function() {  
           return response('cancel');  
        });  
       $('#done-btn').on('click', function() {  
             return response('done');  
       });  
    } else if( type == 'alert') {
          $('#modal-head').html('   <h4 class="modal-title">'+head+'</h4>');  
       $('#modal-body').html('             <p>' + body + '</p>');  
       $('#modal-footer').html('       <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>');  
       $('#custom-modal').modal('show');  
       } else if( type == 'confirm') {  
           $('#modal-head').html('       <h4 class="modal-title">'+ head +'</h4>');  
       $('#modal-body').html('             <p>' + body + '</p>');  
       $('#modal-footer').html('<button type="button" class="btn btn-primary" id="ok-btn">Ok</button>/ '+ 
    +' <button type="button" class="btn btn-danger" id="cancel-btn">Cancel</button>');  
       $('#custom-modal').modal('show');  
       $('#cancel-btn').on('click', function() {  
             return response('cancel');  
          });  
       $('#ok-btn').on('click', function() {  
             return response('ok');  
             });  
             }  
          }
    function response(type) {  
       if(type == 'done') {  
       if(document.getElementById('name-input').value != '') {  
          $('#user-name').html( 'Welcome '+ document.getElementById('name-input').value);  
          $('#custom-modal').modal('hide');  
          return document.getElementById('name-input').value;  
          } else {  
             alert('Please Enter your name');  
             }  
          } else if(type == 'cancel') {  
$('#custom-modal').modal('hide');  
    return false;  
 } else if(type == 'ok') {  
       $('#custom-modal').modal('hide');  
       return true;  
       }  
    } 
    
$(function(){
	$('.demo-noninputable').pastableNonInputable();
	$('.demo-textarea').on('focus', function(){
	  var isFocused = $(this).hasClass('pastable-focus');
	  console && console.log('[textarea] focus event fired! ' + (isFocused ? 'fake onfocus' : 'real onfocus'));
	}).pastableTextarea().on('blur', function(){
	  var isFocused = $(this).hasClass('pastable-focus');
	  console && console.log('[textarea] blur event fired! ' + (isFocused ? 'fake onblur' : 'real onblur'));
	});
	$('.demo-contenteditable').pastableContenteditable();
	$('.demo').on('pasteImage', function(ev, data){
	  var blobUrl = URL.createObjectURL(data.blob);
	  $("#imgBlobData").val(JSON.stringify(data));
	  $(".result").remove();
	  $('<div class="result"><input type="file" id="imgInp" />image: ' + data.width + ' x ' + data.height + '<img src="' + data.dataURL +'" ><a href="' + blobUrl + '">' + blobUrl + '</div>').insertAfter(this);
	}).on('pasteImageError', function(ev, data){
	  alert('Oops: ' + data.message);
	  if(data.url){
	    alert('But we got its url anyway:' + data.url)
	  }
	}).on('pasteText', function(ev, data){
	  $('<div class="result"></div>').text('text: "' + data.text + '"').insertAfter(this);
	});
});