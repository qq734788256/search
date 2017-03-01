package wyl.search.handle.result;

import org.jsoup.Connection;

/**
 * Created by dfsj0317 on 2017/3/1.
 */
public class MusicResult extends BaseResult {

    private static final long serialVersionUID = -5460766374843156225L;
    /**
     * 音频地址
     */
    private String music;

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
