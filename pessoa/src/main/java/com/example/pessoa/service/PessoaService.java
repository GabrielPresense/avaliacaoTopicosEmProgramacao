package com.example.pessoa.service;

import com.example.pessoa.dto.PessoaDTO;
import com.example.pessoa.dto.TrabalhoDTO;
import com.example.pessoa.model.Pessoa;
import com.example.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        return pessoaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @Transactional
    public PessoaDTO create(PessoaDTO dto) {
        if (pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        Pessoa pessoa = toEntity(dto);
        return toDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        return toDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    private PessoaDTO toDTO(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setCpf(pessoa.getCpf());
        if (pessoa.getTrabalho() != null) {
            TrabalhoDTO trabalhoDTO = new TrabalhoDTO();
            trabalhoDTO.setId(pessoa.getTrabalho().getId());
            trabalhoDTO.setCargo(pessoa.getTrabalho().getCargo());
            trabalhoDTO.setSalario(pessoa.getTrabalho().getSalario());
            dto.setTrabalho(trabalhoDTO);
        }
        return dto;
    }

    private Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        return pessoa;
    }
} 