package erdexmaple.erdexample.domain;


import erdexmaple.erdexample.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name="follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class FollowEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
//애매
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "exhibition_id")
   private ExhibitionEntity exhibitionEntity;
//이것도 애매
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "popup_store_id")
   private Popup_StoreEntity popup_storeEntity;
}
