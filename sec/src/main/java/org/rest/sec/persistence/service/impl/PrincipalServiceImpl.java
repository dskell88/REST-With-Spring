package org.rest.sec.persistence.service.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.rest.common.ClientOperation;
import org.rest.common.persistence.service.AbstractService;
import org.rest.sec.model.Principal;
import org.rest.sec.persistence.dao.IPrincipalJpaDAO;
import org.rest.sec.persistence.service.IPrincipalService;
import org.rest.sec.util.SearchSecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrincipalServiceImpl extends AbstractService<Principal> implements IPrincipalService {

    @Autowired IPrincipalJpaDAO dao;

    public PrincipalServiceImpl() {
        super(Principal.class);
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public Principal findByName(final String name) {
        return dao.findByName(name);
    }

    // Spring

    @Override
    protected final IPrincipalJpaDAO getDao() {
        return dao;
    }

    @Override
    public Specification<Principal> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchSecUtil.resolveConstraint(constraint, Principal.class);
    }

    @Override
    protected JpaSpecificationExecutor<Principal> getSpecificationExecutor() {
        return dao;
    }

}