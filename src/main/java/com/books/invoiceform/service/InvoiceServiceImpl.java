package com.books.invoiceform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.invoiceform.dao.InvoiceDao;
import com.books.invoiceform.model.Invoice;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	InvoiceDao invoiceDao;

	@Autowired
	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	@Override
	public Invoice findById(Integer id) {
		return invoiceDao.findById(id);
	}
	
	@Override
	public List<Invoice> findAll() {
		return invoiceDao.findAll();
	}

	
	@Override
	public void saveOrUpdate(Invoice invoice) {

		if (findById(invoice.getInvoiceId())==null) {
			invoiceDao.save(invoice);
		} else {
			invoiceDao.update(invoice);
		}

	}


}