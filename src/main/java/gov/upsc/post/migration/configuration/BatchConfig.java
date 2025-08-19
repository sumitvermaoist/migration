package gov.upsc.post.migration.configuration;

import gov.upsc.post.migration.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    private final DataSource appDataSource; // Postgres

    public BatchConfig(@Qualifier("appDataSource") DataSource appDataSource) {
        this.appDataSource = appDataSource;
    }

    @Bean
    public JdbcCursorItemReader<Customer> reader() {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .dataSource(appDataSource)   // business data comes from Postgres
                .sql("SELECT id, name FROM customer")
                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
                .name("myReader")
                .build();
    }

    @Bean
    public ItemProcessor<Customer, Customer> processor(){
        return new CustomerProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Customer> writer() {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .dataSource(appDataSource)   // write results to Postgres
                .sql("INSERT INTO Customer (id, name) VALUES (:id, :name)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step1) {
        return new JobBuilder("myJob", jobRepository)
                .start(step1)
                .build();
    }
}
