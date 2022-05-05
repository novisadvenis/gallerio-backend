package ch.bzzz.galleriobackend.util;

import ch.bzzz.galleriobackend.db.ImageRepository;
import ch.bzzz.galleriobackend.model.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class InitDB implements ApplicationListener<ContextRefreshedEvent> {

    private ImageRepository repository;

    @Autowired
    public InitDB(ImageRepository repository){
        this.repository = repository;
    }

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(InitDB.class));

    public static int counter;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("Increment counter");


        /*List<ImageModel> result = repository.findAll().parallelStream().peek(imageModel -> {
            counter++;
            try {
                byte[] thumbnail = Util.createThumbnail(new ByteArrayInputStream(imageModel.getImageByte()),
                        imageModel.getName(),
                        imageModel.getType());
                imageModel.setThumbnail(thumbnail);
                LOG.info("object done : counter "+ counter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());

        repository.saveAllAndFlush(result);

        LOG.info("Increment counter");*/

    }
}
