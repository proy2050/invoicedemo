package com.books.invoiceform.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {
	// form:hidden - hidden value
	Integer invoiceId;

	// form:input - textbox
		String name;

		// form:input - textbox
		String email;

		// form:input - textbox/calender icon
		Date dueDate;
		
		// form:input - textbox
		String description;

		// form:input textbox
		Integer amount;
	
	//Total is common
	BigDecimal total;
	
	public boolean isNew() {
		return (this.invoiceId == null);
	}


	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", name=" + name + ", email=" + email + ", dueDate=" + dueDate
				+ ", description=" + description + ", amount=" + amount + ", total=" + total + "]";
	}
	
	
	

}
