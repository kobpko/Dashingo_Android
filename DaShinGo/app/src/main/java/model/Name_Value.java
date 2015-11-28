package model;

import org.apache.http.NameValuePair;

/**
 * Created by MSI on 2015/11/27.
 */
public class Name_Value implements NameValuePair {
    private String name;
    private String value;

    public Name_Value(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override

    public String getName() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
