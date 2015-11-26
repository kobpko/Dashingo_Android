package basic;

import android.widget.TextView;

//import com.lidroid.xutils.util.LogUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by MSI on 2015/11/26.
 */
public class NetAide {
    public static final String CHARSET = "UTF-8";
    public final static class HttpArgs {
        private LinkedList<String> list;

        private LinkedHashMap<String, String> map;

        public HttpArgs() {
            list=new LinkedList<String>();
        }

        public HttpArgs(String action) {
            list = new LinkedList<String>();
            list.add(action);
        }

        public void add(String para) {
            list.add(para);
        }

        public void add(double para) {
            list.add(String.valueOf(para));
        }

        public void add(int para) {
            list.add(para + "");
        }

        public void add(TextView para) {
            list.add(para.getText().toString());
        }

        /**
         * 为POST增加参数
         *
         * @param key
         * @param value
         */
        public void put(String key, String value) {
            if (map == null) {
                map = new LinkedHashMap<String, String>();
            }
            map.put(key, value);
        }

        public void put(String key, boolean value) {
            if (map == null) {
                map = new LinkedHashMap<String, String>();
            }
            map.put(key, String.valueOf(value));
        }

        public void put(String key, TextView value) {
            if (map == null) {
                map = new LinkedHashMap<String, String>();
            }
            map.put(key, value.getText().toString());
        }

        public void put(String key, int value) {
            if (map == null) {
                map = new LinkedHashMap<String, String>();
            }
            put(key, value + "");
        }

        public String toPostBaseUrl() {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str + "/");
            }
            return sb.toString();
        }

        // TODO GET使用
        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str + "/");
            }

            sb.deleteCharAt(sb.length() - 1);
            if (map != null) {
                sb.append("?");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    try {
                        sb.append(entry.getKey()).append("=")
                                .append(toCharset(entry.getValue()))
                                .append("&");
                    } catch (Exception e) {
                        //LogUtils.e(e.getMessage());
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }

        private String toCharset(String value)
                throws UnsupportedEncodingException {
            return value != null ? URLEncoder.encode(value, CHARSET) : "";
        }

//        // POST使用
//        public List<NameValuePair> toNameValuePairList()
//                throws UnsupportedEncodingException {
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            if (map == null) {
//                return nvps;
//            }
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                // nvps.add(new BasicNameValuePair(entry.getKey(),
//                // toCharset(entry
//                // .getValue())));
//                nvps.add(new BasicNameValuePair(entry.getKey(), entry
//                        .getValue()));
//            }
//            return nvps;
//        }

    }
}
