package com.example.batchprocessing.batch;

import com.example.batchprocessing.dto.TransactionDto;
import com.example.batchprocessing.entities.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;


@EnableBatchProcessing
@Configuration
public class JobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CostumItemWriter writer;
    @Autowired
    private CostumItemProcessor processor;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private Environment env;

    //@Bean
    public Step step(){
        Step step =  stepBuilderFactory.get("ReadProcessWrite")
                .<TransactionDto, Transaction>chunk(2)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
        return  step;
    }
    //@Bean
    public Job marchallCsvFile() throws Exception {
        return new JobBuilder("myJob").start(step()).repository(jobRepository).build();
    }

    //@Bean
    public FlatFileItemReader<TransactionDto> reader(){
        //System.out.println("Reader called!");
        FlatFileItemReader<TransactionDto> fileReader = new FlatFileItemReader<TransactionDto>();
        fileReader.setLineMapper(lineMapper());
        fileReader.setResource(new ClassPathResource(env.getProperty("resources.csv-file")));
        return fileReader;
    }

    //@Bean
    public LineMapper<TransactionDto> lineMapper(){
        DefaultLineMapper<TransactionDto> transactionLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer transactionLineTokenizer = new DelimitedLineTokenizer();
        transactionLineTokenizer.setDelimiter(",");
        transactionLineTokenizer.setNames(new String[]{
                "idTransaction",
                "compteId",
                "montant",
                "dateTransaction"
        });
        transactionLineMapper.setLineTokenizer(transactionLineTokenizer);
        BeanWrapperFieldSetMapper<TransactionDto> transactionInformationMapper =
                new BeanWrapperFieldSetMapper<>();
        transactionInformationMapper.setTargetType(TransactionDto.class);
        transactionLineMapper.setFieldSetMapper(transactionInformationMapper);
        return transactionLineMapper;
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    @Scheduled(fixedDelay = 300000, initialDelay = 0)
    public void scheduleFixedDelayTask() throws Exception {
        JobParameters parameters = new JobParametersBuilder().addLong("Current Time", new Long(System.currentTimeMillis())).toJobParameters();
        jobLauncher().run(marchallCsvFile(), parameters);
    }
}

