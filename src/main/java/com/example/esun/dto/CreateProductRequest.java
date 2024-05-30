package com.example.esun.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull
    private String productId;

    @NotNull
    private String productName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;
}
