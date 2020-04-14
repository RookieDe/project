package com.project.project5.dto.testDTO.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName eventRequest
 * @date 2020/4/14 17:23
 */
public class EventRequest {
    private String bid;

    private String bname;

    private String authors;

    private String pubcom;

    private Date pubdate;

    private BigDecimal price;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPubcom() {
        return pubcom;
    }

    public void setPubcom(String pubcom) {
        this.pubcom = pubcom;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
