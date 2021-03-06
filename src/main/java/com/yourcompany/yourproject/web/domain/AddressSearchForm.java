/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces:src/main/java/SearchForm.e.vm.java
 */
package com.yourcompany.yourproject.web.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yourcompany.yourproject.dao.support.SearchTemplate;
import com.yourcompany.yourproject.dao.support.SearchMode;
import com.yourcompany.yourproject.domain.Address;
import com.yourcompany.yourproject.service.AddressService;
import com.yourcompany.yourproject.web.domain.support.GenericSearchForm;

/**
 * SearchForm for AddressService
 */
@Component
@Scope("session")
public class AddressSearchForm extends GenericSearchForm<Address> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static AddressService addressService;

    private Address address = new Address();
    private SearchTemplate searchTemplate = new SearchTemplate().setSearchMode(SearchMode.ANYWHERE);

    @Autowired
    public AddressSearchForm(AddressService instance) {
        if (addressService == null) {
            addressService = instance;
        }
    }

    /**
     * The root model for search by example.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * The default search template.
     */
    public SearchTemplate getSearchTemplate() {
        return searchTemplate;
    }

    /**
     * Prepare the search parameters and call the addressService finder.
     * Automatically called by PrimeFaces component.
     */
    @Override
    public List<Address> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, String> filters) {
        SearchTemplate st = new SearchTemplate(getSearchTemplate());

        // total count so the paginator may display the total number of pages
        this.setRowCount(addressService.findCount(getAddress(), st));

        // load one page of data 
        populateSearchTemplate(st, first, pageSize, sortField, sortOrder, filters);
        return addressService.find(getAddress(), st);
    }
}