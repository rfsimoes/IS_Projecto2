package common;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;
	private String username;
	private String password;
	private String name;
	private EmailValidator email;
	

	public User() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public EmailValidator getEmail() {
		return this.email;
	}

	public void setEmail(EmailValidator email) {
		this.email = email;
	}
   
}
