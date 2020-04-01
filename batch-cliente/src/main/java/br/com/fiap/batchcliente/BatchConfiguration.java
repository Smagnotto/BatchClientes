package br.com.fiap.batchcliente;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.fiap.batchcliente.Pojo.ClientePojo;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
	public FlatFileItemReader<ClientePojo> fileReader(@Value("${input.file}") Resource resource) {
		return new FlatFileItemReaderBuilder<ClientePojo>()
				.name("Read File")
                .resource(resource)
                .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .lineTokenizer(clienteLineTokenizer())
                .fieldSetMapper(new ClienteFieldSetMapper())
				.build();
	}

    @Bean
    public ClienteItemProcessor processor() {
        return new ClienteItemProcessor();
    }

	@Bean
	public LineTokenizer clienteLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        
        tokenizer.setNames(new String[] { "nome", "matricula", "outro" });
        tokenizer.setColumns(new Range(1, 41),
                             new Range(41, 48),
                             new Range(49, 55));

		return tokenizer;
	}

	@Bean
	public JdbcBatchItemWriter<ClientePojo> databaseWriter(DataSource datasource) {
		return new JdbcBatchItemWriterBuilder<ClientePojo>()
				.dataSource(datasource)
				.sql("insert into cliente (nome) values (:nome)")
				.beanMapped()
				.build();
    }
    

    @Bean
    public Job NotificationUser(JobCompletionNotificationListener listener, Step uploadClientes) {
        return jobBuilderFactory.get("Notificacao Usuario")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(uploadClientes)
            .end()
            .build();
    }

    @Bean
    public Step UploadClientes(StepBuilderFactory stepBuilderFactory,
                               ItemReader<ClientePojo> itemReader,
                               ItemWriter<ClientePojo> itemWriter) {

        return stepBuilderFactory.get("Upload Clientes")
                .<ClientePojo, ClientePojo>chunk(10)
                .reader(itemReader)
                .processor(processor())
                .writer(itemWriter)
                .build();
    }
}