package Monitoring.Project.weather.user;


import Monitoring.Project.weather.weathers.now.NowResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/userCreate")
    public User register(@Valid @RequestBody RegisterUserRequestDto request) {
        return userService.create(request);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String token = userService.authenticateAndGenerateToken(request);
        String nickname = userService.getUserNickname(request.email());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("nickname", nickname); // 닉네임도 같이 보냄

        System.out.println(token);
        return ResponseEntity.ok(response);
    }


    // 회원 탈퇴
    @DeleteMapping("/user")
    public void delete(@LoginUser String userEmail) {
        userService.deleteByEmail(userEmail);
    }

    // 토큰 발급
    @GetMapping("/me")
    public UserResponse getCurrentUser(@LoginUser String userEmail) {
        return userService.getCurrentUser(userEmail);
    }


    @GetMapping("/getUser")
    public RegisterUserRequestDto1 getuser(@LoginUser String userEmail){
        return userService.getCurrentUser1(userEmail);
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logOut(@LoginUser String userEmail) {
        userService.logOut(userEmail);
    }

    // 즐겨찾기 목록 등록
    @PutMapping("/like")
    public ResponseEntity<Void> likeCities(@RequestBody LikeRequestDto requestDto, @LoginUser String userEmail) {
        userService.likeCities(requestDto, userEmail);
        return ResponseEntity.ok().build();
    }

    // 즐겨찾기 목록 삭제
    @DeleteMapping("/deleteLike")
    public ResponseEntity<Void> deleteLike(@RequestBody LikeRequestDto requestDto, @LoginUser String userEmail) {
        userService.deleteLikedCities(requestDto, userEmail);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/likedCities")
    public List<LikeRequestDto> readAllLikedCities(@LoginUser String userEmail) {
        List<LikeRequestDto> Data1 = userService.readAllLikedCities(userEmail);
        return Data1;
    }

}