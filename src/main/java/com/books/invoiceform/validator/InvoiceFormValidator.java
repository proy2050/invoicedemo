package com.books.invoiceform.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.books.invoiceform.model.Invoice;
import com.books.invoiceform.service.InvoiceService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class InvoiceFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Invoice.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Invoice invoice = (Invoice) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.invoiceForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.invoiceForm.email");
		
		if(!emailValidator.valid(invoice.getEmail())){
			errors.rejectValue("email", "Pattern.invoiceForm.email");
		}
		
	}

}