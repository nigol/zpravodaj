package cz.nigol.zpravodaj.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@NamedQueries({
@NamedQuery(name=ShortInfo.GET_ALL,
query="SELECT s FROM ShortInfo s"),
    @NamedQuery(name=ShortInfo.GET_BY_DATE,
    query="SELECT s FROM ShortInfo s WHERE s.infoDate = :date ORDER BY f.createdAt DESC"),
})

@Entity
@Table(name = "ZPR_SHORT_INFO")
public class ShortInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String GET_ALL = "ShortInfo.getAll";
    public static final String GET_BY_DATE = "ShortInfo.GET_BY_DATE";

    public static final String DATE_PARAM = "date";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="URL")
    private String url;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "INFO_DATE")
    @Temporal(TemporalType.DATE)
    private Date infoDate;

    @PrePersist
    public void prepareData() {
        createdAt = new Date();
    }

    public Date getInfoDate() {
      return this.infoDate;
    }

    public void setInfoDate(Date infoDate) {
      this.infoDate = infoDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortInfo)) return false;
        ShortInfo shortInfo = (ShortInfo) o;
        return id == shortInfo.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
