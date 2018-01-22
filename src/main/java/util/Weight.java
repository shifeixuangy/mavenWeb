package util;

/**
 * Created by shifeixuan on 2018/1/22.
 */
public class Weight {
    private String _class;
    private String dtime;
    private String  rtuKey;
    private String msg;
    private String ip;

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getRtuKey() {
        return rtuKey;
    }

    public void setRtuKey(String rtuKey) {
        this.rtuKey = rtuKey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
