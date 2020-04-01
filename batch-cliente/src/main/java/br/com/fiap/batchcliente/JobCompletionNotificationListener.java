package br.com.fiap.batchcliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.fiap.batchcliente.Pojo.ClientePojo;

/**
 * JobCompletionNotificationListener
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("**** UPLOAD FINALIZADO COM SUCESSO *****");

            jdbcTemplate.query("SELECT nome FROM cliente", 
                (rs, row) -> new ClientePojo(rs.getString(1))
            ).forEach(cliente -> logger.info("Encontrado <" + cliente + "> no banco de dados"));

        }else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.error("**** UPLOAD FINALIZADO COM ERRO *****");
        }

    }
}