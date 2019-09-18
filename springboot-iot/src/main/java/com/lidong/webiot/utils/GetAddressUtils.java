package com.lidong.webiot.utils;

import com.alibaba.fastjson.JSONObject;

public class GetAddressUtils {

    public static void main(String[] args) {
        String ff = "";
        String[] arr = getAddress(ff);

    }

    public static String[] getAddress(String ff) {
        String[] arr = new String[3];
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
        arr[0] = address;
        arr[1] = lon;
        arr[2] = lat;
        return arr;
    }
}
