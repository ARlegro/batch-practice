package com.bus.springbatch.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FileDeletingTaskletV2 implements Tasklet {



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Logic to delete files
        System.out.println("Files deleted successfully.");
        return RepeatStatus.FINISHED;
    }
//        return (contribution, chunkContext) -> {
//            // Logic to delete files
//            System.out.println("Files deleted successfully.");
//            return RepeatStatus.FINISHED;
//        };

}
