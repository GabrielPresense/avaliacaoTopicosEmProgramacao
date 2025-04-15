package com.example.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private TrabalhoDTO trabalho;
} 