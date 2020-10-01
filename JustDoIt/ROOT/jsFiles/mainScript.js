var JSON_OBJ_ALL_IDS = [];

function onLoadOfApplication(){
	console.log("Application Loaded");
	idJsonCreation();
}
function idJsonCreation() {
	$('#listSection li a').each(function (i) {
        var index = $(this).index();
        var text = $(this).text();
        var value = $(this).attr('href');
		var sectionAndUrl=value.split("/");
		var user = {"Name" : text, "Section" : sectionAndUrl[0], "UrlName" : sectionAndUrl[1],"Url": value };
		JSON_OBJ_ALL_IDS.push(user);
	});
}
function searchForm(){
	$("#searchResults").html("");
	var methodName;
	var inputStr = $('#txtSearch').val().toLowerCase();
	
	var userInput= inputStr.split(" ");
	
	var resultObject = search(JSON_OBJ_ALL_IDS, userInput);
	relatedSearch =resultObject;
	for(var i=0;i<5;i++)
		{
			var highestHit=relatedSearch[i].arrayIndex;
			$("#searchResults").append("<li><a href='"+JSON_OBJ_ALL_IDS[highestHit].Url+"' target='_blank'>"+JSON_OBJ_ALL_IDS[highestHit].Name+"</a></li>");
			$("#searchBoxResult").show();
		}
	
}
function search(JsonObject, userInput){
	
	$("#searchResults").empty();
	var finalArray=[];
	var len=JsonObject.length;
		for (var j = 0; j < len; j++) {
			var c = 0;
			var jsonStr = JsonObject[j].Name.split(" ");

				for (var i = 0; i < userInput.length; i++) {
					for (var k = 0; k < jsonStr.length; k++) {
						if (userInput[i].indexOf(jsonStr[k].toLowerCase()) > -1)
						{
							c++;
						}
						if(userInput[i]==jsonStr[k].toLowerCase()){
							c++;
						}
					}
				}
			var arrObj = {"hitCount" : c,"arrayIndex" : j};
			finalArray.push(arrObj);	
			
	}
		sortedListArray=sortResults(finalArray,"hitCount",false);
		return sortedListArray;
}
function sortResults(objectTobeSorted, sortingVariable, asc)
{
    var newObject = objectTobeSorted.sort(function(a, b)
    {
        if(asc)
        {
            return (a[sortingVariable] > b[sortingVariable]) ? 1 : ((a[sortingVariable] < b[sortingVariable]) ? -1 : 0);
        }
        else
        {
            return (b[sortingVariable] > a[sortingVariable]) ? 1 : ((b[sortingVariable] < a[sortingVariable]) ? -1 : 0);
        }
    });
    return newObject;
}
$(document).mouseup(function(e) 
{
    var container = $("#searchBoxResult");
    if (!container.is(e.target) && container.has(e.target).length === 0) 
    {
        container.hide();
		$("#txtSearch").val("");
    }
});