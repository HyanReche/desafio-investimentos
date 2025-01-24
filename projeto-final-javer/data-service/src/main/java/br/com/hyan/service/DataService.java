package br.com.hyan.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {
		jdbcTemplate.execute("RUNSCRIPT FROM 'classpath:/sql/clientes-create.sql'");
		jdbcTemplate.execute("RUNSCRIPT FROM 'classpath:/sql/clientes-insert.sql'");
	}
}
