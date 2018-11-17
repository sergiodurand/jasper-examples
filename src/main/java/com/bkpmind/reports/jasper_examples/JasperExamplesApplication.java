package com.bkpmind.reports.jasper_examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkpmind.reports.jasper_examples.model.Client;
import com.bkpmind.reports.jasper_examples.model.ClientByCountry;
import com.bkpmind.reports.jasper_examples.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@SpringBootApplication
public class JasperExamplesApplication implements ApplicationRunner {

	// Read the property 'report-output' in application.properties file
	@Value("${report-output}")
	private String reportOutput;

	@Autowired
	private ClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(JasperExamplesApplication.class, args).close();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Access the Client service to retrieve the list of clients and
		// the list of clients grouped by country
		List<Client> clientList = clientService.getClientList();
		List<ClientByCountry> clientByCountryList = clientService.getClientByCountryList();

		// Create a JasperReport data source and ...
		JRBeanCollectionDataSource clientDS = new JRBeanCollectionDataSource(clientList);
		JRBeanCollectionDataSource clientByCountryDS = new JRBeanCollectionDataSource(clientByCountryList);

		// ... add them to the parameters Map object
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", "/");
		parameters.put("CLIENT_DS", clientDS);
		parameters.put("BY_COUNTRY_DS", clientByCountryDS);

		// Call fillReport method passing the main report and the parameters
		// To finish, export the report to a PDF file
		JasperPrint jasperPrint = JasperFillManager.fillReport(new ClassPathResource("/clients-example.jasper").getInputStream(), parameters, new JREmptyDataSource());
		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportOutput));
		pdfExporter.exportReport();
	}
}
