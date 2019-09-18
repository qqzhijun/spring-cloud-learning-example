package com.lidong.webiot.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CellocationUtils  {

//    http://api.cellocation.com:81/cell/?mcc=460&mnc=1&lac=4301&ci=20986&output=xml
//    http://api.cellocation.com:81/wifi/?mac=00:87:36:05:5d:ea&output=xml




    /**
     * 将经纬度获取解析成详细地址
     *
     * @param mcc
     *            经度
     * @param mnc
     *            纬度
     * @return
     */
    public static JSONObject getAddress(String mcc, String mnc,String lac,String ci) {
        try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String url = "http://api.cellocation.com:81/cell/?mcc="+mcc+"&mnc="+mnc+"&lac="+lac+"&ci="+ci+"&output=json";
                // 创建httpget.
                HttpGet httpget = new HttpGet(url);
                System.out.println("executing request " + httpget.getURI());
                // 执行get请求.
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    // 打印响应状态
                    System.out.println(response.getStatusLine());
                    if (entity != null) {
                        // 打印响应内容长度
                        System.out.println("Response content length: " + entity.getContentLength());
                        // 打印响应内容
                        String text = EntityUtils.toString(entity);
                        System.out.println("Response content: " + text);
                        JSONObject result = JSONObject.parseObject(text.toString());
                        if (result != null && result.getIntValue("errcode") == 0) {
//                        {"errcode":0, "lat":"40.117863", "lon":"116.415039", "radius":"159", "address":"北京市昌平区北七家镇教师干部住宅楼2号楼;瑶光路与天机街路口西南295米"}
                            return result;
                        }
                    }
                } finally {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }



    /**
     * 将经纬度获取解析成详细地址
     *
     * @param mac
     *@B#@|01|001|866058040002405|8888888888888888|0|099|20190812062009|88:25:93:e8:56:9e,-49&94:d9:b3:64:5a:2d,-51&9c:a6:15:b6:3f:75,-63&30:fc:68:6e:64:10,-64|5012090,41194,1,460,90|5012090,4153,1,460,90|0,0,0,0,0|0,0,0,0,0|3|@E#@
     * @return
     */
    public static JSONObject getAddress(String mac) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = "http://api.cellocation.com:81/wifi/?mac="+mac+"&output=json";
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                     String text = EntityUtils.toString(entity);
                    System.out.println("Response content: " + text);
                    JSONObject result = JSONObject.parseObject(text.toString());
                    if (result != null && result.getIntValue("errcode") == 0) {
//                        {"errcode":0, "lat":"40.117863", "lon":"116.415039", "radius":"159", "address":"北京市昌平区北七家镇教师干部住宅楼2号楼;瑶光路与天机街路口西南295米"}
                       return result;
                    }
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String mac111 = "88:25:93:e8:56:9e";
        JSONObject address111 = getAddress(mac111);
        System.out.println(address111);
//        String ff = "@B#@|01|001|866058040002405|8888888888888888|1|032|20190802033850|5012090,41194,1,460,101|5012090,4153,1,460,101|0,0,0,0,0|0,0,0,0,0|2|@E#@";

        //gps位置数据
//@B#@|01|001|111112222233333|8888888888888888|1|055|20160715150323|125.48276|37.615124|1|@E#@
//基站定位
//@B#@|01|001|866058040002405|8888888888888888|1|032|20190802033850|5012090,41194,1,460,101|5012090,4153,1,460,101|0,0,0,0,0|0,0,0,0,0|2|@E#@
//wifi 定位
String ff = "@B#@|01|001|866058040002405|8888888888888888|1|031|20190802034024|94:d9:b3:64:5a:2d,-43&88:25:93:e8:56:9e,-57&9c:a6:15:b6:3f:75,-65&ac:a2:13:08:2f:25,-70|5012090,41194,1,460,93|5012090,4153,1,460,93|0,0,0,0,0|0,0,0,0,0|3|@E#@";

        String address = "";
        String lon = "";
        String lat = "";
        String[] split = ff.split("\\|");
        String type = split[split.length - 2];
        if ("2".equals(type)) {
            String s8 = split[8];
            String s9 = split[9];
            String s10 = split[10];
            String s11 = split[11];
            String[] split1 = s8.split(",");
            String mcc = split1[3];
            String mnc = split1[2];
            String lac = split1[1];
            String ci = split1[0];
            JSONObject address1 = CellocationUtils.getAddress(mcc, mnc, lac, ci);

            if (address1.get("errcode").equals(0)) {
                address = address1.getString("address");
                lon = address1.getString("lon");
                lat = address1.getString("lat");
            }
        } else if ("1".equals(type)) {
            lon = split[8];
            lat = split[9];
            address = BaiduApiUtils.getAddress(Double.parseDouble(lon), Double.parseDouble(lat));
        } else if ("3".equals(type)) {
            String s8 = split[8];
            String[] split1 = s8.split(",");
            String mac = split1[0];
            JSONObject address1 = CellocationUtils.getAddress(mac);
            if (address1.get("errcode").equals(0)) {
                address = address1.getString("address");
                lon = address1.getString("lon");
                lat = address1.getString("lat");
            }
        }
//        String mcc ="460";
//        String mnc = "1";
//        String lac = "4153";
//        String ci = "5012090";
//        JSONObject address1 = getAddress(mcc, mnc, lac, ci);
//        System.out.println(address1);
//
//
//        String mcc1 ="460";
//        String mnc1 = "1";
//        String lac1 = "41194";
//        String ci1 = "5012090";
//        JSONObject address2 = getAddress(mcc1, mnc1, lac1, ci1);
//        System.out.println(address2);
    }
}
