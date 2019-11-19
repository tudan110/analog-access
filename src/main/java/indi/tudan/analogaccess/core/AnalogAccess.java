package indi.tudan.analogaccess.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 模拟访问页面
 *
 * @author wangtan
 * @date 2019-11-19 21:02:58
 * @since 1.0
 */
@Data
@Builder
public class AnalogAccess {

    /**
     * 访问
     *
     * @date 2019-11-19 21:07:03
     */
    public void process() {

        // 根据这个匹配
        // class="article-item-box csdn-tracking-statistics"
        // https://tudan.blog.csdn.net/article/list/1?
        // https://tudan.blog.csdn.net/article/list/2?
//        List<String> urlList = CollUtil.newArrayList("https://tudan.blog.csdn.net/article/list/1?");

        List<String> urlList = CollUtil.newArrayList("https://tudan.blog.csdn.net/article/details/103079575",
                "https://tudan.blog.csdn.net/article/details/103064258",
                "https://tudan.blog.csdn.net/article/details/103027650",
                "https://tudan.blog.csdn.net/article/details/102882755",
                "https://tudan.blog.csdn.net/article/details/102859236",
                "https://tudan.blog.csdn.net/article/details/102842328",
                "https://tudan.blog.csdn.net/article/details/102619001",
                "https://tudan.blog.csdn.net/article/details/102556410",
                "https://tudan.blog.csdn.net/article/details/102555391",
                "https://tudan.blog.csdn.net/article/details/102550184",
                "https://tudan.blog.csdn.net/article/details/102504615",
                "https://tudan.blog.csdn.net/article/details/102383306",
                "https://tudan.blog.csdn.net/article/details/101015061",
                "https://tudan.blog.csdn.net/article/details/101013279",
                "https://tudan.blog.csdn.net/article/details/100983953",
                "https://tudan.blog.csdn.net/article/details/100983841",
                "https://tudan.blog.csdn.net/article/details/100983445",
                "https://tudan.blog.csdn.net/article/details/100983063",
                "https://tudan.blog.csdn.net/article/details/100880730",
                "https://tudan.blog.csdn.net/article/details/100739994",
                "https://tudan.blog.csdn.net/article/details/100214799",
                "https://tudan.blog.csdn.net/article/details/98060926"
        );

        urlList.forEach(HttpUtil::get);
        /*urlList.forEach(p -> {
            Console.log(HttpUtil.get(p));
        });*/
    }
}
