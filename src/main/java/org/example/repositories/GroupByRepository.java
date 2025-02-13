package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.example.models.CountResponse;
import org.example.models.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupByRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List groupByParam(String param){
        if(param.equals("amenities")){
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<CountResponse> query = criteriaBuilder.createQuery(CountResponse.class);
            Root<Hotel> root = query.from(Hotel.class);
            Join<Hotel, String> amenities = root.join("amenities", JoinType.INNER);
            TypedQuery<CountResponse> res = entityManager.createQuery(
                    query.multiselect(
                    amenities.alias("name"),
                    criteriaBuilder.count(root).alias("count"))
                            .groupBy(amenities));
            return res.getResultList();
        }
        String hql = "select h."+param+",count(*) from Hotel h group by h."+param;
        Query hqlQuery = entityManager.createQuery(hql,CountResponse.class);
        return hqlQuery.getResultList();
    }
}
