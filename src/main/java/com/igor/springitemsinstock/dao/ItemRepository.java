package com.igor.springitemsinstock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.springitemsinstock.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
