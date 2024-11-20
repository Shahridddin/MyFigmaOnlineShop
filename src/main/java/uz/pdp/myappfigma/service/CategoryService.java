package uz.pdp.myappfigma.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.myappfigma.criteria.CategoryCriteria;
import uz.pdp.myappfigma.dto.category.CategoryCreatDto;
import uz.pdp.myappfigma.dto.category.CategoryDto;
import uz.pdp.myappfigma.dto.category.CategoryUpdateDto;
import uz.pdp.myappfigma.entity.Category;
import uz.pdp.myappfigma.generic.CategoryMapper;
import uz.pdp.myappfigma.dto.page.PageDto;
import uz.pdp.myappfigma.repository.CategoryRepository;

@Service
public class CategoryService {


    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper mapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Long create(CategoryCreatDto dto){
        Category category = categoryMapper.toEntity(dto);
        categoryRepository.save(category);
        return category.getId();
    }

    public Long update(Long id, CategoryUpdateDto dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found" + id));
        categoryMapper.partialUpdate(dto,category);
        categoryRepository.save(category);
        return category.getId();
    }

    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }

    @Transactional
    public PageDto<CategoryDto> getPage(CategoryCriteria criteria) {
        Page<CategoryDto> page = categoryRepository.findPage(criteria).map(categoryMapper::toDto);
        return new PageDto<>(page);
    }

}
