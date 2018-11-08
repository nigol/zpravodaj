package cz.nigol.zpravodaj.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=Article.GET_ALL, query="SELECT a FROM Article a"),
    })
@Entity
@Table(name = "ZPR_ARTICLE")
public class Article implements Serializable {
    private static final long serialVersionUID = 5517765996360052018L;

    public static final String GET_ALL = "Article.GET_ALL";

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
