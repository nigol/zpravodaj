package cz.nigol.zpravodaj.services.impl;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.zpravodaj.entities.*;
import cz.nigol.zpravodaj.services.ShortInfoService;

@Stateless
public class ShortInfoServiceImpl implements ShortInfoService {
    @PersistenceContext(unitName="zpravodajPU")
    private EntityManager em;

    @Override
    public List<ShortInfo> getAll() {
      TypedQuery<ShortInfo> typedQuery = em.createNamedQuery(ShortInfo.GET_ALL, ShortInfo.class);
      return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public ShortInfo getByDate(Date date) {
      TypedQuery<ShortInfo> typedQuery = em.createNamedQuery(ShortInfo.GET_BY_DATE, ShortInfo.class);
      typedQuery.setParameter(ShortInfo.DATE_PARAM, date);
      List<ShortInfo> infos = typedQuery.getResultList();
      return infos.isEmpty() ? null : infos.get(0);
    }

    @Override
    public ShortInfo save(ShortInfo shortInfo) {  
      return em.merge(shortInfo);
    }
    
    @Override
    public void delete(ShortInfo shortInfo) {
      em.remove(em.merge(shortInfo));
    }
}
