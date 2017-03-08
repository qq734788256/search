package wyl.search.util;

import com.alibaba.fastjson.JSON;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wyl.search.handle.vo.MusicGroupVO;
import wyl.search.handle.vo.MusicVO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by dfsj0317 on 2017/3/1.
 */
public class SearchMusicUtil {
    // 酷我音乐盒   http://search2013.kuwo.cn/r.s?all=%E5%90%AF%E7%A8%8B&ft=music&client=kt&cluster=0&pn=0&rn=50&rformat=json&encoding=utf8&r=1488345161002

    /**
     * 酷狗音乐
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    private static String getSearchKugouUrl(String key,int pageNo,int pageSize) {
        try {
            return "http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword="+ URLEncoder.encode(key,"UTF-8")+"&page="+pageNo+"&pagesize="+pageSize+"&showtype=1";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword="+key+"&page="+pageNo+"&pagesize="+pageSize+"&showtype=1";
    }

    private static String getMusicKugouUrl(String hash) {
        return "http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash="+hash+"&from=mkugou";
    }

    /**
     * QQ音乐
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    private static String getSearchQQMusicUrl(String key,int pageNo,int pageSize) {
//        return "https://c.y.qq.com/soso/fcgi-bin/search_for_qq_cp?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=h5&needNewCode=1&w="+key+"&zhidaqu=1&catZhida=1&t=0&flag=1&ie=utf-8&sem=1&aggr=0&perpage=20&n="+pageSize+"&p="+pageNo+"&remoteplace=txt.mqq.all&_="+SysTimeUtil.getSystemTime();
        return "http://c.y.qq.com/soso/fcgi-bin/search_for_qq_cp?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8&notice=0&platform=h5&needNewCode=1&w="+key+"&zhidaqu=1&catZhida=1&t=0&flag=1&ie=utf-8&sem=1&aggr=0&perpage=20&n="+pageSize+"&p="+pageNo+"&remoteplace=txt.mqq.all&_="+new Date().getTime();
    }

    private static String getMusicQQUrl(String songmid) {
        return "http://c.y.qq.com/v8/playsong.html?songmid="+songmid+"&ADTAG=myqq&from=myqq&channel=10007100";
    }

    /**
     * 虾米音乐
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    private static String getSearchXiaMiUrl(String key,int pageNo,int pageSize) {
        try {
            return "https://api.xiami.com/web?v=2.0&app_key=1&key="+ URLEncoder.encode(key,"UTF-8")+"&page="+pageNo+"&limit="+pageSize+"&_ksTS="+SysTimeUtil.getSystemTime()+"145_62&r=search/songs";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "https://api.xiami.com/web?v=2.0&app_key=1&key="+key+"&page="+pageNo+"&limit="+pageSize+"&_ksTS="+SysTimeUtil.getSystemTime()+"145_62&r=search/songs";
    }

//    private static String getMusicXiaMiUrl(String hash) {
//        return "http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash="+hash+"&from=mkugou";
//    }


    /**
     * 百度音乐
     * @param key
     * @param pageNo
     * @param pageSize
     * @return
     */
    private static String getSearchBaiDuUrl(String key,int pageNo,int pageSize) {
        return "http://music.baidu.com/loadmore/search/?query="+key+"&page_size="+pageSize+"&page_no="+pageNo+"&method=baidu.ting.search.common&offset=0";
    }

    private static String getMusicBaiDuUrl(String hash) {
        return "http://musicapi.qianqian.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.playAAC&format=json&songid="+hash+"&s_protocol=&_="+new Date().getTime();
    }

