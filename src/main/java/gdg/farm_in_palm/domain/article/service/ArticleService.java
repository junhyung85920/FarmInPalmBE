package gdg.farm_in_palm.domain.article.service;

import gdg.farm_in_palm.domain.article.Article;
import gdg.farm_in_palm.domain.article.dto.ArticleInfoResDTO;
import gdg.farm_in_palm.domain.article.repository.ArticleRepository;
import gdg.farm_in_palm.exception.CustomException;
import gdg.farm_in_palm.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private static String Article_URL = "https://www.nongmin.com/list/20?page=";

    // 모든 Article 조회
    public List<ArticleInfoResDTO> getAllArticles() throws IOException {
        // crawling 먼저 하고 조회

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

    // url로부터 article crawl
    //@PostConstruct
    @Transactional
    public List<ArticleInfoResDTO> crawlArticles() throws IOException {
        List<ArticleInfoResDTO> articleInfoResDTOs = new ArrayList<>();

        int id_cnt = 1;

        Document document;
        Document innerDocument;

        Elements contents;
        Element innerElement;

        // article table 내용 삭제
        //articleRepository.deleteAll();
        articleRepository.deleteAllInBatch();

        // 1~5페이지 크롤링
        for(int i=1; i<=5; i++) {
            document = Jsoup.connect(Article_URL + i).get();
            contents = document.select("div ul.common_list li");
            for(Element content : contents) {
                innerDocument = Jsoup.connect(content.select("a").attr("abs:href")).get();
                innerElement = innerDocument.selectFirst("input.siteViewContent");

                Article article = Article.builder()
                        .id((long) id_cnt++)
                        .articleTitle(content.select("pre.tit").text())
                        .articleContent(innerElement.attr("value"))
                        .articleDate(content.select("pre.data").text())
                        .articleImagePath(content.select("img.list_imgbox").attr("src"))
                        .build();

                articleRepository.save(article);

                ArticleInfoResDTO articleInfoResDTO = ArticleInfoResDTO.builder()
                        .articleId(article.getId())
                        .articleTitle(article.getArticleTitle())
                        .articleContent(article.getArticleContent())
                        .articleDate(article.getArticleDate())
                        .articleImagePath(article.getArticleImagePath())
                        .build();
                articleInfoResDTOs.add(articleInfoResDTO);
            }
        }

        return articleInfoResDTOs;
    }
}
