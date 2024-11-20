package uz.pdp.myappfigma.dto.category;

import org.springframework.data.jpa.domain.Specification;
import uz.pdp.myappfigma.criteria.CategoryCriteria;
import uz.pdp.myappfigma.entity.Category;
import uz.pdp.myappfigma.entity.Category_;
import uz.pdp.myappfigma.entity.Product;
import uz.pdp.myappfigma.enums.Gender;
import uz.pdp.myappfigma.utils.QueryUtil;

public final class CategorySpecs {

    private CategorySpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Category> getSpecification(CategoryCriteria criteria) {

        Specification<Category> specs = Specification.where(null);

        if (criteria.getGender() != null) {
            specs = specs.and(QueryUtil.equals(root -> root.get(Category_.GENDER), criteria.getGender()));
        }

        return specs;
    }


    public static Specification<Product> productTypeSpecification(Gender gender) {
        return (root, qb, cb) -> cb.equal(root.get(Category_.GENDER), gender);
    }

}
