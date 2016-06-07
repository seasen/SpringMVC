package bjsxt.hibernate;

import org.aspectj.lang.annotation.Before;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateORMappingTest {
	private static SessionFactory sessionFactory;
	@BeforeClass
	public static void beforeClass() {
		Configuration configiguration = new Configuration().configure();
		sessionFactory = configiguration.buildSessionFactory(
						new StandardServiceRegistryBuilder().applySettings(configiguration.getProperties()).build());

	}
	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
	@Test
	public void testSchemaExport() {
		//new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( serviceRegistry ).buildMetadata();
		new SchemaExport(metadata).create(true, true);
	}
}
