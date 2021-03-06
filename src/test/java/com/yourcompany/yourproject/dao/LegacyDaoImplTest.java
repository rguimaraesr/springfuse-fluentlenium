/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend:src/test/java/hibernate/DAOHibernateTest.e.vm.java
 */
package com.yourcompany.yourproject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.Session;

import com.yourcompany.yourproject.dao.support.NamedQueryUtil;
import com.yourcompany.yourproject.dao.support.SearchTemplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import com.yourcompany.yourproject.domain.*;
import com.yourcompany.yourproject.util.*;

/**
 * Unit test on LegacyDaoImpl
 */
public class LegacyDaoImplTest {

    public LegacyDaoImpl legacyDao;
    public EntityManager entityManager;
    public Session session;
    public NamedQueryUtil namedQueryUtil;
    public MockCriteriaUtil criteriaUtil;

    @Before
    public void setUp() {
        entityManager = mock(EntityManager.class);
        session = mock(Session.class);
        namedQueryUtil = mock(NamedQueryUtil.class);
        criteriaUtil = new MockCriteriaUtil();

        legacyDao = new LegacyDaoImpl();
        legacyDao.setEntityManager(entityManager);
        legacyDao.setNamedQueryUtil(namedQueryUtil);
    }

    @Test
    public void testGetReturnsNonNullWhenPkIsSet() {
        Legacy model = new Legacy();
        model.setCode(ValueGenerator.getUniqueString(8));
        model.setDept(ValueGenerator.getUniqueNumeric(Integer.class, "2147483647"));
        model.setName(ValueGenerator.getUniqueString(16));

        when(entityManager.find(eq(Legacy.class), any())).thenReturn(model);

        Legacy result = legacyDao.get(model);

        assertThat(result).isNotNull();
        verify(entityManager, times(1)).find(eq(Legacy.class), any());
    }

    @Test
    public void testGetReturnsNullWhenPkIsSet() {
        Legacy model = new Legacy();
        model.setCode(ValueGenerator.getUniqueString(8));
        model.setDept(ValueGenerator.getUniqueNumeric(Integer.class, "2147483647"));
        model.setName(ValueGenerator.getUniqueString(16));

        when(entityManager.find(eq(Legacy.class), any())).thenReturn(null);

        Legacy result = legacyDao.get(model);

        assertThat(result).isNull();
        verify(entityManager, times(1)).find(eq(Legacy.class), any());
    }

    @Test
    public void testSave() {
        List<Legacy> legacies = new ArrayList<Legacy>();
        legacies.add(new Legacy());
        legacies.add(new Legacy());

        legacyDao.save(legacies);

        verify(entityManager, times(2)).persist(any());
    }

    @Test
    public void testDelete() {
        Legacy model1 = new Legacy();
        model1.setCode(ValueGenerator.getUniqueString(8));
        model1.setDept(ValueGenerator.getUniqueNumeric(Integer.class, "2147483647"));
        model1.setName(ValueGenerator.getUniqueString(16));

        Legacy model2 = new Legacy();
        model2.setCode(ValueGenerator.getUniqueString(8));
        model2.setDept(ValueGenerator.getUniqueNumeric(Integer.class, "2147483647"));
        model2.setName(ValueGenerator.getUniqueString(16));

        List<Legacy> legacies = new ArrayList<Legacy>();
        legacies.add(model1);
        legacies.add(model2);

        when(entityManager.contains(any())).thenReturn(true);

        legacyDao.delete(legacies);

        verify(entityManager, times(2)).remove(any());
    }

    @Test
    public void testFindUseNamedQueryUtil() {
        Legacy model = new Legacy();
        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setNamedQuery("myNamedQuery");
        List<Legacy> legacies = new ArrayList<Legacy>();

        when(namedQueryUtil.findByNamedQuery(any(SearchTemplate.class), any())).thenReturn((List) legacies);

        List<Legacy> result = legacyDao.find(model, searchTemplate);

        assertThat(result).isSameAs(legacies);
        verify(namedQueryUtil, times(1)).findByNamedQuery(any(SearchTemplate.class), any());

    }

    @Test
    public void testFindCountUseNamedQueryUtil() {
        final int expectedResult = 10;
        Legacy model = new Legacy();
        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setNamedQuery("myNamedQuery");

        when(namedQueryUtil.numberByNamedQuery(any(SearchTemplate.class), any())).thenReturn(expectedResult);

        int result = legacyDao.findCount(model, searchTemplate);

        assertThat(result).isEqualTo(expectedResult);
        verify(namedQueryUtil, times(1)).numberByNamedQuery(any(SearchTemplate.class), any());
    }

    @Test
    public void testFindCountCaseNullResultCriteria() {
        Legacy model = new Legacy();
        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setCacheable(false);

        Criteria criteria = criteriaUtil.prepareThenReturnCriteria(entityManager, session, Legacy.class);
        when(criteria.uniqueResult()).thenReturn(null);

        int result = legacyDao.findCount(model, searchTemplate);

        assertThat(result).isEqualTo(0);
        verify(criteria, times(1)).uniqueResult();
    }

    @Test
    public void testFindCountCaseNotNullResultCriteria() {
        final int expectedResult = 10;
        Legacy model = new Legacy();
        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setCacheable(false);

        Criteria criteria = criteriaUtil.prepareThenReturnCriteria(entityManager, session, Legacy.class);
        when(criteria.uniqueResult()).thenReturn(expectedResult);

        int result = legacyDao.findCount(model, searchTemplate);

        assertThat(result).isEqualTo(expectedResult);
        verify(criteria, times(1)).uniqueResult();
    }

    @Test
    public void testGetLocalSearchTemplateWithNullSearchTemplate() {
        String cacheRegion = "cacheRegion";
        boolean cacheable = true;
        legacyDao.setCacheable(cacheable);
        legacyDao.setCacheRegion(cacheRegion);

        SearchTemplate result = legacyDao.getLocalSearchTemplate(null);

        assertThat(result.getCacheRegion()).isEqualTo(cacheRegion);
        assertThat(result.isCacheable()).isEqualTo(cacheable);
    }

    @Test
    public void testGetLocalSearchTemplateWithDefaultSearchTemplate() {
        String cacheRegion = "cacheRegion";
        boolean cacheable = true;
        legacyDao.setCacheable(cacheable);
        legacyDao.setCacheRegion(cacheRegion);
        SearchTemplate defaultSearchTemplate = new SearchTemplate().setCacheable(null);

        SearchTemplate result = legacyDao.getLocalSearchTemplate(defaultSearchTemplate);

        assertThat(result.getCacheRegion()).isEqualTo(cacheRegion);
        assertThat(result.isCacheable()).isEqualTo(cacheable);
    }

    @Test
    public void testGetLocalSearchTemplateWithNormalSearchTemplate() {
        String normalCacheRegion = "normalcacheRegion";
        boolean normalCacheable = false;
        legacyDao.setCacheable(true);
        legacyDao.setCacheRegion("cacheRegion");

        SearchTemplate normalSearchTemplate = new SearchTemplate();
        normalSearchTemplate.setCacheable(normalCacheable);
        normalSearchTemplate.setCacheRegion(normalCacheRegion);

        SearchTemplate result = legacyDao.getLocalSearchTemplate(normalSearchTemplate);

        assertThat(result.getCacheRegion()).isEqualTo(normalCacheRegion);
        assertThat(result.isCacheable()).isEqualTo(normalCacheable);
    }
}