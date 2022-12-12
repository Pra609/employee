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
	let page=Number($('#compamnyPageNo').text())
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
					'<td id = "Id' + item.company_id + '">' + item.company_id + '</td>' +
					'<td id = "name' + item.company_id + '">' + item.company_name+ '</td>' +




					'<td>' +
					'<button type = "button" id = "edit' + item.company_id + '" class = "btn btn-warning btn-md edit">Edit</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "delet' + item.company_id+ '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.billid + ')">Pay Now</button>' +
					'</td>' +




					'</tr>';
			});
			$("#myTable>tbody").html(tableData);


}

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