package uz.pdp.myappfigma.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.myappfigma.criteria.CategoryCriteria;
import uz.pdp.myappfigma.dto.BaseResponse;
import uz.pdp.myappfigma.dto.category.CategoryCreatDto;
import uz.pdp.myappfigma.dto.category.CategoryDto;
import uz.pdp.myappfigma.dto.category.CategoryUpdateDto;
import uz.pdp.myappfigma.dto.page.PageDto;
import uz.pdp.myappfigma.enums.Gender;
import uz.pdp.myappfigma.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Long> create(@Valid @RequestBody CategoryCreatDto dto) {
        Long newId = categoryService.create(dto);

        return new BaseResponse<>(newId);
    }

    @GetMapping("/{id}")
    public BaseResponse<CategoryDto> getId(@Valid @PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        return new BaseResponse<>(categoryDto);
    }

    @GetMapping("/update/{id}")
    public BaseResponse<Long> update(@Valid @PathVariable Long id, @Valid @RequestBody CategoryUpdateDto dto) {
        Long updateId = categoryService.update(id, dto);
        return new BaseResponse<>(updateId);
    }

    @GetMapping("/parentCategory")
    public BaseResponse<List<CategoryDto>> getCategoriesByGender(@RequestParam Gender gender) {
        List<CategoryDto> categories = categoryService.getCategoriesByGender(gender);
        return new BaseResponse<>(categories);
    }

}
