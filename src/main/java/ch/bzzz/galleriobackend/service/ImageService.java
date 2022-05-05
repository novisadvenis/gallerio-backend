package ch.bzzz.galleriobackend.service;

import ch.bzzz.galleriobackend.db.MetaDataRepository;
import ch.bzzz.galleriobackend.db.ImageRepository;
import ch.bzzz.galleriobackend.model.MetaDataModel;
import ch.bzzz.galleriobackend.model.ImageModel;
import ch.bzzz.galleriobackend.util.Util;
import com.drew.imaging.ImageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements ImageServiceInterface {

    private ImageRepository imageRepository;
    private MetaDataRepository metaDataRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, MetaDataRepository metaDataRepository) {
        this.imageRepository = imageRepository;
        this.metaDataRepository = metaDataRepository;
    }

    @Override
    public ImageModel get(String name) {
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(name);
        return retrievedImage.isPresent() ? retrievedImage.get() : null;
    }

    @Override
    public ImageModel get(long id) {
        return null;
    }

    @Override
    public boolean deleteImage(String name) {
        return false;
    }

    @Override
    public boolean deleteImage(long id) {
        imageRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(long id, ImageModel imageModel) {
        return false;
    }

    @Override
    public boolean update(String name, ImageModel imageModel) {
        return false;
    }

    @Override
    public boolean save(MultipartFile file) throws IOException {
        List<MetaDataModel> metaDataModelList = new ArrayList<>();
        try {
            if(!file.isEmpty()) {
                InputStream inputStream = file.getInputStream();
                 metaDataModelList = Util.getMetaData(inputStream);
            }
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
        String contentType = file.getContentType().split("/")[1];

        byte[] thumbnailByte = Util.createThumbnail(file.getInputStream(), file.getName(), contentType);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getBytes(),
                contentType ,thumbnailByte, metaDataModelList);
        imageRepository.save(img);
        return true;
    }

    @Override
    public boolean updateMetaData(long metaDataId, String value) {
        Optional<MetaDataModel> metaDataModel = metaDataRepository.findById((long) metaDataId);
        if(metaDataModel.isPresent()) {
            metaDataModel.get().setValue(value);
            metaDataRepository.save(metaDataModel.get());
            return true;
        }
        return false;
    }

    @Override
    public List<ImageModel> getAll() {
        List<ImageModel> imageModelList = imageRepository.findAll();
        return imageModelList;
    }

    @Override
    public boolean updateImageName(String oldName, String newName) {
        Optional<ImageModel> imageModel =  imageRepository.findByName(oldName);
        if(imageModel.isPresent()) {
            imageModel.get().setName(newName);
            imageRepository.save(imageModel.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateImageName(long id, String name) {
        Optional<ImageModel> imageModel =  imageRepository.findById((long) id);
        if(imageModel.isPresent()) {
            imageModel.get().setName(name);
            imageRepository.save(imageModel.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMetaData(long id) {
        metaDataRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean saveMetaData(MetaDataModel metaDataModel) {
        metaDataRepository.save(metaDataModel);
        return true;
    }
}
