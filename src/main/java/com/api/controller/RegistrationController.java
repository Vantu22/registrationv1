package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAll()
    {
        List<RegistrationDto> dto=registrationService.getReg();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createReg(@Valid  @RequestBody RegistrationDto registrationDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto dto=registrationService.createReg(registrationDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteReg(@RequestParam long id)
    {
        registrationService.deleteReg(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateReg(@RequestBody Registration registration,@PathVariable long id)
    {
        Registration update=registrationService.updateReg(registration,id);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegById(@PathVariable long id)
    {
        RegistrationDto dto=registrationService.getRegById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
