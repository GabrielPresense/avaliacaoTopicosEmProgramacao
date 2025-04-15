package com.example.pessoa.service;

import com.example.pessoa.dto.TrabalhoDTO;
import com.example.pessoa.model.Pessoa;
import com.example.pessoa.model.Trabalho;
import com.example.pessoa.repository.PessoaRepository;
import com.example.pessoa.repository.TrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabalhoService {

    @Autowired
    private TrabalhoRepository trabalhoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<TrabalhoDTO> findAll() {
        return trabalhoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TrabalhoDTO findById(Long id) {
        return trabalhoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));
    }

    @Transactional
    public TrabalhoDTO create(TrabalhoDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        
        Trabalho trabalho = new Trabalho();
        trabalho.setCargo(dto.getCargo());
        trabalho.setSalario(dto.getSalario());
        trabalho.setPessoa(pessoa);
        
        return toDTO(trabalhoRepository.save(trabalho));
    }

    @Transactional
    public TrabalhoDTO update(Long id, TrabalhoDTO dto) {
        Trabalho trabalho = trabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabalho não encontrado"));
        
        trabalho.setCargo(dto.getCargo());
        trabalho.setSalario(dto.getSalario());
        
        return toDTO(trabalhoRepository.save(trabalho));
    }

    @Transactional
    public void delete(Long id) {
        trabalhoRepository.deleteById(id);
    }

    private TrabalhoDTO toDTO(Trabalho trabalho) {
        TrabalhoDTO dto = new TrabalhoDTO();
        dto.setId(trabalho.getId());
        dto.setCargo(trabalho.getCargo());
        dto.setSalario(trabalho.getSalario());
        dto.setPessoaId(trabalho.getPessoa().getId());
        return dto;
    }
} 