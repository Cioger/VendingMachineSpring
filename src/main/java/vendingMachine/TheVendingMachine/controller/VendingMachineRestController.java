package vendingMachine.TheVendingMachine.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import vendingMachine.TheVendingMachine.dao.CurrencyRepository;
import vendingMachine.TheVendingMachine.dao.ItemRepository;
import vendingMachine.TheVendingMachine.entity.Currency;
import vendingMachine.TheVendingMachine.entity.Item;
import vendingMachine.TheVendingMachine.exceptions.NotFullPaidException;
import vendingMachine.TheVendingMachine.exceptions.NotSufficientChangeException;
import vendingMachine.TheVendingMachine.exceptions.SoldOutException;

@RestController
@RequestMapping("/api")
public class VendingMachineRestController {
		
		private ItemRepository itemRepository;
		private CurrencyRepository currencyRepository;
		private double payment;
		private double change;
		
		@Autowired
		public VendingMachineRestController(ItemRepository theItemRepository,CurrencyRepository theCurrencyRepository) {
			itemRepository=theItemRepository;
			currencyRepository=theCurrencyRepository;
		}
		
		
		@GetMapping("/items")
		public List<Item> findAll(){
			return itemRepository.findAll();
		}
		
		@GetMapping("/item/{id}")
		public Optional<Item> findOne(@PathVariable int id) {
			return itemRepository.findById(id);
		}
		
		@GetMapping("/currencies")
		public List<Currency> findAllCurrencies(){
			return currencyRepository.findAll();
		}
		
		@PutMapping("/addMoney/{id}")
		public Optional<Currency> addMoney(@PathVariable int id) {
			return currencyRepository.findById(id).map(tempCurr->{
					payment+=tempCurr.getValue();
					tempCurr.setQuantity(tempCurr.getQuantity()+1);
				
				return currencyRepository.save(tempCurr);
			});
		}
		
		public Optional<Currency> subtractMoney(int id) {
			return currencyRepository.findById(id).map(tempCurr->{
					tempCurr.setQuantity(tempCurr.getQuantity()-1);
				return currencyRepository.save(tempCurr);
			});
		}
		
		@RequestMapping("/payment")
		private String viewPayment() {
			return "Your balance: "+ payment;
		}
		
		@PutMapping("/selectItem/{id}")
		public Optional<Item> selectItem(@PathVariable int id) {
			
			 return itemRepository.findById(id).map(tempItm->{
				if(payment<tempItm.getPrice()) {
					throw new NotFullPaidException("Insufficient Funds!");
				}else {
					if(tempItm.getQuantity()!=0) {
						payment-=tempItm.getPrice();
						if(payment>0) {
							System.out.println("payment before peek: " +payment);
							List<Currency> currencies=currencyRepository.findAll();
							currencies.stream().sorted(Comparator.comparing(Currency::getValue))
							.map(tempCurr->{
								while(payment>=tempCurr.getValue() && tempCurr.getQuantity()>0) {
									payment-=tempCurr.getValue();
									change+=tempCurr.getValue();
									System.out.println("payment in peek: "+payment);
									System.out.println("change in peek: "+change);
									subtractMoney(tempCurr.getId());
								}
								return tempCurr;
							});
						}
						if(payment!=0) {
							System.out.println("change exc payment: " +payment);
							throw new NotSufficientChangeException("There is not enough money in the machine...");
						}
					tempItm.setQuantity(tempItm.getQuantity()-1);
					}
					else {
						throw new SoldOutException("Item sold out!");
					}
				}
				
				return itemRepository.save(tempItm);
			});
		}
		@PutMapping("/addItem/{id}")
		public Optional<Item> addItem(@PathVariable int id) {
			return itemRepository.findById(id).map(tempItm->{
				
					tempItm.setQuantity(tempItm.getQuantity()+1);
				
				return itemRepository.save(tempItm);
			});
		}
		
}
