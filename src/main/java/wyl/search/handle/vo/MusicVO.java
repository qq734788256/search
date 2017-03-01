package wyl.search.handle.vo;

import java.io.Serializable;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
public class MusicVO implements Serializable {

    private static final long serialVersionUID = 1643935515390665142L;

    /**
     * 文件名称
     */
    private String fileName;
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
    /**
     * 所属专辑
     */
    private String albumname;
    /**
     * hash
     */
    private String hash;
    /**
     * 时长
     */
    private int interval;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
