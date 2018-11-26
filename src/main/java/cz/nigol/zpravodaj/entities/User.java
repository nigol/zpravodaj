package cz.nigol.zpravodaj.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cz.nigol.zpravodaj.enums.Role;

@NamedQueries({
	@NamedQuery(name=User.GET_ALL, query="SELECT u FROM User u ORDER BY u.id ASC"),
	    @NamedQuery(name=User.GET_ACTIVE, query="SELECT u FROM User u WHERE u.active = true"),
    })
@Entity
@Table(name = "ZPR_USER")
public class User implements Serializable {
    private static final long serialVersionUID = -8877869295129979225L;

    public static final String GET_ALL = "User.GET_ALL";
    public static final String GET_ACTIVE = "User.GET_ACTIVE";

    @Id
    @Column(name="ID", columnDefinition="VARCHAR(50)")
    private String id;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="EMAIL", columnDefinition="VARCHAR(300)")
    private String email;

    @Column(name="FULL_NAME", columnDefinition="VARCHAR(200)")
    private String fullName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="ACTIVE")
    private boolean active;

    @OneToMany(mappedBy="createdBy")
    private List<Article> articles;

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
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals((user.getId()));
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
