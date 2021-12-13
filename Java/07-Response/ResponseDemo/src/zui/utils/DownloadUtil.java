package zui.utils;

import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class DownloadUtil {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if(agent.contains("MSIE")) {
            //IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", "");
        }else if(agent.contains("Firefox")) {
            // 火狐浏览器
            Base64.Encoder encoder = Base64.getEncoder();
            filename = "?utf-8?B?" + encoder.encodeToString(filename.getBytes(StandardCharsets.UTF_8)) + "?=";
        }else {
            //其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


}
