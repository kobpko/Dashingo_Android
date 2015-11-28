package model;

import java.lang.Boolean; /**
 * Created by MSI on 2015/11/25.
 */
public class Message {

    private Boolean result;

    public Message() {
    }

    public Message(Boolean result, Object obj) {
        this.result = result;
        this.obj = obj;
    }

    private Object obj;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Message(Boolean result) {
        this.result = result;
    }
}
