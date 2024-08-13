package Monitoring.Project.weather.user;


import Monitoring.Project.weather.weathers.now.NowResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/userCreate")
    public User register(@RequestBody RegisterUserRequestDto request) {
        return userService.create(request);
    }

    // 로그인
    @PostMapping("/login")
    public String  login(@Valid @RequestBody LoginRequest request) {
        return userService.authenticateAndGenerateToken(request);
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

    // 로그아웃
    @PostMapping("/logout")
    public void logOut(@LoginUser String userEmail){
        userService.logOut(userEmail);
    }

    // 즐겨찾기 목록 등록
    @PutMapping("/like/{userId}")
    public void likeCities(@RequestBody LikeRequestDto requestDto, @PathVariable Long userId) {
        userService.likeCities(requestDto, userId);
    }

    // 즐겨찾기 목록 조회
    @GetMapping("/likedCities/{userId}")
    public List<NowResponseDto> readAllLikedCities(@PathVariable Long userId) {
        return userService.readAllLikedCities(userId);
    }

}