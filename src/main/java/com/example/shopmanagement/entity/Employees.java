package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Audited
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private Long salary;

    @CreatedDate
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void prePersist() {
        this.createdDate = new Date();
    }
}
