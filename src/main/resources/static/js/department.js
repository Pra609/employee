
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
                                            					'<button type = "button" id = "view' + item.departmentId+'" class = "btn btn-danger btn-md delet" onclick = "view(' + item.departmentId + ')">View</button>' +
                                            					'</td>' +


					'<td>' +
                    					'<button type = "button" id = "edit' + item.departmentId+'" class = "btn btn-warning btn-md edit">Edit</button>' +
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



