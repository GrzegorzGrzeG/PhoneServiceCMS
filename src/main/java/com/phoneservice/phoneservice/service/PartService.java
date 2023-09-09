package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Part;
import com.phoneservice.phoneservice.repository.PartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    @Autowired
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void newPart(Part part) {
        partRepository.save(part);
    }

    public List<Part> allParts() {
        return partRepository.findAll();
    }

    @Transactional
    public void updateQuantity(Long id, Integer quantity, Double price) {
        partRepository.updateById(id, quantity, price);
    }

    public Part findById(Long id) {
        return partRepository.findPartById(id);
    }


}
