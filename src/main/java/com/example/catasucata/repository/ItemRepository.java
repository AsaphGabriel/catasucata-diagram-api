package com.example.catasucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.catasucata.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
