package com.SpringREST.SpringREST.controller;

import com.SpringREST.SpringREST.model.CloudVendor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloudvendor")
public class CloudAPIService {

    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable String vendorId) {
        // Hardcoded response (replace with database/service call later)
        return new CloudVendor(vendorId, "Amazon Web Services", "USA", "1-800-123-4567");
    }
}