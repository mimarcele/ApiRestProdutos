package com.produtos.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDto {

    @ApiModelProperty(value = "Código do produto")
    private Long id;

    private String nome;
    private BigDecimal quantidade;
    private BigDecimal valor;
}
