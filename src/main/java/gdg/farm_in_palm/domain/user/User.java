package gdg.farm_in_palm.domain.user;


import gdg.farm_in_palm.domain.monitor.Monitor;
import gdg.farm_in_palm.domain.stock.Stock;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String refreshToken;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Monitor> monitors;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Stock> stocks;

    @Builder
    public User(String loginId, String password, String name, String refreshToken, List<Monitor> monitors, List<Stock> stocks) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.refreshToken = refreshToken;
        this.monitors = monitors;
        this.stocks = stocks;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getUsername()));
    }

    @Override
    public String getUsername() {
        return loginId;
    }
}
