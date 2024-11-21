package uz.pdp.myappfigma.search;

import org.springframework.data.jpa.domain.Specification;
import uz.pdp.myappfigma.utils.QueryUtil;

public interface GlobalS<T, C> {
    Specification<T> getSpecification(C criteria);
}
