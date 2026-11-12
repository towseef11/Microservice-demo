package com.productservice.demo.Model;

import lombok.Data;

@Data
public class UpdateProduct {
  private String name;
  private Double price;
  private String description;
  private Integer availability;
}
