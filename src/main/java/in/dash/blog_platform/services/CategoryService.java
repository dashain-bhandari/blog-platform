package in.dash.blog_platform.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import in.dash.blog_platform.entities.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	Category createCategory(Category category);

	Optional<Category> getCategoryById(UUID id);

	void deleteCategory(UUID id);

}
