package gdg.farm_in_palm.domain.article.repository;

import gdg.farm_in_palm.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
