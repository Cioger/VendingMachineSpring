package vendingMachine.TheVendingMachine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vendingMachine.TheVendingMachine.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
