package com.hust.smart_apartment.entity;


import com.hust.smart_apartment.constants.CodeGeneratorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "code_generators")
public class CodeGenerator {

    @Id
    private CodeGeneratorType prefix;
    private int lastIndex=0;
}
