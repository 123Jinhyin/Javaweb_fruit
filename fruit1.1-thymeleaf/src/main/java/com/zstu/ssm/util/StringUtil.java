package com.zstu.ssm.util;

import javax.swing.text.StyledEditorKit;

/**
 * ClassName: StringUtil
 * Package: com.zstu.ssm.util
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/4 - 16:28
 * @Version: v1.0
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return  str==null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
