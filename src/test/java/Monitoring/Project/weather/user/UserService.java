package Monitoring.Project.weather.user;


import Apitest.demo.weather.weathers.weathernow.NowResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final SelectLikedCity selectLikedCity;

    public UserService(UserRepository userRepository, JwtProvider jwtProvider, SelectLikedCity selectLikedCity) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.selectLikedCity = selectLikedCity;
    }

    // 인증 & 토큰 생성
    public String authenticateAndGenerateToken(LoginRequest request) {

        User user = authenticate(request);

        return generateToken(user);
    }

    // 인증
    private User authenticate(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("ID 또는 PW가 틀립니다"));

        if (!user.authenticate(request.password())) {
            throw new IllegalArgumentException("ID 또는 PW가 틀립니다");
        }

        return user;
    }

    // 토큰 생성
    public String generateToken(User user) {

        return jwtProvider.createToken(user.getEmail());
    }


    // 회원 가입
    public void create(RegisterUserRequestDto request) {

        User newUser = userRepository.save(new User(
                request.email(),
                SecurityUtils.sha256Encrypt(request.password()),
                request.nickname(),
                request.birthday()
        ));
    }


    // 회원 탈퇴
    @Transactional
    public void deleteByEmail(String email) {

        userRepository.deleteByEmail(email);
    }

    // 회원 정보 조회
    public UserResponse getCurrentUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return new UserResponse(
                user.getEmail(),
                user.getNickname()
        );
    }

    // 로그아웃
    public void logOut(String email) {

        jwtProvider.logout(email);
    }

    // 즐겨찾기 목록 등록
    public void likeCities(LikeRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당 회원이 존재하지 않습니다.")
        );

        user.setLikedList(requestDto.cities());

        userRepository.save(user);
    }

    // 즐겨찾기 목록 조회
    public List<NowResponseDto> readAllLikedCities(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("해당 회원이 존재하지 않습니다.")
        );

        return selectLikedCity.addResponseCities(user);
    }
}