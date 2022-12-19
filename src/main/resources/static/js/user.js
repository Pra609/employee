var did=localStorage.getItem("departmentId");
console.log(did+"did")

$(document).ready(
	function() {

		// SUBMIT FORM
		$("#getUsers").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxGet();
		});

	});
var globleTableData=[]
//var url = 'http://localhost:9000/admin/companyView/'+;
//var cid = url.substring(url.lastIndexOf('/') + 1);



function decPageNo()
{
	let page=Number($('#userPageNo').text())
	//console.log(page,'prev')
	if(page!=1)
	page-=1
	pagedata(page);
	$('#userPageNo').text(page)
}

function incPageNo()
{
	let page=Number($('#userPageNo').text())
	//console.log(page,'prev')
	let datalength=globleTableData.length;
	let pagelimit=Math.ceil((datalength/5.0));
	if(page+1>pagelimit)
	{
		console.log('Page limit reached');
		return;
	}
	pagedata(page+1);
	$('#userPageNo').text(page+1)
}

function pagedata(pageno){
	let tableData="";
	let startno=(pageno-1)*5
	let endno=(pageno*5)
	data=globleTableData.slice(startno,endno)
	data.forEach(function(item) {
		tableData += '<tr>' +
					'<td id = "Id' + item.userId+ '">' + item.userId + '</td>' +
					'<td id = "name' + item.userId+ '">' + item.name+ '</td>' +
					'<td id = "name' + item.userId+ '">' + item.email+ '</td>' +
					'<td id = "name' + item.userId+ '">' + item.dname+ '</td>' +




					'<td>' +
                    					'<button type = "button" id = "edit' + item.usertId+'" class = "btn btn-warning btn-md edit" onclick = "edit(' + item.userId + ')">Edit</button>' +
                    					'</td>' +
                    					'<td>' +
                    					'<button type = "button" id = "delet' + item.userId+'" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.userId + ')">Delete</button>' +
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
		url: "/user/department/"+did,
		data:{keyword: keyword},
		success: function(data) {
		globleTableData=data
			let page=Number($('#userPageNo').text())
			console.log(globleTableData,'globleTableData')
			pagedata(page)

		},

	});
}

$(document).ready(function() {
	ajaxGet();
})
