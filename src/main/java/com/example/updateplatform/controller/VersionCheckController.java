package com.example.updateplatform.controller;

import com.example.updateplatform.entity.AndroidVersion;
import com.example.updateplatform.entity.iOSVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.updateplatform.service.VersionCheckService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/version-check")
public class VersionCheckController {

    @Autowired
    private VersionCheckService service;

    @PostMapping("/{platform}")
    public ResponseEntity<Map<String, String>> checkVersion(
            @PathVariable String platform,
            @RequestBody Map<String, String> request) {

        String version = request.get("version");
        String status = "actual";

        if ("Android".equalsIgnoreCase(platform)) {
            status = service.checkAndroidVersion(version);
        } else if ("Ios".equalsIgnoreCase(platform)) {
            status = service.checkiOSVersion(version);
        }

        return ResponseEntity.ok(Map.of("status", status));
    }

    // CRUD для Android
    @PostMapping("/android")
    public ResponseEntity<AndroidVersion> createAndroidVersion(@RequestBody AndroidVersion version) {
        return ResponseEntity.status(201).body(service.createAndroidVersion(version));
    }

    @GetMapping("/android")
    public ResponseEntity<List<AndroidVersion>> getAllAndroidVersions() {
        return ResponseEntity.status(200).body(service.getAllAndroidVersions());
    }

    @GetMapping("/android/{id}")
    public ResponseEntity<AndroidVersion> getAndroidVersionById(@PathVariable Long id) {
        return service.getAndroidVersionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/android/{id}")
    public ResponseEntity<AndroidVersion> updateAndroidVersion(
            @PathVariable Long id, @RequestBody AndroidVersion versionDetails) {
        return ResponseEntity.ok(service.updateAndroidVersion(id, versionDetails));
    }

    @DeleteMapping("/android/{id}")
    public ResponseEntity<Void> deleteAndroidVersion(@PathVariable Long id) {
        service.deleteAndroidVersion(id);
        return ResponseEntity.noContent().build();
    }

    // CRUD для iOS
    @PostMapping("/ios")
    public ResponseEntity<iOSVersion> createiOSVersion(@RequestBody iOSVersion version) {
        return ResponseEntity.status(201).body(service.createiOSVersion(version));
    }

    @GetMapping("/ios")
    public List<iOSVersion> getAlliOSVersions() {
        return service.getAlliOSVersions();
    }

    @GetMapping("/ios/{id}")
    public ResponseEntity<iOSVersion> getiOSVersionById(@PathVariable Long id) {
        return service.getiOSVersionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ios/{id}")
    public ResponseEntity<iOSVersion> updateiOSVersion(
            @PathVariable Long id, @RequestBody iOSVersion versionDetails) {
        return ResponseEntity.ok(service.updateiOSVersion(id, versionDetails));
    }

    @DeleteMapping("/ios/{id}")
    public ResponseEntity<Void> deleteiOSVersion(@PathVariable Long id) {
        service.deleteiOSVersion(id);
        return ResponseEntity.noContent().build();
    }
}