package app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Emp emp = session.get(Emp.class,7369);
		Dept dept = emp.getDept();
		List<Emp> emps = dept.getEmps();
		for (Emp tmp: emps) {
			System.out.println(tmp.getEname());
		}
		
	
	}

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
