package com.example.heyikun.heheshenghuo.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.heyikun.heheshenghuo.modle.dao.AddressBean;
import com.example.heyikun.heheshenghuo.modle.dao.AddressDaoBean;

import com.example.heyikun.heheshenghuo.greendao.AddressBeanDao;
import com.example.heyikun.heheshenghuo.greendao.AddressDaoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig addressBeanDaoConfig;
    private final DaoConfig addressDaoBeanDaoConfig;

    private final AddressBeanDao addressBeanDao;
    private final AddressDaoBeanDao addressDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        addressBeanDaoConfig = daoConfigMap.get(AddressBeanDao.class).clone();
        addressBeanDaoConfig.initIdentityScope(type);

        addressDaoBeanDaoConfig = daoConfigMap.get(AddressDaoBeanDao.class).clone();
        addressDaoBeanDaoConfig.initIdentityScope(type);

        addressBeanDao = new AddressBeanDao(addressBeanDaoConfig, this);
        addressDaoBeanDao = new AddressDaoBeanDao(addressDaoBeanDaoConfig, this);

        registerDao(AddressBean.class, addressBeanDao);
        registerDao(AddressDaoBean.class, addressDaoBeanDao);
    }
    
    public void clear() {
        addressBeanDaoConfig.clearIdentityScope();
        addressDaoBeanDaoConfig.clearIdentityScope();
    }

    public AddressBeanDao getAddressBeanDao() {
        return addressBeanDao;
    }

    public AddressDaoBeanDao getAddressDaoBeanDao() {
        return addressDaoBeanDao;
    }

}
