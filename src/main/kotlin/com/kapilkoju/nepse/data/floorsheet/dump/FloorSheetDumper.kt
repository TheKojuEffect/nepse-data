package com.kapilkoju.nepse.data.floorsheet.dump

import com.kapilkoju.nepse.data.floorsheet.FloorSheetRepo
import com.kapilkoju.nepse.data.floorsheet.fetch.FloorSheetFetcher
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
class FloorSheetDumper(private val jobBuilderFactory: JobBuilderFactory,
                       private val stepBuilderFactory: StepBuilderFactory,
                       private val jobLauncher: JobLauncher,
                       private val floorSheetFetcher: FloorSheetFetcher,
                       private val floorSheetRepo: FloorSheetRepo) {

    @Scheduled(cron = "0 25 16 * * SUN-THU")
    fun schedule() {
        val now = LocalDateTime.now().toString()
        val param = JobParametersBuilder().addString("now", now).toJobParameters()
        jobLauncher.run(dumpFloorSheetJob(), param)
    }

    @Bean
    fun dumpFloorSheetJob(): Job {
        return jobBuilderFactory.get("dumpFloorSheetJob")
                .start(dumpFloorSheetStep())
                .build()
    }

    @Bean
    fun dumpFloorSheetStep(): Step {
        return stepBuilderFactory.get("dumpFloorSheetStep")
                .tasklet(dumpFloorSheetTasklet())
                .build()
    }

    @Bean
    fun dumpFloorSheetTasklet(): Tasklet {
        return Tasklet { _, _ ->
            val sheets = floorSheetFetcher.getFloorSheets()
            sheets.forEach {
                val sheet = floorSheetRepo.findOne(it.contractNo)
                sheet ?: floorSheetRepo.save(it)
            }
            RepeatStatus.FINISHED
        }
    }
}