package vendingMachine.TheVendingMachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ItemErrorResponse> handleException(SoldOutException exc){
		ItemErrorResponse error= new ItemErrorResponse(HttpStatus.NOT_FOUND.value(),exc.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ItemErrorResponse> handleException(NotFullPaidException exc){
		ItemErrorResponse error= new ItemErrorResponse(HttpStatus.PAYMENT_REQUIRED.value(),exc.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.PAYMENT_REQUIRED);
	}
	
	@ExceptionHandler
	public ResponseEntity<ItemErrorResponse> handleException(NotSufficientChangeException exc){
		ItemErrorResponse error= new ItemErrorResponse(HttpStatus.EXPECTATION_FAILED.value(),exc.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
	}

}
