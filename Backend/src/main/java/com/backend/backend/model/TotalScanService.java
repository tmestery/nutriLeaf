package com.backend.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalScanService {

    @Autowired
    private TotalScanRepository scanRepository;

    @Autowired
    public TotalScanService(TotalScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    public int getWebUserTotalScans(String username) {
        return scanRepository.getTotalScans(username);
    }
}