package jp.co.product.system.sys.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class SystemParameterUtils {

	private static Map<String, Object> cache;
	
//	static {
//		cache = new HashMap<String, Object>();
//		cache.put("search_count", 100);
//		cache.put("csv_count", 1000);
//		cache.put("search_time", 60);
//	}
	
	/**
	 * �p�����[�^�}�X�^���L���b�V������
	 */
	public static void initializeCache() {
		
		System.out.println("�L���b�V���J�n");
		cache = new HashMap<String, Object>();
		cache.put("search_count", 100);
		cache.put("csv_count", 1000);
		cache.put("search_time", 60);
		System.out.println("�L���b�V���I��");
		System.out.println("�L���b�V�������F" + cache.size());
	}

	/**
	 * �p�����[�^�[��������擾����
	 * @param	paramcd	�p�����[�^�[�R�|�h
	 * @return	�p�����[�^�[������
	 */
	public static String getPString(String paramcd) {
		// �p�����[�^�[�R�[�h���L���b�V���ɑ��݂��Ȃ��ꍇ
		// �p�����[�^�[�R�[�h����擾�����^��String�łȂ��ꍇ
		System.out.println("���s���\�b�h�FgetPString");
		System.out.println("�L���b�V�������F" + cache.size());
		return cache.get(paramcd).toString();
	}

	/**
	 * �p�����[�^�[���l���擾����
	 * @param	paramcd	�p�����[�^�[�R�|�h
	 * @return	�p�����[�^�[���l
	 */
	public static BigDecimal getPNumber(String paramcd) {
		// �p�����[�^�[�R�[�h���L���b�V���ɑ��݂��Ȃ��ꍇ
		// �p�����[�^�[�R�[�h����擾�����^��String�łȂ��ꍇ
		System.out.println("���s���\�b�h�FgetPNumber");
		System.out.println("�L���b�V�������F" + cache.size());
		return new BigDecimal(cache.get(paramcd).toString());
	}
}
