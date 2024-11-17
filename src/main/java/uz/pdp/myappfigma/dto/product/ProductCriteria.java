package uz.pdp.myappfigma.dto.product;


import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import uz.pdp.myappfigma.generic.GenericCriteria;


@Getter
@Setter
@ParameterObject
public class ProductCriteria extends GenericCriteria {

    private Long priceFrom;
    private Long priceTo;

    @Override
    public Sort defaultSort() {
        return Sort.by(Sort.Direction.DESC, "id");
    }
}
