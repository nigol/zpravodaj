package cz.nigol.zpravodaj.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
@NamedQuery(name=FileMetadata.GET_ALL,
query="SELECT f FROM Filemetadata f"),
    @NamedQuery(name=FileMetadata.GET_BY_USER,
    query="SELECT f FROM FileMetadata f WHERE f.user = :user ORDER BY f.createdAt DESC"),
})

@Entity
@Table(name = "ZPR_FILE_META")
public class FileMetadata implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String GET_ALL = "FileMetadata.getAll";
    public static final String GET_BY_USER = "FileMetadata.GET_BY_USER";

    public static final String USER_PARAM = "user";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="PATH")
    private String path;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "CREATED_AT")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

    @PrePersist
    public void prepareData() {
        createdAt = new Date();
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        if (!(o instanceof FileMetadata)) return false;
        FileMetadata fileMetadata = (FileMetadata) o;
        return id == fileMetadata.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
