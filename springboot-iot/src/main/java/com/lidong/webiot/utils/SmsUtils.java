package com.lidong.webiot.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SmsUtils {

    public static final String TEMPLATE_CODE_SYSTEM_NOTICE = "SMS_135765346";//系统错误通知
    public static final String ERROR_MOBILE = "13910590486";//系统错误通知
    public static final String SIGN_NAME_YYB = "执壶健康";
    //产品名称:云通信短信API产品,开发者无需替换
    public static final String PRODUCTOR = "Dysmsapi";
    //产品域名,开发者无需替换
    public  static final String DOMAIN = "dysmsapi.aliyuncs.com";
    /**
     * AK—ID
     */
    public static final String ACCESS_KEY_ID = "LTAI4mEJm05nim0h";
    /**
     * 短信秘钥
     */
    public static final String ACCESS_KEY_SECRET = "hrwGdygB8oFvGb8WT1MYJOZLJrmH68";

    public static SendSmsResponse sendMsmSystemNotice(String mobile, String errorType, String errorInfo, String templateCode, String signName)throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCTOR,  DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"error_type\":\""+errorType+"\",\"error_info\":\""+errorInfo+"\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    public static void main(String[] args) {
        try {
            SendSmsResponse systemNotice = sendMsmSystemNotice(ERROR_MOBILE, "位置服务", "北京市昌平区北七家镇教师干部住宅楼3号楼", TEMPLATE_CODE_SYSTEM_NOTICE, SIGN_NAME_YYB);
            systemNotice.getCode();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
