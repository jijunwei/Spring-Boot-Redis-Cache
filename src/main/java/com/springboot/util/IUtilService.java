package com.springboot.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public interface IUtilService {

    String longList2Str(List<Long> list2beConverted, String seperator);

    String longList2Str(List<Long> list2beConverted);

    List<Long> str2LongList(String str2beConverted);

    List<Long> str2LongList(String str2beConverted, String seperator);

    String number2CNMoney(BigDecimal numberOfMoney);

    String safeMap2Str(Map<String, String> info);

    String safeObjectMap2Str(Map<String, Object> info);
}
