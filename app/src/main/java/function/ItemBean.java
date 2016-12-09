package function;

/**
 * Created by chenjunfan on 2016/12/9.
 */

public class ItemBean {
    private String title,num;

    public ItemBean(String title, String num) {
        this.title = title;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public String getNum() {
        return num;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
