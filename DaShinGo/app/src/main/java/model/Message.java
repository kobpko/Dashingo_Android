package model;

import java.lang.Boolean; /**
 * Created by MSI on 2015/11/25.
 */
public class Message {

    private Boolean result;

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
