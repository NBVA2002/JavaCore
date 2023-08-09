package com.javabe.webshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluate")
public class EvaluateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double rate;
    private String comment;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pid") // thông qua khóa ngoại address_id
    private ProductEntity productEntity;

    @Override
    public String toString() {
        return "EvaluateEntity{" +
                "id=" + id +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", productEntity=" + productEntity +
                '}';
    }
}
