package com.dm.study.cloud.util;

import com.dm.study.cloud.po.EmailInfo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月16日 10:27</p>
 * <p>类全名：com.dm.study.cloud.util.MailUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class EmailUtil {
	public static void main(String[] args) {
		// 收件人邮箱（替换为自己知道的有效邮箱）
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setTo("lzhsmart@163.com");
		emailInfo.setSubject("测试邮件");
		emailInfo.setContent("测试邮件内容");
		sendEmail(emailInfo);
	}

	public static void sendEmail(EmailInfo emailInfo) {
		// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
		String from = "lzhknight@163.com";
		// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
		String password = "OILHRTJNSGQETYVF";
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();
		// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.transport.protocol", "smtp");
		// 发件人的邮箱的 SMTP 服务器地址，163邮箱是smtp.163.com，qq邮箱是smtp.qq.com。
		props.setProperty("mail.smtp.host", "smtp.163.com");
		// 需要请求认证
		props.setProperty("mail.smtp.auth", "true");
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		try {
			// 3. 创建一封邮件即邮件对象
			MimeMessage message = createMimeMessageFile(session, from, emailInfo);
			// 4. 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一封包含附件的邮件
	 * @param session 和服务器交互的会话对象
	 * @param sendMail 发件人邮箱
	 * @param emailInfo 邮件信息
	 * @return
	 * @throws Exception
	 */
	private static MimeMessage createMimeMessageFile(Session session, String sendMail, EmailInfo emailInfo) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(sendMail));
		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailInfo.getTo()));
		// 4. Subject: 邮件主题
		message.setSubject(emailInfo.getSubject(), "UTF-8");
		/*
		//邮件内容
		//准备图片数据
		MimeBodyPart image = new MimeBodyPart();
		DataHandler handler = new DataHandler(new FileDataSource("C:\\Users\\86156\\Desktop\\FLAMING MOUNTAIN.JPG"));
		image.setDataHandler(handler);
		image.setContentID("test.png"); //设置图片id
		//准备文本
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("这是一段文本<img src='cid:test.png'>", "text/html;charset=utf-8");
		//附件
		MimeBodyPart appendix = new MimeBodyPart();
		appendix.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\86156\\Desktop\\新建文本文档.txt")));
		appendix.setFileName("test.txt");
		//拼装邮件正文
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(image);
		mimeMultipart.addBodyPart(text);
		mimeMultipart.setSubType("related");//文本和图片内嵌成功
		//将拼装好的正文内容设置为主体
		MimeBodyPart contentText = new MimeBodyPart();
		contentText.setContent(mimeMultipart);
		//拼接附件
		MimeMultipart allFile = new MimeMultipart();
		allFile.addBodyPart(appendix);//附件
		allFile.addBodyPart(contentText);//正文
		allFile.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed
		*/
		// 5. Content: 邮件正文（可以使用html标签）
		// 放到Message消息中
		message.setContent(emailInfo.getContent(),"text/html; charset=GBK");
		// 6. 设置发件时间
		message.setSentDate(new Date());
		// 7. 保存设置
		message.saveChanges();
		return message;
	}
}
