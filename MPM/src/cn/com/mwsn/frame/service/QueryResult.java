/**
 * 
 */
package cn.com.mwsn.frame.service;


/**
 * @ClassName: QueryResult
 * @Description:this class use to store the db Query source.
 * @author leo.huang
 * @date 2013-11-29 13:51:13
 * 
 */
public class QueryResult<T> {

	private long total;

	private long pageIndex;

	private long pageSize;

	private long pageTotal;
 
	private T result;

	/**
	 * @param total >=0 
	 * @param page [1..n] if(page>this.pageSize||page<=0) page=1;
	 * @param row [1..n] if row<=0 than Math.abs(row==0?1:row)
	 * @param result
	 */
	public QueryResult(long total, long pageIndex, long pageSize, T result) {
		super();
		this.total = Math.abs(total);
		if(this.total==0)
		{
			this.pageSize=0;
			this.pageTotal=0;
		}
		else
		{
			this.pageSize = Math.abs(pageSize==0?this.total:pageSize);
			this.pageTotal=(long)Math.ceil(this.total/(this.pageSize+0.0));
			if(pageIndex>this.pageTotal||pageIndex<=0)
			{
				this.pageIndex=1;
			}
			else
			{
				this.pageIndex = pageIndex;
			}
			this.result=result;
		}
	}

	public Long getTotal() {
		return total;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public long getPageSize() {
		return pageSize;
	}

	public long getPageTotal() {
		return pageTotal;
	}

	public T getResult() {
		return result;
	}

}
