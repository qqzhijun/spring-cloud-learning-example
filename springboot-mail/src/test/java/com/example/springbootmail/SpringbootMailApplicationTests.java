package com.example.springbootmail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendAttachmentsMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("1561281670@qq.com");
		helper.setTo("lidong1665@163.com");
		helper.setSubject("oss通知");
		helper.setText("oss STS获取成功");
		mailSender.send(mimeMessage);
	}


}
