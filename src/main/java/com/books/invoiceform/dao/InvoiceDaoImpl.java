package com.books.invoiceform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.books.invoiceform.model.Invoice;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Invoice> findAll() {

		String sql = "SELECT * FROM invoice";
		List<Invoice> result = namedParameterJdbcTemplate.query(sql, new InvoiceMapper());

		return result;

	}
	
	
	@Override
	public Invoice findById(Integer id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM invoice WHERE invoice_id=:id";

		Invoice result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new InvoiceMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}


		return result;

	}


	@Override
	public void save(Invoice invoice) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO invoice(name, email, due_date, description, amount) "
				+ "VALUES ( :name, :email, :dueDate, :description, :amount)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(invoice), keyHolder);
		invoice.setInvoiceId(keyHolder.getKey().intValue());
		
	}

	@Override
	public void update(Invoice invoice) {

		//TODO update query 
		String sql = "";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(invoice));

	}

	

	private SqlParameterSource getSqlParameterByModel(Invoice invoice) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("invoiceId", invoice.getInvoiceId());
		paramSource.addValue("name", invoice.getName());
		paramSource.addValue("email", invoice.getEmail());
		paramSource.addValue("dueDate", invoice.getDueDate());
		paramSource.addValue("description", invoice.getDescription());
		paramSource.addValue("amount", invoice.getAmount());

		return paramSource;
	}

	private static final class InvoiceMapper implements RowMapper<Invoice> {

		public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Invoice invoice = new Invoice();
			
			invoice.setInvoiceId(rs.getInt("invoice_id"));
			invoice.setName(rs.getString("name"));
			invoice.setEmail(rs.getString("email"));
			invoice.setDueDate(rs.getDate("due_date"));
			invoice.setDescription(rs.getString("description"));
			invoice.setAmount(rs.getInt("amount"));
			
			return invoice;
		}
	}


}