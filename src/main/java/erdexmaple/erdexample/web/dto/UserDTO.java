package erdexmaple.erdexample.web.dto;

import erdexmaple.erdexample.domain.FollowEntity;
import erdexmaple.erdexample.domain.Record_PageEntity;
import erdexmaple.erdexample.domain.UserEntity;
import erdexmaple.erdexample.domain.enums.Ad;
import erdexmaple.erdexample.domain.enums.LoginStatus;
import erdexmaple.erdexample.domain.enums.Marketing;
import erdexmaple.erdexample.domain.enums.Provider;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String password;
    private String phone_number;
    private String nickname;
    private Provider provider;
    private String provider_id;
    private LoginStatus status;
    private LocalDate inactive_date;
    private Marketing marketing;
    private Ad ad;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private List<Record_PageEntity> record_pageEntityList;
    private List<FollowEntity> followEntityList;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setPhone_number(userEntity.getPhoneNumber());
        userDTO.setProvider(userEntity.getProvider());
        userDTO.setProvider_id(userEntity.getProvider_id());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setInactive_date(userEntity.getInactive_date());
        userDTO.setMarketing(userEntity.getMarketing());
        userDTO.setAd(userEntity.getAd());
        userDTO.setCreatedTime(userEntity.getCreated_at());
        userDTO.setUpdatedTime(userEntity.getUpdated_at());

        return userDTO;
    }
}
