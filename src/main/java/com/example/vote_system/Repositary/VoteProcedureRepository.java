package com.example.vote_system.Repositary;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.vote_system.Entity.User;
import com.example.vote_system.Entity.Vote;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class VoteProcedureRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Vote> getAllVotes() {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_get_all_votes", Vote.class);

		return query.getResultList();
	}

	public Long createVote(String voteName) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_create_vote");

		query.registerStoredProcedureParameter("p_vote_name", String.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.OUT);

		query.setParameter("p_vote_name", voteName);

		query.execute();

		return (Long) query.getOutputParameterValue("p_vote_id");
	}

	public User login(String username) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_login", User.class);

		query.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);

		query.setParameter("p_username", username);

		List<User> users = query.getResultList();

		return users.isEmpty() ? null : users.get(0);
	}

	public void register(String username, String password, String role) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_register");

		query.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_role", String.class, ParameterMode.IN);

		query.setParameter("p_username", username);
		query.setParameter("p_password", password);
		query.setParameter("p_role", role);

		query.execute();
	}

	public boolean hasVoted(Long userId, Long voteId) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_has_voted");

		query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.IN);

		query.setParameter("p_user_id", userId);

		query.setParameter("p_vote_id", voteId);

		Long count = ((Number) query.getSingleResult()).longValue();

		return count > 0;
	}

	public void createVoteOption(Long voteId, String optionName) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_create_vote_option");

		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_option_name", String.class, ParameterMode.IN);

		query.setParameter("p_vote_id", voteId);

		query.setParameter("p_option_name", optionName);

		query.execute();
	}

	public Vote getVoteById(Long voteId) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_get_vote_by_id", Vote.class);

		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.IN);

		query.setParameter("p_vote_id", voteId);

		List<Vote> votes = query.getResultList();

		if (votes.isEmpty()) {
			return null;
		}

		return votes.get(0);
	}

	public Long createVoteRecord(Long userId, Long voteId, String userName) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_vote");

		query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_user_name", String.class, ParameterMode.IN);

		query.setParameter("p_user_id", userId);
		query.setParameter("p_vote_id", voteId);
		query.setParameter("p_user_name", userName);

		query.execute();

		// 取 SELECT LAST_INSERT_ID()
		Object result = query.getSingleResult();

		return ((Number) result).longValue();
	}

	public void voteOption(Long recordId, Long optionId) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_vote_option");

		query.registerStoredProcedureParameter("p_record_id", Long.class, ParameterMode.IN);

		query.registerStoredProcedureParameter("p_option_id", Long.class, ParameterMode.IN);

		query.setParameter("p_record_id", recordId);

		query.setParameter("p_option_id", optionId);

		query.execute();
	}

	public void deleteVote(Long voteId) {

		StoredProcedureQuery query = em.createStoredProcedureQuery("sp_delete_vote");

		query.registerStoredProcedureParameter("p_vote_id", Long.class, ParameterMode.IN);

		query.setParameter("p_vote_id", voteId);

		query.execute();
	}
}
