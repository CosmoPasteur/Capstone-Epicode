package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Appuntamento;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.AppuntamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appuntamenti")
@CrossOrigin(origins = "http://localhost:5173")
public class AppuntamentoController {

    @Autowired
    private AppuntamentoRepository repository;

    // GET - tutti gli appuntamenti
    @GetMapping
    public ResponseEntity<List<Appuntamento>> getAll() {
        List<Appuntamento> appuntamenti = repository.findAll();
        return ResponseEntity.ok(appuntamenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appuntamento> getById(@PathVariable Long id) {
        Optional<Appuntamento> appuntamento = repository.findById(id);
        return appuntamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appuntamento> create(@RequestBody Appuntamento appuntamento) {
        if (appuntamento.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Appuntamento saved = repository.save(appuntamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appuntamento> update(
            @PathVariable Long id,
            @RequestBody Appuntamento datiAggiornati) {

        return repository.findById(id)
                .map(app -> {
                    app.setTitolo(datiAggiornati.getTitolo());
                    app.setData(datiAggiornati.getData());
                    app.setDescrizione(datiAggiornati.getDescrizione());
                    Appuntamento updated = repository.save(app);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}