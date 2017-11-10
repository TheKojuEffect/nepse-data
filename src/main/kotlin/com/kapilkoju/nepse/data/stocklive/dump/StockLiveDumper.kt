package com.kapilkoju.nepse.data.stocklive.dump

import com.kapilkoju.nepse.data.stocklive.fetch.StockLiveFetcher
import com.kapilkoju.nepse.data.stocklive.service.StockLiveRepo
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDateTime

@Configuration
@EnableBatchProcessing
@EnableScheduling
class StockLiveDumper(private val jobBuilderFactory: JobBuilderFactory,
                      private val stepBuilderFactory: StepBuilderFactory,
                      private val jobLauncher: JobLauncher,
                      private val stockLiveFetcher: StockLiveFetcher,
                      private val stockLiveRepo: StockLiveRepo) {

    @Scheduled(cron = "25,55 * 11-14 * * SUN-FRI")
    fun schedule() {
        val now = LocalDateTime.now().toString()
        val param = JobParametersBuilder().addString("now", now).toJobParameters()
        jobLauncher.run(dumpStockLiveJob(), param)
    }

    @Bean
    fun dumpStockLiveJob(): Job {
        return jobBuilderFactory.get("dumpStockLiveJob")
                .start(dumpStockLiveStep())
                .build()
    }

    @Bean
    fun dumpStockLiveStep(): Step {
        return stepBuilderFactory.get("dumpStockLiveStep")
                .tasklet(dumpStockLiveTasklet())
                .build()
    }

    @Bean
    fun dumpStockLiveTasklet(): Tasklet {
        return Tasklet { _, _ ->
            val lives = stockLiveFetcher.getStockLives()
            stockLiveRepo.save(lives)
            RepeatStatus.FINISHED
        }
    }
}