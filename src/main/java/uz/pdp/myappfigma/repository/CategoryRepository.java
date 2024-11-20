package uz.pdp.myappfigma.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.pdp.myappfigma.criteria.CategoryCriteria;
import uz.pdp.myappfigma.dto.category.CategorySpecs;
import uz.pdp.myappfigma.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    default Page<Category> findPage(CategoryCriteria criteria) {
        Specification<Category> specification = CategorySpecs.getSpecification(criteria);
        return findAll(specification, PageRequest.of(criteria.getPage(), criteria.getSize()));
    }
}
