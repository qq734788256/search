package wyl.search.handle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wyl.search.handle.result.MusicResult;
import wyl.search.util.CommonUtil;
import wyl.search.util.SearchMusicUtil;

import java.io.IOException;

/**
 * Created by dfsj0317 on 2017/3/1.
 */
@Controller
@RequestMapping("/msc")
public class MusicController {

    /**
     * 获取酷狗音乐音频地址
     * @param hash
     * @return
     */
    @RequestMapping("/kugou/music")
    @ResponseBody
    public MusicResult getKugouMusic(String hash){
        // 声明结果类
        MusicResult result = new MusicResult();

        // 验证参数
        if(CommonUtil.isBlank(hash)){
            result.setC(1);
            result.setM("参数缺失");
            return result;
        }

        String url = SearchMusicUtil.getKugouMusic(hash);
        result.setMusic(url);
        return result;
    }

    /**
     * 获取qq音乐音频地址
     * @param hash
     * @return
     */
    @RequestMapping("/qq/music")
    @ResponseBody
    public MusicResult getQqMusic(String hash) throws IOException {
        // 声明结果类
        MusicResult result = new MusicResult();

        // 验证参数
        if(CommonUtil.isBlank(hash)){
            result.setC(1);
            result.setM("参数缺失");
            return result;
        }

        String url = SearchMusicUtil.getQQMusic(hash);
        result.setMusic(url);
        return result;
    }

    /**
     * 获取Baidu音乐音频地址
     * @param hash
     * @return
     */
    @RequestMapping("/baidu/music")
    @ResponseBody
    public MusicResult getBaiDuMusic(String hash) throws IOException {
        // 声明结果类
        MusicResult result = new MusicResult();

        // 验证参数
        if(CommonUtil.isBlank(hash)){
            result.setC(1);
            result.setM("参数缺失");
            return result;
        }

        String url = SearchMusicUtil.getBaiDuMusic(hash);
        result.setMusic(url);
        return result;
    }
}
