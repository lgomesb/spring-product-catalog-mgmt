package com.barbosa.ms.productmgmt.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "{field.name.required}")
    @NotBlank(message = "{field.name.not-blank}")
    @NotEmpty(message = "{field.name.required}")
    @Column(columnDefinition = "varchar(255) not null", unique = true)
    private String name;
    
    @Column(columnDefinition = "varchar(1) not null default 'A'")
    private String status;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by", columnDefinition = "varchar(100) not null default '99999'")
    private String createdBy;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by", columnDefinition = "varchar(100)")
    private String modifiedBy;

    @PreUpdate
    @PrePersist
    public void prePersist() {
        if(this.createdOn == null) {
            this.setCreatedOn(LocalDateTime.now());
            this.setCreatedBy("99999");
            this.setStatus("A");
        } else {
            this.setModifiedOn(LocalDateTime.now());
            this.setModifiedBy("99999");
        }
    }


}
