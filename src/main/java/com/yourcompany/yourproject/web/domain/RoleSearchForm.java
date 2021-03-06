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
import com.yourcompany.yourproject.domain.Role;
import com.yourcompany.yourproject.service.RoleService;
import com.yourcompany.yourproject.web.domain.support.GenericSearchForm;

/**
 * SearchForm for RoleService
 */
@Component
@Scope("session")
public class RoleSearchForm extends GenericSearchForm<Role> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static RoleService roleService;

    private Role role = new Role();
    private SearchTemplate searchTemplate = new SearchTemplate().setSearchMode(SearchMode.ANYWHERE);

    @Autowired
    public RoleSearchForm(RoleService instance) {
        if (roleService == null) {
            roleService = instance;
        }
    }

    /**
     * The root model for search by example.
     */
    public Role getRole() {
        return role;
    }

    /**
     * The default search template.
     */
    public SearchTemplate getSearchTemplate() {
        return searchTemplate;
    }

    /**
     * Prepare the search parameters and call the roleService finder.
     * Automatically called by PrimeFaces component.
     */
    @Override
    public List<Role> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        SearchTemplate st = new SearchTemplate(getSearchTemplate());

        // total count so the paginator may display the total number of pages
        this.setRowCount(roleService.findCount(getRole(), st));

        // load one page of data 
        populateSearchTemplate(st, first, pageSize, sortField, sortOrder, filters);
        return roleService.find(getRole(), st);
    }
}