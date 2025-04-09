package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			/*
			 * Hibernate - Query Language ( hql )
			 * hql = FROM City c WHERE c.countryCode='TUR' OR c.countryCode='USA'
			 * hql = FROM City c WHERE c.name LIKE 'kar%'
			 
			***	SELECT
			String hql = "FROM City c ORDER BY c.name";
			List<City> cities = session.createQuery(hql).getResultList();
			for(City city:cities) {
				System.out.println(city.getName());
			}
			
			*/
			String hql = "SELECT c.countryCode FROM City c GROUP BY c.countryCode";
			List<String> countryCodes = session.createQuery(hql).getResultList();
			for(String code:countryCodes) {
				System.out.println(code);
			}
			
			/*
			//	CRUD	Create Read Update Delete 
			
			***	INSERT
			City city = new City();
			city.setName("Ankara06");
			city.setCountryCode("TUR");
			city.setDistrict("TUR");
			city.setPopulation(698000);
			session.save(city);			
			
			*** UPDATE
			City city = session.get(City.class, 4080);
			System.out.println(city.getPopulation());
			city.setPopulation(598000);
			session.save(city);	
			System.out.println(city.getPopulation());
				
			*** DELETE
			City city = session.get(City.class, 4080);
			session.delete(city);
			*/
			
			session.getTransaction().commit();
		}catch(Exception e) {
			session.getTransaction().rollback();
		}finally {
			factory.close();
		}

	}

}




	
