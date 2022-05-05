package ch.bzzz.galleriobackend.db;

import ch.bzzz.galleriobackend.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Repository Class for ImaageModel Entity
 */
@RepositoryRestResource(collectionResourceRel = "image", path = "image")
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    Optional<ImageModel> findById(Long aLong);
    void deleteById(Long aLong);
}

