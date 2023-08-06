package app;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		//select USERNAME,NICKNAME
		CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//from MEMBER
		Root<Member> root = criteriaQuery.from(Member.class);
		//where USERNAME =? and PASSWORD =?
		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get("username"),"admin"),
				criteriaBuilder.equal(root.get("password"),"P@ssw0rd")
				
				));
		
		//select USENAME, NICKNAME
		criteriaQuery.multiselect(root.get("username"),root.get("nickname"));
		//order by  ID
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		Member member = session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(member.getNickname());
		
	}
		
		
		
		
		
		
		
		
//		TestApp app = new TestApp();
//		app.selectALL().forEach(member-> System.out.println(member.getNickname());
//		for(Member member : app.selectALL()) {
//			System.out.println(member.getNickname());
//		}
//	}
//		Member member = new Member();
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		System.out.println(app.deleteById(3));
//		member.setId(1);
//		member.setPass(false);
//		member.setRoleId(2);
//		System.out.println(app.updateById(member));
//		app.insert(member);
//		System.out.println(member.getId());



//		System.out.println(app.selectById(2).getNickname());
//	}

	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}

	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}

	}

	public int updateById(Member newMember) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member oldMember = session.get(Member.class, newMember.getId());

			// 若有傳pass,就將此pass 設定給oldMember
			final Boolean pass = newMember.getPass();
			if (pass != null) {
				oldMember.setPass(pass);
			}

			// 若有傳roleId,就將此roleId設定給oldMember
			final Integer roleId = newMember.getRoleId();
			if (roleId != null) {
				oldMember.setPass(pass);
			}

			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}

	}
	
	public Member selectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
  }
	public List<Member>selectALL() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query = session.createQuery(
					"SELECT new web.member.pojo.Member(username, nickname)FROM Member",Member.class);
			List<Member> list = query.getResultList();
			
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}return null;
  }
}
