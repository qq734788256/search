package wyl.search.handle.result;

import java.io.Serializable;

/**
 * Created by dfsj0317 on 2017/2/28.
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 6356008170447902137L;

    /**
     * 状态
     */
    private int c;
    /**
     * 信息
     */
    private String m;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
