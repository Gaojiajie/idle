package com.cyl.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by cyl on 2019-03-08.
 */
@Entity
@Table(name = "attachment")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","goods"})
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "attachment_name", length = 200)
    @JsonProperty("attachmentName")
    private String attachmentName;

    @NotNull
    @Column(name = "attachment_url", length = 200)
    @JsonProperty("attachmentUrl")
    private String attachmentUrl;

    @NotNull
    @JsonIgnore
    @ManyToOne(targetEntity = Goods.class)
    @JoinColumn(name = "goods_id",insertable = false, updatable = false)
    private Goods goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentUrl='" + attachmentUrl + '\'' +
//                ", goods=" + goods +
                '}';
    }
}
