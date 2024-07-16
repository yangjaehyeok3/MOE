package erdexmaple.erdexample.service;

import erdexmaple.erdexample.domain.UserEntity;
import erdexmaple.erdexample.repository.UserRepository;
import erdexmaple.erdexample.web.dto.UserDTO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long getKakaoIdToken(String accessToken) throws Exception {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        return getIdTokenFromProvider(accessToken, reqURL);
    }

    public Long getNaverIdToken(String accessToken) throws Exception {
        String reqURL = "https://openapi.naver.com/v1/nid/me";
        return getIdTokenFromProvider(accessToken, reqURL);
    }

    private Long getIdTokenFromProvider(String accessToken, String reqURL) throws Exception {
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            System.out.println("response body : " + result);

            JsonObject element = JsonParser.parseString(result.toString()).getAsJsonObject();

            long idToken = element.get("id").getAsLong();
            System.out.println("id : " + idToken);

            br.close();
            return idToken;
        } catch (IOException e) {
            throw new Exception("서버에서 사용자 정보를 가져오는 중에 문제가 발생했습니다.", e);
        }
    }

    public UserDTO findUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.map(UserDTO::toUserDTO).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

