package com.example.springbootmail;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Base64;

public class Test4 {

    public static void main(String[] args) {
        String URI = "https://s1.sapariba.cn:443/Sourcing/fileupload?realm=chint-T";
        String username ="AN01426789570-T";//用户名：  密码：ariba2019ariba
        String password = "ariba2019ariba";
        CloseableHttpClient closeableHttpClient = HttpClients.custom().build();
        String result = "";
        HttpPost httpPost = null;
        CloseableHttpResponse httpResponse = null;
        HttpEntity entity = null;
        httpPost = new HttpPost(URI);
        httpPost.setHeader("Content-Type","text/xml");
        httpPost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
        String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap-env:Envelope xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap-env:Header><m:CallerInformation xmlns:m=\"http://www.sap.com/webas/712/soap/features/runtime/metering/\"><m:Type>SA</m:Type><m:Company>0020146981</m:Company><m:Sys>D01_900</m:Sys></m:CallerInformation><wsa:To soap-env:mustUnderstand=\"1\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\">https://s1.sapariba.cn:443/Sourcing/fileupload?realm=chint-T</wsa:To><wsa:From xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address></wsa:From><wsa:ReplyTo xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address></wsa:ReplyTo><wsa:FaultTo xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><wsa:Address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</wsa:Address></wsa:FaultTo><wsa:Action soap-env:mustUnderstand=\"1\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"/><wsa:MessageID xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\">uuid:00155d00-f614-1ed9-9eed-edb4dbb6da6d</wsa:MessageID></soap-env:Header><soap-env:Body><n0:MT_UploadReq xmlns:n0=\"http://ariba.com/xi/AribaUpload\" xmlns:prx=\"urn:sap.com:proxy:D01:/1SAI/TASBF2F26173F170031754F:740\"><Header><Parameters><Parameter name=\"realm\"><value>chint-T</value></Parameter><Parameter name=\"fullload\"><value>true</value></Parameter><Parameter name=\"event\"><value>Import External System Master Data</value></Parameter><Parameter name=\"clienttype\"><value>DirectConnect</value></Parameter><Parameter name=\"clientversion\"><value>CI9-DirectConnect</value></Parameter><Parameter name=\"clientinfo\"><value>10.1.110.177-WJIAB</value></Parameter><Parameter name=\"operation\"><value>Load</value></Parameter><Parameter name=\"systemId\"><value>SAP</value></Parameter></Parameters><AttachmentFolder contentID=\"FOL33000000000004EXT39000000000138\" contentType=\"application/zip\" fileName=\"processing20190521102332_1.zip\" contentLength=\"2040\"><Content>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</Content></AttachmentFolder></Header></n0:MT_UploadReq></soap-env:Body></soap-env:Envelope>";
        System.out.println(requestXml);
        HttpEntity requestEntity = new StringEntity(requestXml, "UTF-8");
        httpPost.setEntity(requestEntity);
        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            } // 关闭连接
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //
            System.out.println(result);
        }

    }

}
