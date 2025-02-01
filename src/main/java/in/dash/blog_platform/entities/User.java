package in.dash.blog_platform.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(nullable=false,updatable=false)
	private UUID id;
	
	@Column(nullable=false,unique=true)
	private String email;
	


	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	@Column()
	private LocalDateTime createdAt;
	
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, email, id, name, password);
	}
	
	@OneToMany(mappedBy="author",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Post> posts=new ArrayList<Post>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt=LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User(UUID id, String email, String password, String name, LocalDateTime createdAt, List<Post> posts) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.createdAt = createdAt;
		this.posts = posts;
	}

	public User() {
		super();
	}
	
	
	
}
