package wyl.search.handle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wyl.search.util.SearchMusicUtil;
import wyl.search.handle.result.SearchMusicResult;
import wyl.search.handle.vo.MusicGroupVO;
import wyl.search.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping("/msc")
    @ResponseBody
    public SearchMusicResult searchMusic(String plat,String key,String page,String pageSize){
        // 声明返回的结果对象
        SearchMusicResult result = new SearchMusicResult();

        // 判断搜索的关键词
        if(CommonUtil.isBlank(key)){
            result.setC(0);
            result.setM("成功");
            return result;
        }
        // 判断页码
        if(CommonUtil.isBlank(page) || CommonUtil.isNumber(page)){
            page = "1";
        }
        if(CommonUtil.isBlank(pageSize) || CommonUtil.isNumber(pageSize)){
            pageSize = "20";
        }

        List<MusicGroupVO> musicGroups = new ArrayList<>();
        MusicGroupVO musicGroup = null;
        // 判断平台
        if(CommonUtil.isBlank(plat)){
            // 查询所有平台（页码统一为1，每页数量为20）
        } else {
            switch (plat) {
                case "kugou":
                    System.out.println("kugou");
                    musicGroup = SearchMusicUtil.getKugouMusics(key, page, pageSize);
                    musicGroups.add(musicGroup);
                    break;
                case "qq":
                    System.out.println("qq");
                    musicGroup = SearchMusicUtil.getQQMusics(key, page, pageSize);
                    musicGroups.add(musicGroup);
                    break;
                case "xiami":
                    System.out.println("xiami");
                    musicGroup = SearchMusicUtil.getXiaMiMusics(key, page, pageSize);
                    musicGroups.add(musicGroup);
                    break;
                case "baidu":
                    System.out.println("baidu");
                    musicGroup = SearchMusicUtil.getBaiDuMusics(key, page, pageSize);
                    musicGroups.add(musicGroup);
                    break;
                default:
                    result.setC(0);
                    result.setM("成功");
                    break;
            }
        }
        result.setC(0);
        result.setM("成功");
        result.setMusicGroup(musicGroups);
        return result;
    }
}
