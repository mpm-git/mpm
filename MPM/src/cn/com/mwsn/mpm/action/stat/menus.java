package cn.com.mwsn.mpm.action.stat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class menus {

	public static String getPropertiesValue() {
		String path = menus.class.getResource("menus.properties").toString();
		String file=path.substring(6, path.length());
		BufferedReader reader = null;
		String tempString = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			// 一次读入一行，直到读入null为文件结束
			if ((tempString = reader.readLine()) == null) {
				return null;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return tempString;
	}

	public static void main(String[] args) {
		//System.out.println(getPropertiesValue());
		
//		List<List<Map<String,String>>> list=new ArrayList<List<Map<String,String>>>();
//		List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
//		Map<String,String> map1=new HashMap<String,String>();
//		map1.put("menu1", "url1");
//		Map<String,String> map2=new HashMap<String,String>();
//		map1.put("menu2", "url2");
//		list1.add(map1);
//		list1.add(map2);
//		list.add(list1);
		
		
	}

}
