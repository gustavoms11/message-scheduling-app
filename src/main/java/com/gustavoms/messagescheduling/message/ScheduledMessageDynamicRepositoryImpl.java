package com.gustavoms.messagescheduling.message;

import com.gustavoms.messagescheduling.data.QueryManipulator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;

public class ScheduledMessageDynamicRepositoryImpl implements ScheduledMessageDynamicRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ScheduledMessage> search(ScheduledMessageSearchVO vo) {
        var sql = new StringBuilder("SELECT sm FROM ScheduledMessage sm ");
        var params = new StringBuilder();
        var paramsMap = new LinkedHashMap<String, Object>();

        if (vo.getStatus() != null) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.status = :status ");
            paramsMap.put("status", vo.getStatus());
        }

        if (vo.getReceiverId() != null
                && !vo.getReceiverId().isEmpty()) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.receiverId = :receiverId ");
            paramsMap.put("receiverId", vo.getReceiverId());
        }

        if (vo.getCreatedAtStartDate() != null) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.createdAt >= :createdAtStartDate ");
            paramsMap.put("createdAtStartDate", vo.getCreatedAtStartDate());
        }

        if (vo.getCreatedAtEndDate() != null) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.createdAt <= :createdAtEndDate ");
            paramsMap.put("createdAtEndDate", vo.getCreatedAtEndDate());
        }

        if (vo.getScheduledToStartDate() != null) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.scheduledTo >= :scheduledToStartDate ");
            paramsMap.put("scheduledToStartDate", vo.getScheduledToStartDate());
        }

        if (vo.getScheduledToEndDate() != null) {
            params.append(QueryManipulator.whereOrAnd(params));
            params.append("sm.scheduledTo <= :scheduledToEndDate ");
            paramsMap.put("scheduledToEndDate", vo.getScheduledToEndDate());
        }

        sql.append(params.toString());
        var query = entityManager.createQuery(
                sql.toString(), ScheduledMessage.class);

        for (var param : paramsMap.keySet()) {
            query.setParameter(param, paramsMap.get(param));
        }

        return query.getResultList();
    }
}
