package com.github.controlx.catalogue.model;

public class Page {

    private String title;
    private String totalContentItems;
    private String pageNum;
    private String pageSize;
    private ContentItems contentItems;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The totalContentItems
     */
    public String getTotalContentItems() {
        return totalContentItems;
    }

    /**
     * 
     * @param totalContentItems
     *     The total-content-items
     */
    public void setTotalContentItems(String totalContentItems) {
        this.totalContentItems = totalContentItems;
    }

    /**
     * 
     * @return
     *     The pageNum
     */
    public String getPageNum() {
        return pageNum;
    }

    /**
     * 
     * @param pageNum
     *     The page-num
     */
    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The page-size
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * @return
     *     The contentItems
     */
    public ContentItems getContentItems() {
        return contentItems;
    }

    /**
     * 
     * @param contentItems
     *     The content-items
     */
    public void setContentItems(ContentItems contentItems) {
        this.contentItems = contentItems;
    }

}
