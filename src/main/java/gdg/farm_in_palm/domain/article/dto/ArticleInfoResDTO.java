package gdg.farm_in_palm.domain.article.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleInfoResDTO {
    private Long articleId;
    private String articleTitle;
    private String articleContent;
    private String articleDate;
    private String articleImagePath;

    @Builder
    public ArticleInfoResDTO(Long articleId, String articleTitle, String articleContent, String articleDate, String articleImagePath) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
        this.articleImagePath = articleImagePath;
    }
}
