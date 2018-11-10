package cz.nigol.zpravodaj.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cz.nigol.zpravodaj.enums.Category;

@NamedQueries({
	@NamedQuery(name=Article.GET_ALL, query="SELECT a FROM Article a"),
	    @NamedQuery(name=Article.GET_BY_USER,
			query="SELECT a FROM Article a WHERE a.createdBy = :user ORDER BY changedAt DESC"),
    })
@Entity
@Table(name = "ZPR_ARTICLE")
public class Article implements Serializable {
    private static final long serialVersionUID = 5517765996360052018L;

    public static final String GET_ALL = "Article.GET_ALL";
    public static final String GET_BY_USER = "Article.GET_BY_USER";

    public static final String USER_PARAM = "user";

    @Id
    @Column(name="ID", columnDefinition="VARCHAR(300)")
    private String id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User createdBy;

    @Column(name="BODY")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String body;

    // perex
    @Column(name="LEAD_PARAGRAPH", columnDefinition="VARCHAR(700)")
    private String leadParagraph;

    @Column(name="PUBLISHED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedAt;

    @Column(name="CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name="LABEL", columnDefinition="VARCHAR(200)")
    private String label;

    @Column(name="CHANGED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt;

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
	return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * @return the body
     */
    public String getBody() {
	return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
	this.body = body;
    }

    /**
     * @return the leadParagraph
     */
    public String getLeadParagraph() {
	return leadParagraph;
    }

    /**
     * @param leadParagraph the leadParagraph to set
     */
    public void setLeadParagraph(String leadParagraph) {
	this.leadParagraph = leadParagraph;
    }

    /**
     * @return the publishedAt
     */
    public Date getPublishedAt() {
	return publishedAt;
    }

    /**
     * @param publishedAt the publishedAt to set
     */
    public void setPublishedAt(Date publishedAt) {
	this.publishedAt = publishedAt;
    }

    /**
     * @return the changedAt
     */
    public Date getChangedAt() {
	return changedAt;
    }

    /**
     * @param changedAt the changedAt to set
     */
    public void setChangedAt(Date changedAt) {
	this.changedAt = changedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id == article.getId();
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
