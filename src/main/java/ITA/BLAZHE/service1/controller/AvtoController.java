package ITA.BLAZHE.service1.controller;

import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.service.AvtoCommandService;
import ITA.BLAZHE.service1.service.AvtoQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/avto")
@Api(value = "Auto Renting Management System", description = "Operations pertaining to auto rentals")
public class AvtoController {

    @Autowired
    private AvtoCommandService avtoCommandService;

    @Autowired
    private AvtoQueryService avtoQueryService;

    @ApiOperation(value = "Get list of all autos")
    @GetMapping
    public ResponseEntity<List<Avto>> getAllAvto() {
        List<Avto> autos = avtoQueryService.getAllAvto();
        return ResponseEntity.ok(autos);
    }

    @ApiOperation(value = "Get an auto by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Avto> getAvtoById(@PathVariable String id) {
        Avto avto = avtoQueryService.getAvtoById(id);
        return ResponseEntity.ok(avto);
    }

    @ApiOperation(value = "Create a new auto")
    @PostMapping
    public ResponseEntity<Avto> createAvto(@RequestBody Avto avto) {
        Avto createdAvto = avtoCommandService.createAvto(avto);
        return ResponseEntity.ok(createdAvto);
    }

    @ApiOperation(value = "Update an auto by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Avto> updateAvto(@PathVariable String id, @RequestBody Avto avto) {
        Avto updatedAvto = avtoCommandService.updateAvto(id, avto);
        return ResponseEntity.ok(updatedAvto);
    }

    @ApiOperation(value = "Delete an auto by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvto(@PathVariable String id) {
        avtoCommandService.deleteAvto(id);
        return ResponseEntity.ok().build();
    }
}
