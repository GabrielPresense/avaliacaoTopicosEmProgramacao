package com.example.pessoa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabalhos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String cargo;
    
    @Column(nullable = false)
    private Double salario;
    
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
} 