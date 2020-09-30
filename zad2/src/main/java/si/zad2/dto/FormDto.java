package si.zad2.dto;

public class FormDto {
    private int instance;
    private String method;
    private String varchoice;
    private String valuechoice;

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVarchoice() {
        return varchoice;
    }

    public void setVarchoice(String varchoice) {
        this.varchoice = varchoice;
    }

    public String getValuechoice() {
        return valuechoice;
    }

    public void setValuechoice(String valuechoice) {
        this.valuechoice = valuechoice;
    }
}
