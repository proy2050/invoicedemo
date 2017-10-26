package com.books.invoiceform.dao;

import java.util.List;

import com.books.invoiceform.model.Invoice;

public interface InvoiceDao {

	Invoice findById(Integer id);
	
	List<Invoice> findAll();

	void save(Invoice invoice);

	void update(Invoice invoice);
	

}