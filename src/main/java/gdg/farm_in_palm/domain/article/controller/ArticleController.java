package gdg.farm_in_palm.domain.article.controller;

import gdg.farm_in_palm.domain.article.dto.ArticleInfoResDTO;
import gdg.farm_in_palm.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    // 모든 Article 조회
    @GetMapping
    public List<ArticleInfoResDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    // 특정 Article 조회
    @GetMapping("/{articleId}")
    public ArticleInfoResDTO getArticleById(@PathVariable("articleId") Long articleId) {
        return articleService.getArticleById(articleId);
    }

    // Article Crawling
    @GetMapping("/crawling")
    public List<ArticleInfoResDTO> crawlArticles() throws IOException {
        return articleService.crawlArticles();
    }

}
