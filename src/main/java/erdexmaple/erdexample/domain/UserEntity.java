package erdexmaple.erdexample.domain;

import erdexmaple.erdexample.domain.common.BaseEntity;
import erdexmaple.erdexample.domain.enums.Ad;
import erdexmaple.erdexample.domain.enums.Marketing;
import erdexmaple.erdexample.domain.enums.Provider;
import erdexmaple.erdexample.domain.enums.LoginStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20)
    private String password;

    @Column(nullable = false,length = 11)
    private String phoneNumber;

    @Column(nullable = false,length = 100)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private Provider provider;

    @Column
    private String provider_id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private LoginStatus status;

    @Column
    private LocalDate inactive_date;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'NO'")
    private Ad ad;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'NO'")
    private Marketing marketing;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Record_PageEntity> Record_PageEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<FollowEntity> FollowEntityList = new ArrayList<>();


}
