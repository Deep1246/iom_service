package com.example.demo.service;

import com.example.demo.dto.IOMRequestDto;
import com.example.demo.entity.IOMRequestMaster;
import com.example.demo.repository.IOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOMRequestService {

    @Autowired
    private IOMRepository iomRepository;

    public IOMRequestMaster  createIOMRequest(IOMRequestDto iomRequestDto){

        IOMRequestMaster build = IOMRequestMaster.builder()
                .title(iomRequestDto.getTitle())
                .category(iomRequestDto.getCategory())
                .subCategory(iomRequestDto.getSubCategory())
                .desc(iomRequestDto.getDescription())
                .build();

        IOMRequestMaster save = iomRepository.save(build);
        return save;

    }

}
