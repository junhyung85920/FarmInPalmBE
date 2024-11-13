package gdg.farm_in_palm.domain.article;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    private String articleTitle;
    private String articleContent;
    private String articleDate;
    private String articleImagePath;

    @Builder
    public Article(Long id, String articleTitle, String articleContent, String articleDate, String articleImagePath) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
        this.articleImagePath = articleImagePath;
    }
}
