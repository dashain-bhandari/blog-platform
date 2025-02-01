package in.dash.blog_platform.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import in.dash.blog_platform.entities.Post;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class CategoryDto {
	private UUID id;

	private String name;

	private long postCount;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPostCount() {
		return postCount;
	}

	public void setPostCount(long postCount) {
		this.postCount = postCount;
	}

	public CategoryDto(UUID id, String name, long postCount) {
		super();
		this.id = id;
		this.name = name;
		this.postCount = postCount;
	}

	public CategoryDto() {
		super();
	}
	
	
}
