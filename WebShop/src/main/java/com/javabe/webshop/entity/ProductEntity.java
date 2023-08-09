package com.javabe.webshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String productName;

  private int price;

  private String description;

  private String gender;

  @Column(name = "type_product")
  private String typeProduct;

  @Column(name = "image_url")
  private String imgURL;

  @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
  private List<EvaluateEntity> evaluateEntities;

  @Override
  public String toString() {
    return "ProductEntity{" +
            "id=" + id +
            ", productName='" + productName + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            ", gender='" + gender + '\'' +
            ", typeProduct='" + typeProduct + '\'' +
            ", imgURL='" + imgURL + '\'' +
            '}';
  }
}
