package in.dash.blog_platform.mappers;

import java.util.List;

import in.dash.blog_platform.dtos.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


import in.dash.blog_platform.dtos.CategoryDto;
import in.dash.blog_platform.entities.Category;
import in.dash.blog_platform.entities.Post;
import in.dash.blog_platform.entities.PostStatus;

@Mapper(componentModel="spring",unmappedTargetPolicy=ReportingPolicy.IGNORE)

public interface CategoryMapper {

	@Mapping(target="postCount",source="posts",qualifiedByName="calculatePostCount")
	CategoryDto toDto(Category category);

	@Mapping(target = "id", ignore = true) // Ignore the ID field since it's auto-generated
	@Mapping(target = "posts", ignore = true) // Ignore the posts field for simplicity
   Category toEntity(CreateCategoryRequest createCategoryRequest);
	
	@Named("calculatePostCount")
	default long calculatePostCount(List<Post> posts) {
		if(posts==null) {
			return 0;
		}
		return posts.stream().filter(post->PostStatus.PUBLISHED.equals(post.getStatus())).count();
	}
}
