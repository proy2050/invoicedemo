package com.books.invoiceform.service;

import java.util.List;

import com.books.invoiceform.model.Invoice;

public interface InvoiceService {

	Invoice findById(Integer id);
	
	List<Invoice> findAll();
	
	void saveOrUpdate(Invoice invoice);
	
}