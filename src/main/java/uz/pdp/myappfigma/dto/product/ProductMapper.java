package uz.pdp.myappfigma.dto.product;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.myappfigma.entity.Product;
import uz.pdp.myappfigma.generic.GenericMapper;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper extends GenericMapper<Product, ProductDto, ProductCreateDto, ProductUpdateDto> {

}