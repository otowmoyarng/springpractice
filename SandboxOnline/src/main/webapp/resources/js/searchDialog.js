/**
 * 検索ダイアログ	JS定義
 */
function setParams() {
	var params = window.dialogArguments;
	
	if (params != null) {
		if (params[0] != null) {
			console.log("params[0]:" + params[0]);
			document.getElementById('companykbn' + params[0]).checked = 'checked';
			document.getElementById('hdn_companykbn').value = params[0];
		}
		if (params[0] != null) {
			console.log("params[0]:" + params[0]);
		}
	}
}
function getSelectedItms() {
	var tbody = docmennt.getElementByID('searchlist-tbody');
	 
	for (var i=0, rowlength=tbody.children.length; i < rowlength; i++) {
		 
		for (var j=0, columnlength=tbody.children[i].cells.length; j < columnlength; i++) {
			 
			var cell = tbody.children[i].cells[j];
			console.log(i + "-" + i);
			console.log(cell);
		}
	}
}