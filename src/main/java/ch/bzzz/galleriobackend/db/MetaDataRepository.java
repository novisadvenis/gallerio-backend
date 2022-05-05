package ch.bzzz.galleriobackend.db;

import ch.bzzz.galleriobackend.model.MetaDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Repository Class for MetaDataModel Entity
 */
@RepositoryRestResource(collectionResourceRel = "exif", path = "exif")
public interface MetaDataRepository extends JpaRepository<MetaDataModel, Long> {
    Optional<MetaDataModel> findById(Long aLong);
    <S extends MetaDataModel> List<S> saveAllAndFlush(Iterable<S> entities);
}
