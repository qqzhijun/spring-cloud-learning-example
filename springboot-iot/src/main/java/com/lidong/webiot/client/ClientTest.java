package com.lidong.webiot.client;

import com.lidong.webiot.utils.CoderUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {


    public static void main(String[] args) {
        sendClientRequest();
//        String s = CoderUtils.unicodeToString("\\u5317\\u4eac\\u5e02\\u6d77\\u6dc0\\u533a\\u4e2d\\u5173\\u6751\\u5927\\u8857\\u0032\\u0037\\u53f7\\u0031\\u0031\\u0030\\u0031\\u002d\\u0030\\u0038\\u5ba4");
//        System.out.println(s);
    }

    private static void sendClientRequest() {
        try {
//            Socket socket = new Socket("127.0.0.1", 7777);
//            OutputStream outputStream = socket.getOutputStream();
//            PrintWriter printWriter = new PrintWriter(outputStream);
//            printWriter.write("@B#@|01|001|111112222233333|8888888888888888|1|55|20160715150323|125.48276|37.615124|1|@E#@");
//            printWriter.flush();
//            socket.shutdownOutput();
//            socket.close();
            String data = "@B#@|01|001|111112222233333|8888888888888888|1|55|20160715150323|125.48276|37.615124|1|@E#@";
            NioClient nc = new NioClient("127.0.0.1", 7777);
            new Thread(nc).start();
            Thread.sleep(4000);
            nc.writeAndFlush(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
