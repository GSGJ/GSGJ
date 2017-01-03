package function;

/**
 * Created by chenjunfan on 2017/1/2.
 */

public class RmItemBean {
    private String title;
    private String num,remennum;

    public RmItemBean(String title, String num, String remennum) {
        this.title = title;
        this.num = num;
        this.remennum = remennum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRemennum() {
        return remennum;
    }

    public void setRemennum(String remennum) {
        this.remennum = remennum;
    }
}
