

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
                    					'<button type = "button" id = "view' + item.companyId + '" class = "btn btn-warning btn-md view" >View</button>' +
                    					'</td>' +


					'<td>' +
                    					'<button type = "button" id = "edit' + item.companyId + '" class = "btn btn-warning btn-md edit">Edit</button>' +
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
//			data.forEach(function(item) {
//				tableData += '<tr>' +
//					'<td id = "Id' + item.billid + '">' + item.billid + '</td>' +
//					'<td id = "name' + item.billid + '">' + item.name+ '</td>' +
//					'<td id = "meter' + item.billid + '">' + item.meter+ '</td>' +
//                    '<td id = "metertype' + item.billid + '">' + item.metertype+ '</td>' +
//					'<td id = "board' + item.billid + '">' + item.board+ '</td>' +
//					'<td id = "bconntype' + item.billid + '">' + item.bconntype + '</td>' +
//					'<td id = "energy' + item.billid + '">' + item.energy + '</td>' +
//					'<td id = "date' + item.billid + '">' + item.date + '</td>' +
//					'<td id = "price' + item.billid + '">' + item.price + '</td>' +
//					'<td id = "status' + item.billid + '">' + item.status + '</td>' +
//
//
//
//
//					'<td>' +
//					'<button type = "button" id = "edit' + item.billid + '" class = "btn btn-warning btn-md edit">Edit</button>' +
//					'</td>' +
//					'<td>' +
//					'<button type = "button" id = "delet' + item.billid + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.billid + ')">Pay Now</button>' +
//					'</td>' +
//
//
//
//
//					'</tr>';
//			});
//			$("#myTable>tbody").html(tableData);
		},

	});
}

$(document).ready(function() {
	ajaxGet();
})

$(document).delegate('.view', 'click', function() {
window.location.href = "/admin/companyView"

var cid=

});



