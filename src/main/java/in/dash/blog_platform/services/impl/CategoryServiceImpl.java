package in.dash.blog_platform.services.impl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import in.dash.blog_platform.entities.Category;
import in.dash.blog_platform.entities.Post;
import in.dash.blog_platform.repositories.CategoryRepository;
import in.dash.blog_platform.services.CategoryService;


@Service

public class CategoryServiceImpl implements CategoryService{

	
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories=this.categoryRepository.findAllWithPostCount();
		return categories;
	}

	@Override
	@Transactional
	public Category createCategory(Category category) {
		boolean exists=this.categoryRepository.existsByNameIgnoreCase(category.getName());
		if(exists){
			throw new IllegalArgumentException("Category with name already exists in database");
		}

		return this.categoryRepository.save(category);
	}

	@Override
	public Optional<Category> getCategoryById(UUID id) {
		return this.categoryRepository.findById(id);
	}

	@Override
	public void deleteCategory(UUID id) {
		Optional<Category> category= getCategoryById(id);
		if(category.isPresent()){
			if(!category.get().getPosts().isEmpty())
			{
				this.categoryRepository.deleteById(id);
			}
			else{
				throw new IllegalArgumentException("Category cannot be deleted.");
			}
		}
		else{
			throw new IllegalArgumentException("Category not found");
		}
	}

}
