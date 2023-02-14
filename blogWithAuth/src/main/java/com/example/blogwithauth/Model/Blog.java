package com.example.blogwithauth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

   @Column
   private  String title;

 @Column
 private  String  body;



    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}
