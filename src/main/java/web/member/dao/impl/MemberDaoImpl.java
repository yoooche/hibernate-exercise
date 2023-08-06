package web.member.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import web.member.dao.MemberDao;
import web.member.entity.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insert(Member member) {
		getSession().persist(member);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		Session session = getSession();
		Member member = session.get(Member.class, id);
		session.remove(member);
		return 1;
	}

	@Override
	public int update(Member member) {
		final StringBuilder hql = new StringBuilder()
			.append("UPDATE Member SET ");
		final String password = member.getPassword();
		if (password != null && !password.isEmpty()) {
			hql.append("password = :password,");
		}
		hql.append("nickname = :nickname,")
			.append("pass = :pass,")
			.append("roleId = :roleId,")
			.append("updater = :updater,")
			.append("lastUpdatedDate = NOW() ")
			.append("WHERE username = :username");
		
		Query<?> query = getSession().createQuery(hql.toString());
		if(password != null && !password.isEmpty()) {
			query.setParameter("password", password);
		}
		
		return query
			.setParameter("nickname", member.getNickname())
			.setParameter("pass", member.getPass())
			.setParameter("roleId", member.getRoleId())
			.setParameter("updater", member.getUpdater())
			.setParameter("username", member.getUsername())
			.executeUpdate();
	}
	
	@Override
	public Member selectById(Integer id) {
		return getSession().get(Member.class, id);
	}

	@Override
	public List<Member> selectAll() {
		final String hql = "FROM Member ORDER BY id";
		return getSession().createQuery(hql, Member.class).getResultList();
	}

	@Override
	public Member selectByUsername(String username) {
		final String sql = "select * from MEMBER where USERNAME = ?";
		
		Session session = getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//from MEMBER
		Root<Member> root = criteriaQuery.from(Member.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
		
		return session
				.createQuery(criteriaQuery)
				.uniqueResult();
	}
	
	@Override
	public Member selectForLogin(String username, String password) {
		final String sql = "select * from MEMBER where USERNAME = :username and PASSWORD = :password";
		
		return getSession()
				.createNativeQuery(sql, Member.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
	}
}
