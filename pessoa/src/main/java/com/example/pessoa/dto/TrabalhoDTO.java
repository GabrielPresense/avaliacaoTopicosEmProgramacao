package com.example.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabalhoDTO {
    private Long id;
    private String cargo;
    private Double salario;
    private Long pessoaId;
} 