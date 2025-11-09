package com.alex.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Data
@Table(name = "submission")
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "submission_sector",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private List<Sector> sectors;

    @Column(nullable = false, name = "agreed_to_terms")
    private Boolean agreedToTerms;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Submission(Long id, String name, List<Sector> sectors, Boolean agreedToTerms ) {
        this.id = id;
        this.name = name;
        this.sectors = sectors;
        this.agreedToTerms = agreedToTerms;
    }
}
