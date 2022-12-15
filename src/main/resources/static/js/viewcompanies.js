

$(document).ready(
	function() {

		// SUBMIT FORM
		$("#getCompanies").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxGet();
		});

	});
var globleTableData=[]
var coId=null;
function decPageNo()
{
	let page=Number($('#companyPageNo').text())
	//console.log(page,'prev')
	if(page!=1)
	page-=1
	pagedata(page);
	$('#companyPageNo').text(page)
}

function incPageNo()
{
	let page=Number($('#companyPageNo').text())
	//console.log(page,'prev')
	let datalength=globleTableData.length;
	let pagelimit=Math.ceil((datalength/5.0));
	if(page+1>pagelimit)
	{
		console.log('Page limit reached');
		return;
	}
	pagedata(page+1);
	$('#companyPageNo').text(page+1)
}

function pagedata(pageno){
	let tableData="";
	let startno=(pageno-1)*5
	let endno=(pageno*5)
	data=globleTableData.slice(startno,endno)
	data.forEach(function(item) {



		tableData += '<tr>' +
					'<td id = "Id' + item.companyId + '">' + item.companyId + '</td>' +
					'<td id = "name' + item.companyId + '">' + item.companyName+ '</td>' +




				'<td>' +
                       '<button type = "button" id = "coView' +item.companyId + '" class = "btn btn-danger btn-md coView" onclick = "coView(' +item.companyId + ')">View</button>' +
                '</td>' +
                '<td>' +
                					'<button type = "button" id = "edit' + item.companyId + '" class = "btn btn-warning btn-md edit" onclick = "edit(' + item.companyId + ')">Edit</button>' +
                					'</td>' +
                '<td>' +
                   '<button type = "button" id = "delet' + item.companyId + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.companyId + ')">Delete</button>' +
                '</td>' +




					'</tr>';
			});
			$("#myTable>tbody").html(tableData);


}
// DO GET
function ajaxGet() {
    var keyword = $("#keyword").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/companies",
		data:{keyword: keyword},
		success: function(data) {
		globleTableData=data
			let page=Number($('#companyPageNo').text())
			console.log(globleTableData,'globleTableData')
			pagedata(page)

		},

	});
}

$(document).ready(function() {
	ajaxGet();
})
/*$(document).delegate('.vie', 'click', function() {
//window.location.href = "companyView";
coId=vie(companyId);
console.log(coId+"coid");
});*/

function coView(companyId){
window.location.href = "/admin/companyView/"+companyId,
//var newWindow = window.open('/admin/companyView/'+companyId)
coId=companyId;
//newWindow.my_special_setting = companyId;
console.log(coId+"coid");
var m=companyId;
localStorage.setItem("companyId",m);
};


/* Delete */
function delet(companyId){
	if (confirm('Do you really want to delete the company?')) {
		var parent = $(this).parent().parent();

		$.ajax({
			type: "DELETE",
			url: "/company/" +companyId,
			cache: false,
			success: function() {
			console.log("success")
				parent.fadeOut('slow', function() {
					$(this).remove();
				});
				location.reload(true)
			},
			error: function() {
			console.log("error")
				$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
					$(this).remove();
				});
			}
		});
	}
};
function edit(companyId){

 coId=companyId;
 console.log(coId+"coId")



$(document).delegate('.edit', 'click', function() {
   console.log(companyId+"companyId")
  //coId=companyId
	var parent = $(this).parent().parent();

	var companyId = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");

    var buttons = parent.children("td:nth-child(4)");

   // console.log(companyId.id+"companyId")


	name.html("<input type='text' id='name' value='" + name.html() + "'/>");



	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();


	var companyId = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");

    var buttons = parent.children("td:nth-child(4)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/editCompany/"+coId,

		data: JSON.stringify({
			'companyId': companyId.html(), 'name': name.children("input[type=text]").val(),

		}),
		cache: false,
		success: function() {

	       name.html(name.children("input[type = text]").val());


			buttons.html("<button class='btn btn-warning edit' id='" + companyId.html() + "'>Edit</button>");
		},

	});

});

};


