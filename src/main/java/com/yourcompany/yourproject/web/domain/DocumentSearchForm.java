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
import com.yourcompany.yourproject.domain.Document;
import com.yourcompany.yourproject.service.DocumentService;
import com.yourcompany.yourproject.web.domain.support.GenericSearchForm;

/**
 * SearchForm for DocumentService
 */
@Component
@Scope("session")
public class DocumentSearchForm extends GenericSearchForm<Document> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static DocumentService documentService;

    private Document document = new Document();
    private SearchTemplate searchTemplate = new SearchTemplate().setSearchMode(SearchMode.ANYWHERE);

    @Autowired
    public DocumentSearchForm(DocumentService instance) {
        if (documentService == null) {
            documentService = instance;
        }
    }

    /**
     * The root model for search by example.
     */
    public Document getDocument() {
        return document;
    }

    /**
     * The default search template.
     */
    public SearchTemplate getSearchTemplate() {
        return searchTemplate;
    }

    /**
     * Prepare the search parameters and call the documentService finder.
     * Automatically called by PrimeFaces component.
     */
    @Override
    public List<Document> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, String> filters) {
        SearchTemplate st = new SearchTemplate(getSearchTemplate());

        // total count so the paginator may display the total number of pages
        this.setRowCount(documentService.findCount(getDocument(), st));

        // load one page of data 
        populateSearchTemplate(st, first, pageSize, sortField, sortOrder, filters);
        return documentService.find(getDocument(), st);
    }
}