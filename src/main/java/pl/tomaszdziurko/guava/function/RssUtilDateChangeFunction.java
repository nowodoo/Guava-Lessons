package pl.tomaszdziurko.guava.function;

import com.google.common.base.Function;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by maluyong on 2016/12/28.
 */
public class RssUtilDateChangeFunction implements Function<Map<String, Object>, Map<String, Object>> {

    //清除多余的key
    final static String[] reduntantKey = {"timestamp", "resType", "departsDates", "deadlineDay", "deadlineTime", "prePayPrice", "isDefault"};

    @Nullable
    public Map<String, Object> apply(@Nullable Map<String, Object> origin) {
        //去掉多余的属性
        for (String key : reduntantKey) {
            if (origin.containsKey(key)) {
                origin.remove(key);
            }
        }

        //转换时间
        if (origin.containsKey("departsDate")) {
            String date = (String) origin.get("departsDate");
            String formattedDate = date.split("\\s+")[0];
            origin.put("departsDate", formattedDate);
        }

        //返回转换好的属性
        return origin;
    }
}
