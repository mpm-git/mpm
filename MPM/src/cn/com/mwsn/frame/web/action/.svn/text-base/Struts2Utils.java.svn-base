package cn.com.mwsn.frame.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author by wuda
 * @description:实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 */
public class Struts2Utils {

	// header 常量定义
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	public static final String LAST_URI = "s_uri";

	private static Logger logger = LoggerFactory.getLogger(Struts2Utils.class);

	private Struts2Utils() {
	}

	// 取得Request/Response/Session的简化函数 //

	/**
	 * 取得HttpSession的简化方法.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化方法.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 取得Application的简化方法
	 * @return
	 */
	public static ServletContext getApplication(){
		return ServletActionContext.getServletContext();
	}

	/**
	 * 取得HttpResponse的简化方法.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 绕过jsp/freemaker直接输出文本的函数 //

	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",见示例代码.
	 *            不设置时默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content,
			final String... headers) {
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName,
						NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName
							+ "不是一个合法的header类型");
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param string
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String string, final String... headers) {
		render("application/json", string, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param map
	 *            Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	@SuppressWarnings("unchecked")
	public static void renderJson(final Map map, final String... headers) {
		String jsonString = new JSONObject(map).toString();
		renderJson(jsonString, headers);
	}


	/**
	 * 直接输出JSON.
	 * 
	 * @param object
	 *            Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object, final String... headers) {
		String jsonString = new JSONObject(object).toString();
		renderJson(jsonString, headers);
	}

	/**
	 * add by tiandw used to get the map that don't contain null value from
	 * request
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> getParams(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String[]> m = Struts2Utils.getRequest().getParameterMap();
		if (str != null && m.size() > 0) {
			Set<String> keySet = m.keySet();
			for (String key : keySet) {
				String[] values = m.get(key);
				if (str != null) {
					if (key.startsWith(str) && values != null
							&& values[0] != null
							&& !values[0].trim().equals("")) {
						map.put(key.substring(key.indexOf(str)), StringUtils
								.join(values, ","));
					}
				} else {
					if (values != null && values[0] != null
							&& !values[0].trim().equals("")) {
						map.put(key, StringUtils.join(values, ","));
					}
				}
			}
		}
		return map;
	}

	/**
	 * add by tiandw used to get the map that don't contain null value from
	 * request
	 * 
	 * @param str
	 * @return
	 */
	public Map<String, Object> getParams() {
		return getParams(null);
	}

	/**
	 * 返回保存的list
	 */
	public static void renderList() {
		StringBuffer back = new StringBuffer();
		back.append("<html><head></head><body>");
		back.append("<form name=\"backForm\" action=\"").append(
				getSession().getAttribute(LAST_URI))

		.append("\" method=\"post\"></form>\n");
		back.append("<script>document.backForm.submit();</script>");
		back.append("</body></html>");
		getSession().removeAttribute(LAST_URI);
		System.out.println(back.toString());
		renderHtml(back.toString());
	}
}
