package com.igor.springitemsinstock.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.springitemsinstock.dao.ItemRepository;
import com.igor.springitemsinstock.models.Item;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	private ItemRepository repository;
	
	@Autowired
	public ItemController(ItemRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<Collection<Item>> getAllItems() {
		List<Item> items = repository.findAll();
		if (items != null && !items.isEmpty()) {
			return ResponseEntity.ok(items);			
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id) {
		Optional<Item> item = repository.findById(id);
		if (item.isPresent()) {
			return ResponseEntity.ok(item.get());
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		if (item != null) {
			item.setId(0);
			return ResponseEntity.ok(repository.save(item));
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Item> deleteItem(@PathVariable long id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/deposit/{id}/{amount}")
	public ResponseEntity<Item> depositAmount(@PathVariable long id, @PathVariable int amount) {
		Item item = repository.findById(id).orElse(null);
		if (item != null) {
			item.setAmount(item.getAmount() + amount);
			return ResponseEntity.ok(repository.save(item));
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/withdraw/{id}/{amount}")
	public ResponseEntity<Item> withdrawAmount(@PathVariable long id, @PathVariable int amount) throws NotEnoughItemsException {
		Item item = repository.findById(id).orElse(null);
		if (item != null) {
			if (item.getAmount() > amount) {
				item.setAmount(item.getAmount() - amount);
				return ResponseEntity.ok(repository.save(item));
			} else {
				throw new NotEnoughItemsException("There is not enough items on stock!");
			}
		}
		return ResponseEntity.noContent().build();
	}
}
