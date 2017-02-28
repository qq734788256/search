package wyl.search.handle.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
public class MusicGroupVO implements Serializable {

    private static final long serialVersionUID = -3208470305944290014L;

    /**
     * 组代码
     */
    private String groupCode;
    /**
     * 音乐列表
     */
    private List<MusicVO> musics;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public List<MusicVO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicVO> musics) {
        this.musics = musics;
    }
}
