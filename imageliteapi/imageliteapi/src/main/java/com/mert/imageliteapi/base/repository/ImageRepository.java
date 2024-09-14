package com.mert.imageliteapi.base.repository;

import com.mert.imageliteapi.domain.entity.Image;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    /**
     * @param extension
     * @param query
     * @return SELECT * FROM IMAGE WHERE 1=1 AND EXTENSION 'PNG' AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY')
     */
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        Specification<Image> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
        Specification<Image> spec = Specification.where(conjunction);

        if (extension != null) {
            Specification<Image> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
            spec = spec.and(extensionEqual);
        }
        // query != null kullanmamızın sebebi, query'nin boş olabileceği durumda tüm kayıtları getirmesini engellemek
        if (StringUtils.hasText(query)) {
            Specification<Image> nameLike = (root, q, cb) -> cb.like(cb.upper(root.get("name")),"%" + query.toUpperCase() + "%");
            Specification<Image> tagsLike = (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + query.toUpperCase() + "%")

            Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);
        }

        return findAll(spec);
    }
}
