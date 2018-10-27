package com.bkpmind.reports.jasper_examples.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bkpmind.reports.jasper_examples.model.Client;
import com.bkpmind.reports.jasper_examples.model.ClientByCountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientDAO {

    @Autowired
    private final JdbcTemplate jdbc;

    public ClientDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Client> getClientList() {
        List<Client> clientList = new ArrayList<>();

        String sql = "SELECT * FROM Clients ORDER BY name;";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Client client = new Client((int)row.get("ID"), (String)row.get("NAME"), (String)row.get("COUNTRY"));
            clientList.add(client);
        }

        return clientList;
    }

    public List<ClientByCountry> getClientsByCountry() {
        List<ClientByCountry> clientByCountryList = new ArrayList<>();

        String sql = "SELECT COUNT(*) as TOTAL, country from Clients GROUP BY country order by TOTAL DESC;";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        for (Map<String, Object> row : rows) {
            ClientByCountry clientByCountry = new ClientByCountry((String)row.get("COUNTRY"), (int)row.get("TOTAL"));
            clientByCountryList.add(clientByCountry);
        }

        return clientByCountryList;
    }

}