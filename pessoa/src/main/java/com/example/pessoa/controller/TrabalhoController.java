package com.example.pessoa.controller;

import com.example.pessoa.dto.TrabalhoDTO;
import com.example.pessoa.service.TrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabalhos")
public class TrabalhoController {

    @Autowired
    private TrabalhoService trabalhoService;

    @GetMapping
    public ResponseEntity<List<TrabalhoDTO>> findAll() {
        return ResponseEntity.ok(trabalhoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabalhoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trabalhoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TrabalhoDTO> create(@RequestBody TrabalhoDTO dto) {
        return ResponseEntity.ok(trabalhoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabalhoDTO> update(@PathVariable Long id, @RequestBody TrabalhoDTO dto) {
        return ResponseEntity.ok(trabalhoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trabalhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 