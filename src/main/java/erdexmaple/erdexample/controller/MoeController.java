package erdexmaple.erdexample.controller;

import erdexmaple.erdexample.service.UserService;
import erdexmaple.erdexample.web.dto.ReissueTokenResponseDTO;
import erdexmaple.erdexample.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Moe")
public class MoeController {

    private static final Logger log = LoggerFactory.getLogger(MoeController.class);

    private final UserService userService;

    // /Moe 경로로 접속 시 /Moe/login으로 리디렉션
    @GetMapping
    public String redirectToLogin() {
        return "redirect:/Moe/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        // 로그인 페이지를 반환합니다. (뷰 템플릿에서 처리)
        return "login";
    }

    // 로그인 후 메인화면 정보
    @PostMapping("/main")
    @ResponseBody
    public UserDTO login(@RequestBody Long id) {
        UserDTO userDto = userService.findUserById(id);
        log.info(userDto.toString());
        return userDto;
    }

    // 카카오 로그인 처리
    @PostMapping("/kakao")
    public ResponseEntity<UserDTO> kakaoLogin(@RequestBody ReissueTokenResponseDTO reissueTokenResponseDto) {
        try {
            long idToken = userService.getKakaoIdToken(reissueTokenResponseDto.getAccessToken());
            UserDTO userDto = userService.findUserById(idToken);
            if (userDto != null) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while getting idToken: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 네이버 로그인 처리
    @PostMapping("/naver")
    public ResponseEntity<UserDTO> naverLogin(@RequestBody ReissueTokenResponseDTO reissueTokenResponseDto) {
        try {
            long idToken = userService.getNaverIdToken(reissueTokenResponseDto.getAccessToken());
            UserDTO userDto = userService.findUserById(idToken);
            if (userDto != null) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while getting idToken: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 카카오 로그인 뷰
    @GetMapping("/kakao")
    public String kakaoLogin() {
        return "kakao-login";
    }

    // 네이버 로그인 뷰
    @GetMapping("/naver")
    public String naverLogin() {
        return "naver-login";
    }
    @GetMapping("/follow")
    public String follow() {
        return "follow";
    }
}

