/**
 * 検索ダイアログ	JS定義
 */
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