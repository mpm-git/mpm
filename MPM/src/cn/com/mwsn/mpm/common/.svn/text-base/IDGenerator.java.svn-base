package cn.com.mwsn.mpm.common;

/**
 * 自增类，递增步长1
 * @author haiyanzhihun
 *
 */
public class IDGenerator {
	private static Long seed = Long.valueOf(System.currentTimeMillis());

	public static long getUniqueID() {
		synchronized (seed) {
			Long tmp9_6 = seed;
			seed = Long.valueOf(tmp9_6.longValue() + 1L);
			return tmp9_6.longValue();
		}
	}

	public static void main(String[] args) {
		System.out.println(getUniqueID());
	}

}