package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.constants.CodeGeneratorType;
import com.hust.smart_apartment.entity.CodeGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGeneratorRepository extends JpaRepository<CodeGenerator, CodeGeneratorType> {
}
