package tacos.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@Configuration
public class AppConfig {
//
////
////@Bean
////    public DataSource dataSource(){
////        DriverManagerDataSource ds=new DriverManagerDataSource();
////        ds.setDriverClassName("org.h2.Driver");
////        ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
////        ds.setUsername("sa");
////        ds.setPassword("");
////        return ds;
////
////    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}