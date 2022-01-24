package com.heifan.demo.utils;

import com.heifan.demo.domain.vo.HitokotoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
public class SendEmail {

    static final String FromWho = "*****41@qq.com";// 发件人的邮箱
    static final String EmailPassword = "yec*******swbjch";//客户端授权码***
    static final String EmailSMTPHost = "smtp.qq.com";// 邮件SMTP协议
    static final String ToWho = "16****441@qq.com";//收件人邮箱

    static final String subject = "Hello小鱼，新的一天要开心哦^_^";
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public static void send() throws Exception {
        // =============================================
        // 1. 连接上发送邮件的服务器
        // 创建属性文件对象
        Properties pro = new Properties();
        // 需要指定邮件的服务器地址，复制一下。推荐去复制，自己写容易写错了。
        //以qq邮箱为例子
        // 邮件服务器主机
        pro.setProperty("mail.host", EmailSMTPHost);
        // 邮件传输协议
        pro.setProperty("mail.transport.protocol", "smtp");
        // 连接邮件的服务器，会话
        Session session = Session.getDefaultInstance(pro);
        // 获取到传输对象
        Transport transport = session.getTransport();
        // 校验账号和密码（该密码不是QQ号的密码），授权码
        // 是固定，必须传入授权码
        //在这里两个参数，第一个参数是你的邮箱，第二个参数是授权码
        transport.connect(FromWho, EmailPassword);

        // =============================================
        // 2. 构建出一封邮件（设置收件人、设置主题和设置正文）
        // 有邮件的类
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        //这里的参数是发件人邮箱
        message.setFrom(new InternetAddress(FromWho));

        // 设置收件人
        // Message.RecipientType.TO 表示收件人
        // Message.RecipientType.CC 表示抄送给XXX
        // Message.RecipientType.BCC 表示暗送
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(ToWho));

        // message.setRecipient(Message.RecipientType.CC,new InternetAddress(""));

        // 设置主题
        message.setSubject(subject);
        HitokotoVO hitokoto = GetHitokotoUtil.getHitokoto();

        // 设置正文
        message.setContent(hitokoto.getHitokoto() + " " + "------" + " " + hitokoto.getFrom(), "text/html;charset=UTF-8");

        // =============================================
        // 3. 发送邮件
        try {
            transport.sendMessage(message, message.getAllRecipients());
            log.info("邮件发送成功！");
        } catch (Exception e) {
            log.error("邮件发送失败！", e);
        }finally {
            // 关闭连接
            transport.close();
        }
    }

    public static void main(String[] args) throws Exception {
        send();
    }

}
