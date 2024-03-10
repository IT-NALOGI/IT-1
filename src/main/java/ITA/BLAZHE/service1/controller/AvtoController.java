package ITA.BLAZHE.service1.controller;


import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.service.AvtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avti")
public class AvtoController {

    @Autowired
    private AvtoService avtoService;

    @GetMapping
    public List<Avto> getAllAvto() {
        return avtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avto> getAvtoById(@PathVariable String id) {
        return avtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Avto createAvto(@RequestBody Avto avto) {
        return avtoService.save(avto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avto> updateAvto(@PathVariable String id, @RequestBody Avto avto) {
        return ResponseEntity.ok(avtoService.update(id, avto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvto(@PathVariable String id) {
        avtoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
