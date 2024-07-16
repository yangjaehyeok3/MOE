package erdexmaple.erdexample.domain;

import erdexmaple.erdexample.domain.common.BaseEntity;
import erdexmaple.erdexample.domain.enums.District;
import erdexmaple.erdexample.domain.enums.Region;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "region")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "VARCHAR(10)")
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "VARCHAR(800)")
    private District district;

    @OneToMany(mappedBy = "regionEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ExhibitionEntity> ExhibitionEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "regionEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Popup_StoreEntity> Popup_StoreEntityList = new ArrayList<>();


}
