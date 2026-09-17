package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.service.EmployeService;
import com.lacouf.rsbjwt.service.dto.CompagnieDTO;
import com.lacouf.rsbjwt.service.dto.EmployeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping("/compagnie")
    public ResponseEntity<CompagnieDTO> addCompagnie(@RequestBody CompagnieRequest request) {
        CompagnieDTO compagnie = employeService.addCompagnie(request.compagniName(), request.city());
        return ResponseEntity.status(HttpStatus.CREATED).body(compagnie);
    }

    @PostMapping
    public ResponseEntity<EmployeDTO> addEmploye(@RequestBody EmployeRequest request) {
        EmployeDTO employe = employeService.addEmploye(
                request.id(),
                request.name(),
                request.surname(),
                request.email(),
                request.password(),
                request.compagnieId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(employe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDTO> getEmploye(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.findEmployeBy_Id(id));
    }

    public record CompagnieRequest(String compagniName, String city) {}

    public record EmployeRequest(Long id, String name, String surname, String email, String password, Long compagnieId) {}
}