package in.dash.blog_platform.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity()
@Table(name="posts")


public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(nullable=false,updatable=false)
	private UUID id;
	
	@Column(nullable=false)
	private String title;
	
	//text le varying content as content grows it can accomodate more
	@Column(nullable=false,columnDefinition="TEXT")
	private String content;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private PostStatus status;
	
	@Column(nullable=false)
	private Integer readingTime;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@Column(nullable=false)
	private LocalDateTime updatedAt;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private User author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",nullable=false)
	private Category category;
	
	@ManyToMany()
	@JoinTable(
			name="post_tags",
			joinColumns=@JoinColumn(name="post_id"),
			inverseJoinColumns=@JoinColumn(name="tag_id")
			)
	private Set<Tag> tags=new HashSet();
	
	@Override
	public int hashCode() {
		return Objects.hash(content, createdAt, id, readingTime, status, title, updatedAt);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(content, other.content) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(id, other.id) && Objects.equals(readingTime, other.readingTime)
				&& status == other.status && Objects.equals(title, other.title)
				&& Objects.equals(updatedAt, other.updatedAt);
	}



	@PrePersist
	protected void onCreate() {
		this.createdAt=LocalDateTime.now();
		this.updatedAt=LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt=LocalDateTime.now();
	}



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public PostStatus getStatus() {
		return status;
	}



	public void setStatus(PostStatus status) {
		this.status = status;
	}



	public Integer getReadingTime() {
		return readingTime;
	}



	public void setReadingTime(Integer readingTime) {
		this.readingTime = readingTime;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	public User getAuthor() {
		return author;
	}



	public void setAuthor(User author) {
		this.author = author;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<Tag> getTags() {
		return tags;
	}



	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}



	public Post(UUID id, String title, String content, PostStatus status, Integer readingTime, LocalDateTime createdAt,
			LocalDateTime updatedAt, User author, Category category, Set<Tag> tags) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.readingTime = readingTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.author = author;
		this.category = category;
		this.tags = tags;
	}



	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + ", readingTime="
				+ readingTime + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", author=" + author
				+ ", category=" + category + ", tags=" + tags + "]";
	}



	public Post() {
		super();
	}
	
	
}
