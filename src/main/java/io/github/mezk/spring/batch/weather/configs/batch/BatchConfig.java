package io.github.mezk.spring.batch.weather.configs.batch;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.github.mezk.spring.batch.weather.domain.weather.batch.WeatherForecastProcessor;
import io.github.mezk.spring.batch.weather.domain.weather.models.City;
import io.github.mezk.spring.batch.weather.domain.weather.models.Weather;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class BatchConfig {

    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final WeatherForecastProcessor processor;

    @Autowired
    public BatchConfig(
        JobLauncher jobLauncher,
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory,
        EntityManagerFactory entityManagerFactory,
        WeatherForecastProcessor processor
    ) {
        this.jobLauncher = jobLauncher;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.processor = processor;
    }

    @Scheduled(initialDelay = 10_000, fixedDelay = 20_000)
    public void perform() throws Exception {

        System.out.println("Job Started at :" + new Date());

        final JobExecution execution =
            jobLauncher.run(weatherFromInternetToDatabaceJob(), new JobParameters());

        System.out.println("Job finished with status :" + execution.getStatus());
    }

    @Bean
    public Job weatherFromInternetToDatabaceJob() {
        return jobBuilderFactory
            .get("job name")
            .incrementer(new RunIdIncrementer())
            .flow(step())
            .end()
            .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
            .get("step name")
            .allowStartIfComplete(true)
            .<City, Weather>chunk(1)
            .reader(cityItemReader())
            .processor(processor)
            .writer(weatherItemWriter())
            .build();
    }

    @Bean
    public ItemWriter<Weather> weatherItemWriter() {
        final JpaItemWriter<Weather> iw = new JpaItemWriter<>();
        iw.setEntityManagerFactory(entityManagerFactory);
        return iw;
    }

    @Bean
    public ItemReader<City> cityItemReader() {
        final JpaPagingItemReader<City> ir = new JpaPagingItemReader<>();
        ir.setEntityManagerFactory(entityManagerFactory);
        ir.setQueryString("SELECT c FROM City AS c");
        return ir;
    }

}
