package com.books.invoiceform.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.books.invoiceform.model.Invoice;
import com.books.invoiceform.service.InvoiceService;
import com.books.invoiceform.validator.InvoiceFormValidator;
//import javax.validation.Valid;

@Controller
public class InvoiceController {

	private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	InvoiceFormValidator invoiceFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(invoiceFormValidator);
	}

	private InvoiceService invoiceService;

	@Autowired
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/invoices";
	}

	// list page
	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public String showAllInvoices(Model model) {

		logger.debug("showAllInvoices()");
		model.addAttribute("invoices", invoiceService.findAll());
		return "invoices/list";

	}

	// save or update invoice
	@RequestMapping(value = "/invoices", method = RequestMethod.POST)
	public String saveOrUpdateInvoice(@ModelAttribute("invoiceForm") @Validated Invoice invoice,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateInvoice() : {}", invoice);

		if (result.hasErrors()) {
			logger.debug("result.hasErrors()");
			return "invoices/invoiceform";
		} else {

			/*redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Invoice added successfully!");*/
			
			logger.debug("call saveOrUpdate()");
			invoiceService.saveOrUpdate(invoice);
			
			// POST/REDIRECT/GET
			//return "redirect:/invoices/" + invoice.getInvoiceId();

			logger.debug("redirect:/invoices");
			// POST/FORWARD/GET
			 return "redirect:/invoices";

		}

	}

	// show add invoice form
	@RequestMapping(value = "/invoices/add", method = RequestMethod.GET)
	public String showAddInvoiceForm(Model model) {

		logger.debug("showAddInvoiceForm()");

		Invoice invoice = new Invoice();
		// set default value
		//invoice.setName("abc");
		//invoice.setEmail("test@gmail.com");
		
		model.addAttribute("invoiceForm", invoice);
		return "invoices/invoiceform";

	}


	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("invoice/show");
		model.addObject("msg", "invoice not found");

		return model;

	}

}