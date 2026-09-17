package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.service.EmployerService;
import com.lacouf.rsbjwt.service.dto.CompanyDTO;
import com.lacouf.rsbjwt.service.dto.EmployerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final EmployerService employerService;

    public EmployeController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/compagnie")
    public ResponseEntity<CompanyDTO> addCompagnie(@RequestBody CompagnieRequest request) {
        CompanyDTO compagnie = employerService.addCompagnie(request.compagniName(), request.city());
        return ResponseEntity.status(HttpStatus.CREATED).body(compagnie);
    }

    @PostMapping
    public ResponseEntity<EmployerDTO> addEmploye(@RequestBody EmployeRequest request) {
        EmployerDTO employe = employerService.addEmploye(
                request.name(),
                request.surname(),
                request.email(),
                request.password(),
                request.compagnieId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(employe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDTO> getEmploye(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.findEmployeBy_Id(id));
    }

    public record CompagnieRequest(String compagniName, String city) {}

    public record EmployeRequest(Long id, String name, String surname, String email, String password, Long compagnieId) {}
}