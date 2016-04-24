package com.lge.keyboard.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import static javax.mail.Message.RecipientType.TO;

public class GMail implements IEmail {
    private static final String PLAIN = "text/plain";
    private static final String HTML = "text/html";

    private static final String HOST = "smtp.gmail.com";
    private static final String USER = "lge.keyboard@gmail.com";
    private static final String PASSWORD = "lgekeyboard";

    private InternetAddress mSender = null;
    private List<InternetAddress> mDestinations = new ArrayList<InternetAddress>();
    private String mSubject;
    private String mBody;
    private String mContentType;

    @Override
    public void setSender(String sender) {
        try {
            mSender = new InternetAddress(sender);
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDestinations(Collection<String> destinations) {
        for (String destination : destinations) {
            try {
                mDestinations.add(new InternetAddress(destination));
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setHtml(boolean isHtml) {
        mContentType = isHtml ? HTML : PLAIN;
    }

    @Override
    public void setSubject(String subject) {
        mSubject = subject;
    }

    @Override
    public void setBody(String body) {
        mBody = body;
    }

    @Override
    public void send() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);

        if (mSender == null) {
            throw new RuntimeException("Sender is empty");
        }

        if (mDestinations == null || mDestinations.size() <= 0) {
            throw new RuntimeException("Destinations are empty");
        }

        Session session = Session.getDefaultInstance(props, null);
        Message msg = composeMessage(session);

        SMTPTransport t = null;
        try {
            t = (SMTPTransport) session.getTransport("smtp");
            t.connect(HOST, USER, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            close(t);
        }
    }

    private Message composeMessage(Session s) {
        Message msg = new MimeMessage(s);
        try {
            msg.setFrom(mSender);
            for (InternetAddress destination : mDestinations) {
                msg.addRecipient(TO, destination);
            }
            msg.setSubject(mSubject);
            msg.setContent(mBody, mContentType);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private void close(SMTPTransport t) {
        if (t != null) {
            try {
                t.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
