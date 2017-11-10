package com.kapilkoju.nepse.data.todaysprice.dump

import com.kapilkoju.nepse.data.todaysprice.fetch.TodaysPriceFetcher
import com.kapilkoju.nepse.data.todaysprice.service.TodaysPriceRepo
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
import java.time.LocalDate


@Configuration
@EnableBatchProcessing
@EnableScheduling
class TodaysPriceDumper(private val jobBuilderFactory: JobBuilderFactory,
                        private val stepBuilderFactory: StepBuilderFactory,
                        private val jobLauncher: JobLauncher,
                        private val todaysPriceFetcher: TodaysPriceFetcher,
                        private val todaysPriceRepo: TodaysPriceRepo) {

    @Scheduled(cron = "0 15 16 * * SUN-THU")
    fun schedule() {
        val today = LocalDate.now().toString()
        val param = JobParametersBuilder().addString("today", today).toJobParameters()
        jobLauncher.run(dumpTodaysPriceJob(), param)
    }

    @Bean
    fun dumpTodaysPriceJob(): Job {
        return jobBuilderFactory.get("dumpTodaysPriceJob")
                .start(dumpTodaysPriceStep())
                .build()
    }

    @Bean
    fun dumpTodaysPriceStep(): Step {
        return stepBuilderFactory.get("dumpTodaysPriceStep")
                .tasklet(dumpTodaysPriceTasklet())
                .build()
    }

    @Bean
    fun dumpTodaysPriceTasklet(): Tasklet {
        return Tasklet { _, _ ->
            val prices = todaysPriceFetcher.getTodaysPrices()
            todaysPriceRepo.save(prices)
            RepeatStatus.FINISHED
        }
    }
}