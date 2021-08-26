package vendingMachine.TheVendingMachine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vendingMachine.TheVendingMachine.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
