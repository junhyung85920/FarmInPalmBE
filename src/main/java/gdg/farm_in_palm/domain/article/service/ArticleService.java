package gdg.farm_in_palm.domain.article.service;

import gdg.farm_in_palm.domain.article.Article;
import gdg.farm_in_palm.domain.article.dto.ArticleInfoResDTO;
import gdg.farm_in_palm.domain.article.repository.ArticleRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 모든 Article 조회
    public List<ArticleInfoResDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleInfoResDTO> articleInfoResDTOs = new ArrayList<>();

        for (Article article : articles) {
            ArticleInfoResDTO articleInfoResDTO = ArticleInfoResDTO.builder()
                    .articleId(article.getId())
                    .articleTitle(article.getArticleTitle())
                    .articleContent(article.getArticleContent())
                    .articleDate(article.getArticleDate())
                    .articleImagePath(article.getArticleImagePath())
                    .build();

            articleInfoResDTOs.add(articleInfoResDTO);
        }

        return articleInfoResDTOs;
    }


    // 특정 Article 조회
    public ArticleInfoResDTO getArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));

        return ArticleInfoResDTO.builder()
                .articleId(article.getId())
                .articleTitle(article.getArticleTitle())
                .articleContent(article.getArticleContent())
                .articleDate(article.getArticleDate())
                .articleImagePath(article.getArticleImagePath())
                .build();
    }
}
