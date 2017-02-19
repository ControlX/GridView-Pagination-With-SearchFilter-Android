package com.github.controlx.catalogue.model;

import java.util.ArrayList;
import java.util.List;

public class ContentItems {

    private List<Content> content = new ArrayList<Content>();

    /**
     * 
     * @return
     *     The content
     */
    public List<Content> getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(List<Content> content) {
        this.content = content;
    }

}
