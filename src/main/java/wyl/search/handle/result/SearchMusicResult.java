package wyl.search.handle.result;

import wyl.search.handle.vo.MusicGroupVO;

import java.util.List;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
public class SearchMusicResult extends BaseResult {

    private static final long serialVersionUID = -794342326649870694L;

    private List<MusicGroupVO> musicGroup;

    public List<MusicGroupVO> getMusicGroup() {
        return musicGroup;
    }

    public void setMusicGroup(List<MusicGroupVO> musicGroup) {
        this.musicGroup = musicGroup;
    }
}
