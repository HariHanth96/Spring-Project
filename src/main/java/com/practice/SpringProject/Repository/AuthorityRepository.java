package com.practice.SpringProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.SpringProject.Model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>{

}
