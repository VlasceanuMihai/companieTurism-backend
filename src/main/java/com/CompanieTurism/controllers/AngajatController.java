package com.CompanieTurism.controllers;

import com.CompanieTurism.services.AngajatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angajat")
public class AngajatController {

    private final AngajatService angajatService;

    @Autowired
    public AngajatController(AngajatService angajatService) {
        this.angajatService = angajatService;
    }

    @GetMapping("/v1/angajati")
    public ResponseEntity<Object> getAllAngajati(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.angajatService.getAllAngajati(pageable));
    }
}
