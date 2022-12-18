
var cid=localStorage.getItem("companyId");
console.log(cid+"cid")



$(document).ready(
	function() {

		// SUBMIT FORM
		$("#getDepartments").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxGet();
		});

	});
var globleTableData=[]
//var url = 'http://localhost:9000/admin/companyView/'+;
//var cid = url.substring(url.lastIndexOf('/') + 1);


console.log(cid)
function decPageNo()
{
	let page=Number($('#departmentPageNo').text())
	//console.log(page,'prev')
	if(page!=1)
	page-=1
	pagedata(page);
	$('#departmentPageNo').text(page)
}

function incPageNo()
{
	let page=Number($('#departmentPageNo').text())
	//console.log(page,'prev')
	let datalength=globleTableData.length;
	let pagelimit=Math.ceil((datalength/5.0));
	if(page+1>pagelimit)
	{
		console.log('Page limit reached');
		return;
	}
	pagedata(page+1);
	$('#departmentPageNo').text(page+1)
}

function pagedata(pageno){
	let tableData="";
	let startno=(pageno-1)*5
	let endno=(pageno*5)
	data=globleTableData.slice(startno,endno)
	data.forEach(function(item) {
		tableData += '<tr>' +
					'<td id = "Id' + item.departmentId+ '">' + item.departmentId + '</td>' +
					'<td id = "name' + item.departmentId+ '">' + item.departmentName+ '</td>' +

						'<td>' +
                                            					'<button type = "button" id = "view' + item.departmentId+'" class = "btn btn-warning btn-md view" onclick = "view(' + item.departmentId + ')">View</button>' +
                                            					'</td>' +


					'<td>' +
                    					'<button type = "button" id = "edit' + item.departmentId+'" class = "btn btn-warning btn-md edit" onclick = "edit(' + item.departmentId + ')">Edit</button>' +
                    					'</td>' +
                    					'<td>' +
                    					'<button type = "button" id = "delet' + item.departmentId+'" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.departmentId + ')">Delete</button>' +
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
		url: "/departmentbycompany/"+cid,
		data:{keyword: keyword},
		success: function(data) {
		globleTableData=data
			let page=Number($('#departmentPageNo').text())
			console.log(globleTableData,'globleTableData')
			pagedata(page)

		},

	});
}

$(document).ready(function() {
	ajaxGet();
})



/*DELETE*/

function delet(departmentId){
	if (confirm('Do you really want to delete the company?')) {
		var parent = $(this).parent().parent();

		$.ajax({
			type: "DELETE",
			url: "/department/"+cid+"/"+departmentId,
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

function view(departmentId){
window.location.href = "/admin/userView/"+departmentId;
//var newWindow = window.open('/admin/companyView/'+companyId)
//coId=companyId;
//newWindow.my_special_setting = companyId;
//console.log(coId+"coid");
var n=departmentId;
localStorage.setItem("departmentId",n);
};


function edit(departmentId){

 //coId=companyId;
 //console.log(coId+"coId")

var dId=departmentId;

$(document).delegate('.edit', 'click', function() {
  // console.log(companyId+"companyId")
  //coId=companyId
	var parent = $(this).parent().parent();

	var departmentId = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");

    var buttons = parent.children("td:nth-child(4)");

   // console.log(companyId.id+"companyId")


	name.html("<input type='text' id='name' value='" + name.html() + "'/>");



	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();


	var departmentId = parent.children("td:nth-child(1)");
	var name = parent.children("td:nth-child(2)");

    var buttons = parent.children("td:nth-child(4)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/edit/department/"+cid+"/"+dId,

		data: JSON.stringify({
			'departmentId': departmentId.html(), 'name': name.children("input[type=text]").val(),

		}),
		cache: false,
		success: function() {

	       name.html(name.children("input[type = text]").val());


			buttons.html("<button class='btn btn-warning edit' id='" + departmentId.html() + "'>Edit</button>");
		},

	});

});

};






