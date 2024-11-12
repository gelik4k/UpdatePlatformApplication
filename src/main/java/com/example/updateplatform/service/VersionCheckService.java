package com.example.updateplatform.service;

import com.example.updateplatform.entity.AndroidVersion;
import com.example.updateplatform.entity.iOSVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.updateplatform.repository.AndroidVersionRepository;
import com.example.updateplatform.repository.iOSVersionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VersionCheckService {

    @Autowired
    private AndroidVersionRepository androidRepo;

    @Autowired
    private iOSVersionRepository iosRepo;

    public String checkAndroidVersion(String version) {
        return androidRepo.findAll().stream()
                .filter(v -> v.getVersion().equals(version))
                .map(AndroidVersion::getStatus)
                .findFirst()
                .orElse("actual");
    }

    public String checkiOSVersion(String version) {
        return iosRepo.findAll().stream()
                .filter(v -> v.getVersion().equals(version))
                .map(iOSVersion::getStatus)
                .findFirst()
                .orElse("actual");
    }

    public List<AndroidVersion> getAllAndroidVersions() {
        return androidRepo.findAll();
    }

    public Optional<AndroidVersion> getAndroidVersionById(Long id) {
        return androidRepo.findById(id);
    }

    public AndroidVersion createAndroidVersion(AndroidVersion version) {
        return androidRepo.save(version);
    }

    public AndroidVersion updateAndroidVersion(Long id, AndroidVersion versionDetails) {
        return androidRepo.findById(id).map(version -> {
            version.setVersion(versionDetails.getVersion());
            return androidRepo.save(version);
        }).orElseThrow(() -> new RuntimeException("Version not found with id " + id));
    }

    public void deleteAndroidVersion(Long id) {
        androidRepo.deleteById(id);
    }

    public List<iOSVersion> getAlliOSVersions() {
        return iosRepo.findAll();
    }

    public Optional<iOSVersion> getiOSVersionById(Long id) {
        return iosRepo.findById(id);
    }

    public iOSVersion createiOSVersion(iOSVersion version) {
        return iosRepo.save(version);
    }

    public iOSVersion updateiOSVersion(Long id, iOSVersion versionDetails) {
        return iosRepo.findById(id).map(version -> {
            version.setVersion(versionDetails.getVersion());
            return iosRepo.save(version);
        }).orElseThrow(() -> new RuntimeException("Version not found with id " + id));
    }

    public void deleteiOSVersion(Long id) {
        iosRepo.deleteById(id);
    }
}
