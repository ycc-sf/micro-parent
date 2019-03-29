package com.demo.microuser.model;

/**
 * 分页实体类
 * Created by Administrator on 2015/10/19.
 */
public class Page {
    // 分页查询开始记录位置
    private int begin;
    // 分页查看下结束位置
    private int end;
    // 查询结果总记录数
    private int count;
    // 当前页码
    private int current;
    // 总共页数
    private int total;
    //每页显示条数
    private int pageSize = 10;

    public Page() {
    	
    }

    /**
     * 构造函数
     *
     * @param begin
     * @param pageSize
     */
    public Page(int begin, int pageSize) {
        this.begin = begin;
        this.pageSize = pageSize;
        this.end = this.begin + this.pageSize;
        this.current = (int) Math.floor((this.begin * 1.0d) / this.pageSize) + 1;
    }

    /**
     * @param begin
     * @param pageSize
     * @param count
     */
    public Page(int begin, int pageSize, int count) {
        this(begin, pageSize);
        this.count = count;
    }

    /**
     * @return the begin
     */
    public int getBegin() {
        begin = (current - 1) * pageSize;
        if (begin < 0) {
            begin = 0;
        }
        return begin;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        if (end < 1) {
            end = getBegin() + pageSize;
        }
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(int begin) {
        this.begin = begin;
        if (this.pageSize != 0) {
            this.current = (int) Math.floor((this.begin * 1.0d) / this.pageSize) + 1;
        }
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
        this.total = (int) Math.floor((this.count * 1.0d) / this.pageSize);
        if (this.count % this.pageSize != 0) {
            this.total++;
        }
    }

    /**
     * @return the current
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        if (total == 0) {
            return 1;
        }
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.begin != 0) {
            this.current = (int) Math.floor((this.begin * 1.0d) / this.pageSize) + 1;
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "begin=" + begin +
                ", end=" + end +
                ", count=" + count +
                ", current=" + current +
                ", total=" + total +
                ", pageSize=" + pageSize +
                '}';
    }
}
