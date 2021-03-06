/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/test/java/manager/ManagerImplTest.e.vm.java
 */
package com.yourcompany.yourproject.service;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.yourcompany.yourproject.domain.ContactInfo;
import com.yourcompany.yourproject.service.ContactInfoServiceImpl;
import com.yourcompany.yourproject.dao.ContactInfoDao;
import com.yourcompany.yourproject.domain.enums.CivilityEnum;
import com.yourcompany.yourproject.dao.support.SearchTemplate;

/**
 * Unit test on ContactInfoServiceImpl
 */
public class ContactInfoServiceImplTest {

    private ContactInfoServiceImpl contactInfoServiceImpl;
    private ContactInfoDao contactInfoDao;

    @Before
    public void setUp() {
        // called before each test.
        contactInfoServiceImpl = new ContactInfoServiceImpl();
        contactInfoDao = mock(ContactInfoDao.class);
        contactInfoServiceImpl.setContactInfoDao(contactInfoDao);
    }

    @Test
    public void testFindUniqueOrNoneCaseNone() {
        List<ContactInfo> none = new ArrayList<ContactInfo>();

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(none);

        ContactInfo result = contactInfoServiceImpl.findUniqueOrNone(new ContactInfo());

        assertThat(result).isNull();
        verify(contactInfoDao, times(1)).find(any(ContactInfo.class), any(SearchTemplate.class));
    }

    @Test
    public void testFindUniqueOrNoneCaseUnique() {
        List<ContactInfo> unique = new ArrayList<ContactInfo>();
        unique.add(new ContactInfo());

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(unique);

        ContactInfo result = contactInfoServiceImpl.findUniqueOrNone(new ContactInfo());

        assertThat(result).isNotNull();
        verify(contactInfoDao, times(1)).find(any(ContactInfo.class), any(SearchTemplate.class));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindUniqueOrNoneCaseMultiple() {
        List<ContactInfo> multiple = new ArrayList<ContactInfo>();
        multiple.add(new ContactInfo());
        multiple.add(new ContactInfo());

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(multiple);

        contactInfoServiceImpl.findUniqueOrNone(new ContactInfo());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindUniqueCaseNone() {
        List<ContactInfo> none = new ArrayList<ContactInfo>();

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(none);

        contactInfoServiceImpl.findUnique(new ContactInfo());
    }

    @Test
    public void testFindUniqueCaseUnique() {
        List<ContactInfo> unique = new ArrayList<ContactInfo>();
        unique.add(new ContactInfo());

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(unique);

        ContactInfo result = contactInfoServiceImpl.findUnique(new ContactInfo());

        assertThat(result).isNotNull();
        verify(contactInfoDao, times(1)).find(any(ContactInfo.class), any(SearchTemplate.class));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindUniqueCaseMultiple() {
        List<ContactInfo> multiple = new ArrayList<ContactInfo>();
        multiple.add(new ContactInfo());
        multiple.add(new ContactInfo());

        when(contactInfoDao.find(any(ContactInfo.class), any(SearchTemplate.class))).thenReturn(multiple);

        contactInfoServiceImpl.findUnique(new ContactInfo());
    }
}