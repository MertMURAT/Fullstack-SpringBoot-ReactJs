package com.mert.imageliteapi.base.repository;

import com.mert.imageliteapi.base.repository.specification.GenericSpecs;
import com.mert.imageliteapi.domain.entity.Image;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.mert.imageliteapi.base.repository.specification.GenericSpecs.*;
import static com.mert.imageliteapi.base.repository.specification.ImageSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    /**
     * @param extension
     * @param query
     * @return SELECT * FROM IMAGE WHERE 1=1 AND EXTENSION 'PNG' AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY')
     */
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        Specification<Image> spec = where(conjunction());
        if (extension != null) {
            spec = spec.and(extensionEqual(extension));
        }
        // query != null kullanmamızın sebebi, query'nin boş olabileceği durumda tüm kayıtları getirmesini engellemek
        if (StringUtils.hasText(query)) {
            spec = spec.and(anyOf(nameLike(query), tagsLike(query)));
        }
        return findAll(spec);
    }
}
