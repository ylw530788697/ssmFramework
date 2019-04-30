package cn.evan.ssm.cityandcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 取得给定汉字串的首字母串,即声母串
 *
 * @author v_songtaohu
 */
public final class ChineseInitialsUtils {

    private static Logger logger = LoggerFactory.getLogger(ChineseInitialsUtils.class);
    private final static int[] SECTOR_POSITION_CODE = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
    public final static String[] FIRST_SECTOR_POSITION_CODE = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "w", "x", "y", "z"};

    /**
     * 取得给定汉字串的首字母串,即声母串
     *
     * @param str 给定汉字串
     * @return 声母串
     */
    public static String getAllFirstSectorPositionCode(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }

        String s = "";
        for (int i = 0; i < str.length(); i++) {
            s = s + getFirstSectorPositionCode(str.substring(i, i + 1));
        }
        return s;
    }

    /**
     * 取得给定汉字的首字母,即声母
     *
     * @param chinese 给定的汉字
     * @return 给定汉字的声母
     */
    public static String getFirstSectorPositionCode(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = conversionString(chinese, "GB2312", "ISO8859-1");

        if (chinese.length() > 1) // 判断是不是汉字
        {
            int sectorCode = (int) chinese.charAt(0); // 汉字区码
            int positionCode = (int) chinese.charAt(1); // 汉字位码
            sectorCode = sectorCode - 160;
            positionCode = positionCode - 160;
            int code = sectorCode * 100 + positionCode; // 汉字区位码
            if (code > 1600 && code < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (code >= SECTOR_POSITION_CODE[i] && code < SECTOR_POSITION_CODE[i + 1]) {
                        chinese = FIRST_SECTOR_POSITION_CODE[i];
                        break;
                    }
                }
            } else { // 非汉字字符,如图形符号或ASCII码
                chinese = conversionString(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }

    /**
     * 字符串编码转换
     *
     * @param str           要转换编码的字符串
     * @param charsetName   原来的编码
     * @param toCharsetName 转换后的编码
     * @return 经过编码转换后的字符串
     */
    private static String conversionString(String str, String charsetName, String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串编码转换异常：" + e.getMessage());
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println("获取拼音首字母：" + ChineseInitialsUtils.getFirstSectorPositionCode("深圳市"));
    }

}
