package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Audited
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @CreatedDate
    private Date createdDate;

    @NotAudited
    @OneToMany(mappedBy = "shop")
    private Collection<Employees> employees;

    @NotAudited
    @OneToMany(mappedBy = "shop")
    private Collection<Trucks> trucks;

    @NotAudited
    @ManyToMany(mappedBy = "shops")
    private Collection<Products> products;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void prePersist() {
        this.createdDate = new Date();
    }
}