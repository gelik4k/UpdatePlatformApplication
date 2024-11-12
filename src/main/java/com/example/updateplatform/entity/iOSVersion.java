package com.example.updateplatform.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "ios_versions")
public class iOSVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String platform;
}