    /**
     * 获取酷狗音乐的作品
     * @param key
     * @param page
     * @param pageSize
     * @return
     */
    public static MusicGroupVO getKugouMusics(String key,String page,String pageSize){
        MusicGroupVO musicGroup = null;
        String json = HttpClientUtil.doGet(SearchMusicUtil.getSearchKugouUrl(key,Integer.parseInt(page),Integer.parseInt(pageSize)));
        HashMap<String,Object> kugouInfo = JSON.parseObject(json, HashMap.class);
        if(Integer.parseInt(kugouInfo.get("errcode").toString()) == 0 && Integer.parseInt(kugouInfo.get("status").toString()) == 1) {
            // 获取data数据
            Map<String, Object> kugouData = (Map<String, Object>) kugouInfo.get("data");
            List<Map<String, Object>> musics = (List<Map<String, Object>>) kugouData.get("info");
            MusicVO music = null;
            if (musics.size() > 0) {
                // 循环取数据
                musicGroup = new MusicGroupVO();
                musicGroup.setGroupCode("kugou");
                List<MusicVO> musicList = new ArrayList<>();
                for (Map<String, Object> musicInfo : musics) {
                    music = new MusicVO();
                    music.setFileName(musicInfo.get("filename").toString());
                    music.setMusicName(musicInfo.get("songname").toString());
                    music.setPlatCode("kugou");
                    music.setSinger(musicInfo.get("singername").toString());
                    music.setHash(musicInfo.get("hash").toString());
                    music.setAlbumname(musicInfo.get("album_name").toString());
                    music.setInterval(Integer.parseInt(musicInfo.get("duration").toString()));
                    musicList.add(music);
                }
                musicGroup.setMusics(musicList);
            }
        }
        return musicGroup;
    }

    /**
     * 获取酷狗音乐的地址
     * @param hash
     * @return
     */
    public static String getKugouMusic(String hash){
        String json = HttpClientUtil.doGet(getMusicKugouUrl(hash));
        HashMap<String,Object> music = JSON.parseObject(json, HashMap.class);
        return music.get("url").toString();
    }

    /**
     * 获取QQ音乐的作品
     * @param key
     * @param page
     * @param pageSize
     * @return
     */
    public static MusicGroupVO getQQMusics(String key,String page,String pageSize){
        MusicGroupVO musicGroup = null;
        String json = HttpClientUtil.doGet(SearchMusicUtil.getSearchQQMusicUrl(key,Integer.parseInt(page),Integer.parseInt(pageSize)));
        HashMap<String,Object> qqInfo = JSON.parseObject(json, HashMap.class);
        if(Integer.parseInt(qqInfo.get("code").toString()) == 0) {
            // 获取data数据
            Map<String, Object> qqData = (Map<String, Object>) qqInfo.get("data");
            Map<String, Object> qqSong = (Map<String, Object>) qqData.get("song");
            List<Map<String, Object>> musics = (List<Map<String, Object>>) qqSong.get("list");
            MusicVO music = null;
            if (musics.size() > 0) {
                // 循环取数据
                musicGroup = new MusicGroupVO();
                musicGroup.setGroupCode("qq");
                List<MusicVO> musicList = new ArrayList<>();
                for (Map<String, Object> musicInfo : musics) {
                    music = new MusicVO();
                    music.setFileName(musicInfo.get("songname").toString());
                    music.setMusicName(musicInfo.get("songname").toString());
                    music.setPlatCode("qq");
                    List<Map<String,Object>> singersInfo = (List<Map<String,Object>>) musicInfo.get("singer");
                    if(singersInfo.size() > 0){
                        StringBuffer sbuffer = new StringBuffer();
                        for(Map<String,Object> singerInfo : singersInfo){
                            sbuffer.append(singerInfo.get("name")).append(",");
                        }
                        music.setSinger(sbuffer.toString().substring(0,sbuffer.toString().length()-1));
                    }
                    music.setAlbumname(musicInfo.get("albumname").toString());
                    music.setHash(musicInfo.get("songmid").toString());
                    music.setInterval(Integer.parseInt(musicInfo.get("interval").toString()));
                    musicList.add(music);
                }
                musicGroup.setMusics(musicList);
            }
        }
        return musicGroup;
    }

    /**
     * 获取QQ音乐的地址
     * @param songmid
     * @return
     */
    public static String getQQMusic(String songmid) throws IOException {
        Connection connection= Jsoup.connect(getMusicQQUrl(songmid));
        Document doc = connection.userAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1").ignoreContentType(true).get();
        String scriptInfo = doc.getElementsByTag("body").get(0).getElementsByTag("script").get(3).html();
        String[] infos = scriptInfo.split("\"m4aUrl\":\"");
        if(infos.length < 1){
            return "";
        }
        String[] urls = infos[1].split("\",\"pic\"");
        if(urls.length < 1){
            return "";
        }
        return urls[0];
    }

