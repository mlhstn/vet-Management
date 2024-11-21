package com.vetManagement.spring.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface ImodelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();
}
