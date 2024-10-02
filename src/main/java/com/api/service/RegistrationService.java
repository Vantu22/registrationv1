package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getReg() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dto = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
         return dto;
    }

    public RegistrationDto createReg(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration saved = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(saved);
        return dto;
    }
    //copy dto to entity
    Registration mapToEntity(RegistrationDto registrationDto)
    {
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;
    }
    //copy entity to dto
    RegistrationDto mapToDto(Registration registration)
    {
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
        return dto;
    }

    public void deleteReg(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateReg(Registration registration, long id) {
        Registration r=registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration saved = registrationRepository.save(r);
        return saved;
    }

    public RegistrationDto getRegById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not Found"));
        return mapToDto(registration);
    }
}
