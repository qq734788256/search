package wyl.search.handle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wyl.search.handle.result.SearchMusicResult;
import wyl.search.util.CommonUtil;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    /**
     * 搜索音乐
     * @param plat 所属平台
     * @param key 搜索关键字
     * @return
     */
    @RequestMapping("/msc.smc")
    @ResponseBody
    public Object searchMusic(String plat,String key){
        System.out.println("aaa");
        SearchMusicResult result = new SearchMusicResult();
        if(CommonUtil.isBlank(key)){
            result.setC(0);
            result.setM("成功");
            return result;
        }
        return null;
    }
}
