/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/test/java/hibernate/DAOHibernateWithRealSessionTest.e.vm.java
 */
package com.yourcompany.yourproject.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yourcompany.yourproject.domain.Book;
import com.yourcompany.yourproject.service.BookGenerator;
import com.yourcompany.yourproject.dao.BookDao;
import com.yourcompany.yourproject.util.*;

/**
 * Integration test on BookDaoImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml" })
@Transactional
public class BookDaoImplWithRealSessionTest {
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(BookDaoImplWithRealSessionTest.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookGenerator bookGenerator;

    @Before
    public void onSetUp() throws Exception {
        //reset generator
        ValueGenerator.resetAll();
    }

    @Test
    public void testSaveAndGet() {
        Book book = bookGenerator.getBook();

        // add it to a Set before saving
        Set<Book> set = new HashSet<Book>();
        set.add(book);

        bookDao.save(book);
        entityManager.flush();

        // reload it from cache and check equality
        Book model = new Book();
        model.setBookId(book.getBookId());
        assertThat(book).isEqualTo(bookDao.get(model));

        // clear cache to force reload from db
        entityManager.clear();

        // pk are equals...
        assertThat(book.getPrimaryKey()).isEqualTo(bookDao.get(model).getPrimaryKey());

        // but since you do not use a business key, equality is lost.
        assertThat(book).isNotEqualTo(bookDao.get(model));
    }
}