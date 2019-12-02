/**
 * 共通JSライブラリファイル
 * 複数画面で使用するjsモジュールを配置する
 */

/**
 * 画面初回起動
 */
function showwindow() {
    var features = 'menubar=no,toolbar=no,location=yes,resizable=yes,scrollbars=yes,status=no,height=768,width=1280';
    window.open('./main', '_blank', features);
    window.open("", "_self").close();
}

/**
 * 検索ダイアログ表示
 */
function showSearchDialog(companykbn) {
	
	// 会社区分を取得する
    var kbn = document.getElementById('companykbn');
    
	// 会社番号を取得する
    var cno = document.getElementById('companyno');
    
    var searchparamters = new Array(kbn.value, cno.value);
    console.log('companykbn:' + kbn.value);
    console.log('companyno:' + cno.value);
//    var searchparamters = {};
//    searchparamters.companyno = cno.value;
//    searchparamters.companykbn = companykbn;
//    window.alert('companyno:' + searchparamters.companyno);
//    window.alert('companykbn:' + searchparamters.companykbn);
    
    var option = 'dialogheight=768; dialogwidth=1280;';
    var result = window.showModalDialog('./search/init', searchparamters, option);
//    var result = window.showModalDialog('../../searchDialog.html', searchparamters, option);
//    var result = window.showModalDialog('./searchDialog.html', searchparamters, option);
    console.log('result:' + result);
}
