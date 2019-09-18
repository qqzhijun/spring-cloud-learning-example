package com.lidong.webiot.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BaiduApiUtils {

    public static String MAP_AK = "uazKgSemWrvwID2OCBKGyO7t9edPEPXU";

    /**
     * 逆地址解析
     */
    public static String reverse_geocoding = "http://api.map.baidu.com/reverse_geocoding/v3/?output=json&coordtype=wgs84ll&ak=" + MAP_AK;
    /**
     * 地址解析
     */
    public static String geocoding = "http://api.map.baidu.com/geocoding/v3/?output=json&ak=" + MAP_AK;

    /**
     * 将经纬度获取解析成详细地址
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @return
     */
    public static String getAddress(double lng, double lat) {
        String address = "";
        String location = lat + "," + lng;
        BufferedReader in = null;
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(reverse_geocoding + "&location=" + location);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                address = result.getJSONObject("result").getString("formatted_address");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 将地址解析成经纬度
     *
     * @param address
     *            地址，例：浙江省杭州市西湖区
     * @return 返回经纬度数据。例：{"lng":120.08899292561351,"lat":30.207036169515438}
     */
    public static JSONObject getPosition(String address) {
        BufferedReader in = null;
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(geocoding + "&address=" + address);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            String s = text.toString();
            JSONObject result = JSONObject.parseObject(s);
            if (result != null) {
                return result.getJSONObject("result").getJSONObject("location");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JSONObject position = getPosition("北京市市辖区大兴区观音寺街道办事处观音寺南里社区");
        Double lng = position.getDouble("lng");
        Double lat = position.getDouble("lat");
        System.out.println(lng);
        System.out.println(lat);
//        System.out.println(getPosition("北京市市辖区昌平区北七家镇白庙村西街海龙公寓"));
    }

}
