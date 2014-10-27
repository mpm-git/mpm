package org.cz.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;

import sun.awt.image.codec.JPEGImageEncoderImpl;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author zeng.qinglong
 * 
 */
public class Util {
	public static String[] getImageTypes()
	{
		return new String[]{"jpg","JPG","png","PNG","jpeg","JPEG","bmp","BMP"};
	}
	//将source进行MD5加密，获取加密后的字符串
	public static String getMD5(byte[] source) {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//根据文件路径和操作系统编码返回指定文件的GBK编码内容
	public static String getGBFileText(String filePath, String osEncoding){
		StringBuffer context_buff = new StringBuffer();
		try {
			FileReader reader = new FileReader(filePath);  
			BufferedReader br = new BufferedReader(reader);   
			String s1 = null;
			while ((s1 = br.readLine()) != null) {    
				context_buff.append(s1+"\r\n");
			}		    
			br.close();    
			reader.close();
			String context = context_buff.toString();
			if ("GBK".equals(osEncoding) == false) {
				context = new String(context.getBytes("ISO8859_1"), "GBK");
			}
			return context;
		}catch(IOException ioe){			
			ioe.printStackTrace();
			return "nofile";
		}
		
	}

	//将source进行BASE64加密，获取加密后的字符串
	public static String getMd5(String str) {
        try {
			//确定计算方法
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        return base64en.encode(md5.digest(str.getBytes()));
        } catch(Exception e) {
        	return "";
        }
	}
	
	//编码转换：ISO8859_1 ==> GBK
	public static String iso2gb(String str) {
		try {
			str = new String(str.getBytes("ISO8859_1"), "GBK");
		} catch(Exception e) {
			str = "";
		}
		return str;
	}

	//编码转换：GBK ==> ISO8859_1
	public static String gb2iso(String str) {
		try {
			str = new String(str.getBytes("GBK"), "ISO8859_1");
		} catch(Exception e) {
			str = "";
		}
		return str;
	}
	
	
	//输出日志
	public static void writeLog(Class<?> c,String str) {
		Logger logger = Logger.getLogger(c);
		if ("en_US".equals(System.getenv("LANG"))) {
			logger.info(Util.gb2iso(str));
		} else {
			logger.getClass();
			logger.info(str);
		}
	}
	//输出日志
	public static void writeLog(Class<?> c,String... str) {
		Logger logger = Logger.getLogger(c);
		Method method=null;
		try {
			if(str.length==2)
			{
				method = logger.getClass().getMethod(str[1].toLowerCase(), Object.class);
			}
			else
			{
				method = logger.getClass().getMethod("info", Object.class);
			}
			if ("en_US".equals(System.getenv("LANG"))) {
				method.invoke(logger, Util.gb2iso(str[0]));
//				logger.info(Util.gb2iso(str[0]));
			} else {
				
				method.invoke(logger, str[0]);
//				logger.info(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 搜索条件过滤
	public static String filterQueryString(String str) {
		if (str == null)  return "";
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("\"", "&quot;");
		str = str.replace("'", "&#39;");
		return str;
	}
	
	/**
	 *@author huang.die
	 *分页
	 *@param bean			控制器	
	 *@param total			总数
	 *@param page			当前页数
	 *@param paginal_num	总页数
	 */
	public static String paging(String bean, String action, int total, int page, int paginal_num){
		String pagingHtml = "<div style='TEXT-ALIGN:right'>" +
					"<span style='font-weight: bolder'>" +
						page +
					"</span>" +
					"/ " +
					"<span style='font-weight: bolder'>" +
						paginal_num +
					"</span>" +
					"&nbsp;&nbsp;&nbsp;共" +
					"<span style='font-weight: bolder'>" +
						total +
					"</span> " +
					"项&nbsp;&nbsp;&nbsp;";
		if (page>1) {
			pagingHtml += "<a href='" + bean + ".do?action="+action+"&page=" + (page-1) + "'>上一页</a>";
		}else if (page <=1 ) {
			pagingHtml += "上一页";
		}
		if (page<paginal_num) {
			pagingHtml += "<a href='" + bean + ".do?action="+action+"&page=" + (page+1) + "'>下一页</a>";
		} else if(page>=paginal_num){
			pagingHtml += "下一页";
		}
		pagingHtml += "&nbsp;" +
						"<form action='" + bean + ".do?action="+action+"' method='post' style='margin:0px;display:inline'>" +
						"跳转到第<input name='page' size='2' type='text'/>页<input value='GO' type='submit'/>" +
						"</form>"+
						"</div>";
					
		return pagingHtml;
	}
	/**
	 * 检查字符串是否为null或者‘’或者为‘null’其中的null不区分大小写
	 * 如果为null则返回true，否则返回false
	 * @param s
	 * @return
	 */
	public static boolean isNULL(String s){
		if(s == null) {
			return true;
		}else if(s == "") {
			return true;
		}else if("null".equalsIgnoreCase(s)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * AES加密
	 * @param content 	  待加密数值
	 * @param encrypt 加密或者解密（true加密，false解密）
	 */
	public static String setAES(String content, boolean encrypt){
		int mode;
		byte[] contentByte;
		//若是加密String转byte[]，若是解密十六进制String转byte[]
		if (encrypt) {
			mode = Cipher.ENCRYPT_MODE;
			contentByte = content.getBytes();
		}else{
			mode = Cipher.DECRYPT_MODE;
			contentByte = parseHexStr2Byte(content);
		}
//		writeLog(Util.class, content);
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed("z0x9c8V&".getBytes("utf-8"));
			kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            cipher.init(mode, key);// 初始化   
            byte[] result = cipher.doFinal(contentByte);  
            //加密结果二进制转换为十六进制
            if (encrypt) {
            	return new String(parseByte2HexStr(result).getBytes());
			}
            return new String(result); // 加密 
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return null;
		
	}
	public static void main(String[] args) {
		System.out.println(Util.setAES("14", true));
		Util.getMd5("99");
		String s="<a href=\" /front/into/idea   \">企业理念</a></div><a href=\"/front/into/idea?name=sss&age=12\">其他</a></div>";
		String reg="/front/into/idea?name=sss&age=12";		
//                String reg="/front/into/idea";
		reg=reg.replaceAll("\\?", "\\\\?");
		reg=reg.replaceAll("\\&", "\\\\&");
		System.out.println(reg);
		System.out.println(s.replaceAll("[\"|\']\\s*"+reg+"\\s*[\"|\']", "\""+reg+".html\""));
		System.out.println(s.replaceAll("/front/into/idea?name=sss&age=12", "\""+reg+".html\""));
		System.out.println("D:\\tomcat-5.5.23\\webapps\\Diy2\\".replaceAll("\\\\", "\\\\\\\\"));
		String replaceAll = "D:\\tomcat-5.5.23\\webapps\\Diy2\\Clipart\\1\\xx/1dialog.svg".replaceAll("D:\\tomcat-5.5.23\\webapps\\Diy2\\".replaceAll("\\\\", "\\\\\\\\"), "");
		System.out.println(replaceAll);
	}
	
    /**将二进制转换成16进制 
     * @param buf 
     * @return 
     */  
    public static String parseByte2HexStr(byte buf[]) {  
            StringBuffer sb = new StringBuffer();  
            for (int i = 0; i < buf.length; i++) {  
                    String hex = Integer.toHexString(buf[i] & 0xFF);  
                    if (hex.length() == 1) {  
                            hex = '0' + hex;  
                    }  
                    sb.append(hex.toUpperCase());  
            }  
            return sb.toString();  
    }  
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public static byte[] parseHexStr2Byte(String hexStr) {  
            if (hexStr.length() < 1)  
                    return null;  
            byte[] result = new byte[hexStr.length()/2];  
            for (int i = 0;i< hexStr.length()/2; i++) {  
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                    result[i] = (byte) (high * 16 + low);  
            }  
            return result;  
    }  
    
	  /**
     * 得到需要进行MD5加密的字符串.
     * @param paramters 待加密的参数名值对
     * @return
     */
    public static String getSignContent(Map<String,String> paramters){
    	//需要进行MD5的字符串
    	StringBuilder content = new StringBuilder();
    	//先将key排序
    	List<String> keys = new ArrayList<String>(paramters.keySet());
		Collections.sort(keys);
    	for(String key : keys) {
    		content.append("&").append("_").append(key).append("=").append(paramters.get(key));
    	}
		return content.toString().substring(1);
    }
    public static void reduceImg(String imgsrc, String imgdist, int widthdist,        
            int heightdist) {        
        try {        
            File srcfile = new File(imgsrc);        
            if (!srcfile.exists()) {        
                return;        
            }        
            Image src = javax.imageio.ImageIO.read(srcfile);        
           
            BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,        
                    BufferedImage.TYPE_INT_RGB);        
           
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH ), 0, 0,  null);        
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);        
                    
//            OutputStream out =new FileOutputStream(imgdist);        
            OutputStream out =new BufferedOutputStream( new FileOutputStream(imgdist));        
            JPEGImageEncoderImpl encoder = (JPEGImageEncoderImpl) JPEGCodec.createJPEGEncoder(out);        
            encoder.encode(tag);        
            out.close();        
           
        } catch (IOException ex) {        
            ex.printStackTrace();        
        }        
    } 
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
	  * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	  * @param str 字符串
	  * @param format 日期格式
	  * @return 日期
	  * @throws java.text.ParseException
	  */
	public static Date str2Date(String str){
		if (null == str || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	 /** 日期转换为字符串
	  * @param date 日期
	  * @param format 日期格式
	  * @return 字符串
	  */
	public static String date2Str(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(date);
	}
	public static String date2Str(Date date,String format) {
		if (null == date||format==null||"".equals(format.trim())) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
