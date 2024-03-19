package ITA.BLAZHE.service1.controller;


import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.service.AvtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/avto")
@Api(value = "Auto Renting Management System", description = "Operations pertaining Auto to rentings")
public class AvtoController {

    @Autowired
    private AvtoService avtoService;
    @ApiOperation(value = "Get list of all auto's")
    @GetMapping
    public List<Avto> getAllAvto() {
        return avtoService.findAll();
    }
    @ApiOperation(value = "Get the auto by id")
    @GetMapping("/{id}")
    public ResponseEntity<Avto> getAvtoById(@PathVariable String id) {
        return avtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @ApiOperation(value = "Create a new Auto")
    @PostMapping
    public Avto createAvto(@RequestBody Avto avto) {
        return avtoService.save(avto);
    }
    @ApiOperation(value = "Update the Auto by id")
    @PutMapping("/{id}")
    public ResponseEntity<Avto> updateAvto(@PathVariable String id, @RequestBody Avto avto) {
        return ResponseEntity.ok(avtoService.update(id, avto));
    }
    @ApiOperation(value = "Delete the Auto by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvto(@PathVariable String id) {
        avtoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
