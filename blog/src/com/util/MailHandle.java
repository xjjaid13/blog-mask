package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream; 
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件发送接收方法
 * @author cloud
 * */
public class MailHandle {

	/** 邮件发送地址 */
	private String sendaddress = "";
	
	/** 用户名 */
	private String username = "sowwa";
	
	/** 密码 */
	private String password = "0p9o8i";
	
	/** 发送内容 */
	private String sendcontent = "";
	
	/** 发送标题 */
	private String sendtitle = "";
	
	/** 发送附件名 */
	private String sendattachname = "";
	
	/** 发送附件 */
	private String sendattach = "";
	
	/** 服务器ip */
	private String host = "112.125.54.6";
	
	/** 服务器dns */
	private String dns = "sowwa.com";
	
	private String saveAttchPath = "";
	
	StringBuffer bodytext = new StringBuffer();
	
	/** 发送邮件 */
	public void sendMail() throws AddressException, MessagingException{
		String from = username+"@"+dns;
		String to = sendaddress;
		String subject = sendtitle;
		String content = sendcontent;
		Properties props = new Properties();
		Session session = Session.getInstance(props);
		session.setDebug(false);
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setSentDate(new Date());
		
		Multipart mp = new MimeMultipart();
		
		BodyPart mdp = new MimeBodyPart();
		
		mdp.setContent(content,"text/html;charset=GBK");
		
		mp.addBodyPart(mdp);
		
		if(!sendattach.equals("")){
			mdp = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(sendattach);
			DataHandler dh = new DataHandler(fds);
			mdp.setFileName("test.txt");
			mdp.setDataHandler(dh);
			mp.addBodyPart(mdp);
		}
		
		message.setContent(mp);
		message.saveChanges();
		
		Transport transport = session.getTransport("smtp");
		
		transport.connect(host,username,password);
		
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	/** 接收邮件 */
	public void receiveMail() throws Exception{
		bodytext.delete(0, bodytext.length());
		boolean isKeepCopy = false;
		String pop3Server = "112.125.54.6";
		String loginName = "sowwa";
		String loginPassword = "0p9o8i";
		
		Session session = Session.getInstance(System.getProperties());
		session.setDebug(false);
		
		Store store = session.getStore("pop3");
		store.connect(pop3Server,loginName,loginPassword);
		Folder folder = store.getFolder("INBOX");
		folder.open(isKeepCopy ? Folder.READ_ONLY : Folder.READ_WRITE);
		
		Message[] message = folder.getMessages();
		for(int j = 0;j < message.length; j++){
			Part messagePart= (Part)message[j];
//			saveAttachMent(messagePart);
//			getMailContent(messagePart);
			if(isContainAttach(messagePart)){
				saveAttachMent(messagePart);
			}
			message[j].setFlag(Flags.Flag.DELETED,true);
		}
		folder.close(!isKeepCopy);
		store.close();
	}
	
	public void getMailContent(Part part) throws MessagingException, IOException{  
	     String contentType = part.getContentType();  
	     int nameindex = contentType.indexOf("name");  
	     boolean conname = false;  
	     if(nameindex != -1){
	         conname = true;
	     }  
	     if(part.isMimeType("text/plain")&&!conname){  
	         bodytext.append((String)part.getContent());  
	     }else if(part.isMimeType("text/html")&&!conname){
	         bodytext.append((String)part.getContent());  
	     }else if(part.isMimeType("multipart/*")){  
	         Multipart multipart = (Multipart) part.getContent();  
	         int count = multipart.getCount();  
	         for(int i=0;i<count;i++){  
	             getMailContent(multipart.getBodyPart(i));  
	         }  
	     }else if(part.isMimeType("message/rfc822")){  
	         getMailContent((Part) part.getContent());   
	     }  
	}  
	
	/** 是否包含附件 */
    public boolean isContainAttach(Part part) throws Exception {   
         boolean attachflag = false;   
         if (part.isMimeType("multipart/*")) {   
             Multipart mp = (Multipart) part.getContent();   
             for (int i = 0; i < mp.getCount(); i++) {   
                 BodyPart mpart = mp.getBodyPart(i);   
                 String disposition = mpart.getDisposition();
                 if ((disposition != null)   
                         && ((disposition.equals(Part.ATTACHMENT)) || (disposition   
                                 .equals(Part.INLINE))))   
                     attachflag = true;   
                 else if (mpart.isMimeType("multipart/*")) {   
                     attachflag = isContainAttach((Part) mpart);   
                } else {   
                     String contype = mpart.getContentType();   
                     if (contype.toLowerCase().indexOf("application") != -1)   
                         attachflag = true;   
                     if (contype.toLowerCase().indexOf("name") != -1)   
                         attachflag = true;   
                 }   
             }   
         } else if (part.isMimeType("message/rfc822")) {   
             attachflag = isContainAttach((Part) part.getContent());   
         }   
         return attachflag;   
    }    
	  
    /** 保存附件 */
    public void saveAttachMent(Part part) throws Exception {   
       String fileName = "";   
       if (part.isMimeType("multipart/*")) {
    	   
           Multipart mp = (Multipart) part.getContent();   
           for (int i = 0; i < mp.getCount(); i++) {   
               BodyPart mpart = mp.getBodyPart(i);   
               String disposition = mpart.getDisposition();  
               if ((disposition != null)   
                       && ((disposition.equals(Part.ATTACHMENT)) || (disposition   
                               .equals(Part.INLINE)))) {   
                   fileName = mpart.getFileName();   
                   if (fileName.toLowerCase().indexOf("gb2312") != -1) {   
                       fileName = MimeUtility.decodeText(fileName);   
                   }   
                   saveFile(fileName, mpart.getInputStream());   
               } else if (mpart.isMimeType("multipart/*")) {
                   saveAttachMent(mpart);   
               } else {   
                   fileName = mpart.getFileName();   
                   if ((fileName != null)   
                           && (fileName.toLowerCase().indexOf("GB2312") != -1)) {   
                       fileName = MimeUtility.decodeText(fileName);   
                       saveFile(fileName, mpart.getInputStream());   
                   }   
               }   
           }   
       } else if (part.isMimeType("message/rfc822")) { 
           saveAttachMent((Part) part.getContent());   
       }   
    }    
	
	
    private void saveFile(String filename, InputStream inputStream) throws IOException {  
         String osname = System.getProperty("os.name");  
         String storedir = getSaveAttchPath();  
         String sepatror = "";  
         if(osname == null){
             osname = "";  
         }  
           
         if(osname.toLowerCase().indexOf("win")!=-1){  
             sepatror = "//";  
             if(storedir==null||"".equals(storedir)){  
                 storedir = "d://temp";  
             }  
         }else{  
             sepatror = "/";  
             storedir = "/temp";  
         }  
           
         File storefile = new File(storedir+sepatror+filename);  
           
         BufferedOutputStream bos = null;  
         BufferedInputStream bis = null;  
         
         try {  
             bos = new BufferedOutputStream(new FileOutputStream(storefile));  
             bis = new BufferedInputStream(inputStream);  
             int c;  
             while((c= bis.read())!=-1){  
                 bos.write(c);  
                 bos.flush();  
             }  
         } catch (FileNotFoundException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         } catch (IOException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }finally{  
             bos.close();  
             bis.close();  
         }  
	}  
	    
	public String getSendaddress() {
		return sendaddress;
	}

	public void setSendaddress(String sendaddress) {
		this.sendaddress = sendaddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSendcontent() {
		return sendcontent;
	}

	public void setSendcontent(String sendcontent) {
		this.sendcontent = sendcontent;
	}

	public String getSendattachname() {
		return sendattachname;
	}

	public void setSendattachname(String sendattachname) {
		this.sendattachname = sendattachname;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public String getSendtitle() {
		return sendtitle;
	}

	public void setSendtitle(String sendtitle) {
		this.sendtitle = sendtitle;
	}

	public String getSendattach() {
		return sendattach;
	}

	public void setSendattach(String sendattach) {
		this.sendattach = sendattach;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}


	public String getSaveAttchPath() {
		return saveAttchPath;
	}

	public void setSaveAttchPath(String saveAttchPath) {
		this.saveAttchPath = saveAttchPath;
	}
	
}
