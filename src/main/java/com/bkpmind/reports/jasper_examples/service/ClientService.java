package com.bkpmind.reports.jasper_examples.service;

import java.util.List;

import com.bkpmind.reports.jasper_examples.dao.ClientDAO;
import com.bkpmind.reports.jasper_examples.model.Client;
import com.bkpmind.reports.jasper_examples.model.ClientByCountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public List<Client> getClientList() {
        return clientDAO.getClientList();
    }

    public List<ClientByCountry> getClientByCountryList() {
        return clientDAO.getClientsByCountry();
    }

}