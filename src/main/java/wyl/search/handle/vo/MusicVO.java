package wyl.search.handle.vo;

import java.io.Serializable;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
public class MusicVO implements Serializable {

    private static final long serialVersionUID = 1643935515390665142L;

    /**
     * 音乐名称
     */
    private String musicName;
    /**
     * 歌手
     */
    private String singer;
    /**
     * 平台代码
     */
    private String platCode;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }
}
