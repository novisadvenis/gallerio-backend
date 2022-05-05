package ch.bzzz.galleriobackend.db;

import ch.bzzz.galleriobackend.model.MetaDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "exif", path = "exif")
public interface MetaDataRepository extends JpaRepository<MetaDataModel, Long> {
    @Override
    Optional<MetaDataModel> findById(Long aLong);
    @Override
    <S extends MetaDataModel> List<S> saveAllAndFlush(Iterable<S> entities);
}