    /**
     * 获取虾米音乐的作品
     * @param key
     * @param page
     * @param pageSize
     * @return
     */
    public static MusicGroupVO getXiaMiMusics(String key,String page,String pageSize){
        MusicGroupVO musicGroup = null;
        String json = HttpClientForXMUtil.doGet(SearchMusicUtil.getSearchXiaMiUrl(key,Integer.parseInt(page),Integer.parseInt(pageSize)));
        HashMap<String,Object> xiaMiInfo = JSON.parseObject(json, HashMap.class);
        if(Integer.parseInt(xiaMiInfo.get("state").toString()) == 0) {
            // 获取data数据
            Map<String, Object> xiaMiData = (Map<String, Object>) xiaMiInfo.get("data");
            List<Map<String, Object>> musics = (List<Map<String, Object>>) xiaMiData.get("songs");
            MusicVO music = null;
            if (musics.size() > 0) {
                // 循环取数据
                musicGroup = new MusicGroupVO();
                musicGroup.setGroupCode("xiami");
                List<MusicVO> musicList = new ArrayList<>();
                for (Map<String, Object> musicInfo : musics) {
                    music = new MusicVO();
                    music.setFileName(musicInfo.get("song_name").toString());
                    music.setMusicName(musicInfo.get("song_name").toString());
                    music.setPlatCode("xiami");
                    music.setSinger(musicInfo.get("artist_name").toString());
                    music.setAlbumname(musicInfo.get("album_name").toString());
                    String path = musicInfo.get("listen_file").toString();
//                    music.setHash(MD5.parse(path));
                    music.setHash(path);
                    music.setInterval(-1);
                    musicList.add(music);
                }
                musicGroup.setMusics(musicList);
            }
        }
        return musicGroup;
    }
    /**
     * 获取BaiDu音乐的作品
     * @param key
     * @param page
     * @param pageSize
     * @return
     */
    public static MusicGroupVO getBaiDuMusics(String key,String page,String pageSize){
        MusicGroupVO musicGroup = null;
        String json = HttpClientUtil.doGet(SearchMusicUtil.getSearchBaiDuUrl(key,Integer.parseInt(page),Integer.parseInt(pageSize)));
        HashMap<String,Object> baiDuInfo = JSON.parseObject(json, HashMap.class);
        // 获取data数据
        String baiDuData = baiDuInfo.get("repeator").toString();
        Document doc = Jsoup.parse(baiDuData);
        Elements elements = doc.getElementsByTag("li");

        MusicVO music = null;
        if (elements.size() > 0) {
            // 循环取数据
            musicGroup = new MusicGroupVO();
            musicGroup.setGroupCode("baidu");
            List<MusicVO> musicList = new ArrayList<>();
            for (Element element : elements) {
                music = new MusicVO();
                if(element.getElementsByTag("em").size() > 0){
                    music.setFileName(element.getElementsByTag("em").get(0).html());
                }
                if(element.getElementsByClass("txt").size() > 0){
                    music.setSinger(element.getElementsByClass("txt").get(0).html());
                }
                if(element.getElementsByTag("em").size() > 0){
                    music.setMusicName(element.getElementsByTag("em").get(0).html());
                }
                music.setPlatCode("baidu");
                music.setAlbumname("");
                music.setHash(element.attr("data-sid"));
                music.setInterval(-1);
                musicList.add(music);
            }
            musicGroup.setMusics(musicList);
        }
        return musicGroup;
    }
    /**
     * 获取baidu音乐的地址
     * @param songmid
     * @return
     */
    public static String getBaiDuMusic(String songmid) throws IOException {
        String json = HttpClientUtil.doGet(getMusicBaiDuUrl(songmid));
        Map<String,Object> baiDuInfo = JSON.parseObject(json);
        if(Integer.parseInt(baiDuInfo.get("error_code").toString())==22000){
            Map<String,Object> musicInfo = (Map<String, Object>) baiDuInfo.get("bitrate");
            return musicInfo.get("file_link").toString();
        }
        return "";
    }
    public static void main(String[] args){
        getBaiDuMusics("演员","1","20");
    }
}
