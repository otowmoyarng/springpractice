package jp.co.product.system.sys.cache;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemParameterTest {

	public static void main(String[] args) {
		
		//SystemParameter sp = n
		// ˆ—“ú
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.print(sdf1.format(date));
	}
}
