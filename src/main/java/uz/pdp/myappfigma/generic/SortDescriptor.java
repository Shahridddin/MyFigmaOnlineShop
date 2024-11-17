package uz.pdp.myappfigma.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public record SortDescriptor(String field, @JsonProperty("direction") String direction) {

    @JsonIgnore
    public Order getOrder() {
        return new Order(getDirection(), field);
    }

    @JsonIgnore
    private Direction getDirection() {
        return direction != null && direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
    }
}
