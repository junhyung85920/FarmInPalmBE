package gdg.farm_in_palm.domain.stock;

import gdg.farm_in_palm.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_id")
    private Long id;

    private String stockName;
    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Stock(Long id, String stockName, Integer stockQuantity, User user) {
        this.id = id;
        this.stockName = stockName;
        this.stockQuantity = stockQuantity;
        this.user = user;
    }
}
