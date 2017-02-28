package wyl.search.util;

/**
 * 通用工具类
 * @author dfsj0317
 *
 */
public class CommonUtil {
	/**
	 * 验证是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 验证手机号
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("1[3578][0-9]{9}");
	}
	
	public static boolean isBlank(String str){
		if (str==null || "".equals(str)){
			return true;
		}
		return false;
	}
}
