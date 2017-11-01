package com.kapilkoju.nepse.data.todaysprice.dump

import com.kapilkoju.nepse.data.todaysprice.fetch.TodaysPriceFetcher
import com.kapilkoju.nepse.data.todaysprice.service.TodaysPriceRepo
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class TodaysPriceDumper(private val jobBuilderFactory: JobBuilderFactory,
                        private val stepBuilderFactory: StepBuilderFactory,
                        private val todaysPriceFetcher: TodaysPriceFetcher,
                        private val todaysPriceRepo: TodaysPriceRepo) {

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