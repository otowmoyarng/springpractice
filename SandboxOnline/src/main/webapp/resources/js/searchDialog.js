/**
 * 検索ダイアログ	JS定義
 */

/**
 * 初期化処理
 */
function initialize() {
	
	// 画面遷移パラメータ設定
	setParams();
	
	// 選択ボタンの使用可否設定
	enableSelectedButton();
}

/**
 * 画面遷移パラメータ設定
 */
function setParams() {
	var params = window.dialogArguments;
	
	if (params != null) {
		if (params[0] != null) {
			console.log("params[0]:" + params[0]);
			document.getElementById('companykbn' + params[0]).checked = 'checked';
			document.getElementById('hdn_companykbn').value = params[0];
		}
		if (params[1] != null) {
			console.log("params[1]:" + params[1]);
			document.getElementById('companyno').value = params[1];
		}
	}
}

/**
 * 選択ボタンクリック使用可否
 */
function enableSelectedButton() {
	
	var tablecount = searchlistmeisai.rows.length;
    var button = document.getElementById('selectedbutton');
    
	button.disabled = !(tablecount > 0);
}

/**
 * 選択ボタンクリック時
 */
function onclickSelectedButton() {
//	var tbody = docmennt.getElementByID('searchlist-tbody');
//	 
//	for (var i=0, rowlength=tbody.children.length; i < rowlength; i++) {
//		 
//		for (var j=0, columnlength=tbody.children[i].cells.length; j < columnlength; i++) {
//			 
//			var cell = tbody.children[i].cells[j];
//			console.log(i + "-" + i);
//			console.log(cell);
//		}
//	}
	for (var i=0, rowlength=searchlistmeisai.rows.length; i < rowlength; i++) {
		
		for (var j=0, columnlength=searchlistmeisai.rows[i].cells.length; j < columnlength; j++) {
			
			// j=0の場合はchildren[0]からinputタグを取得する
			// 上記以外はchildrenを参照しない
			// rowspan=2の行は奇数行しか取得できない
			var cell;
			if (j == 0) {
				cell = searchlistmeisai.rows[i].cells[j].children[0];
			} else {
				cell = searchlistmeisai.rows[i].cells[j];
			}
			console.log(i + "-" + j);
			console.log(cell);
		}
	}
}

/**
 * 選択ボタンクリック時
 */
function onclickCancelButton() {
	window.close();
}