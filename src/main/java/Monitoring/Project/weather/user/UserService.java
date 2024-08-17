package Monitoring.Project.weather.user;


import Monitoring.Project.weather.weathers.KoreanCity;
import Monitoring.Project.weather.weathers.now.NowResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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


        jwtProvider.clearBlacklistedTokens();
        User user = authenticate(request);


        return generateToken(user);
    }

    // 인증
    private User authenticate(LoginRequest request) {

        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("ID 또는 PW가 틀립니다"));

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
    public User create(RegisterUserRequestDto request) {

        LocalDate birthday;
        birthday = LocalDate.parse(request.birthday());


        User newUser = userRepository.save(new User(request.email(), SecurityUtils.sha256Encrypt(request.password()), request.nickname(), birthday));

        return newUser;
    }


    // 회원 탈퇴
    @Transactional
    public void deleteByEmail(String email) {

        userRepository.deleteByEmail(email);
    }

    // 회원 정보 조회
    public UserResponse getCurrentUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException(""));
        return new UserResponse(user.getEmail(), user.getNickname());
    }

    // 로그아웃
    public void logOut(String email) {

        jwtProvider.logout(email);
    }

    // 즐겨찾기 목록 등록
    @Transactional
    public void likeCities(LikeRequestDto requestDto, String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("해당 회원이 존재하지 않습니다."));

        user.setLikedList(requestDto.cities().stream().map(c -> new LikedList(c)).toList());

        userRepository.save(user);
    }

    // 즐겨찾기 목록 조회
    public List<LikeRequestDto> readAllLikedCities(String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("해당 회원이 존재하지 않습니다."));


        List<KoreanCity> likedCities = user.getLikedList().stream().map(LikedList::getLikedcity).collect(Collectors.toList());


        LikeRequestDto likeRequestDto = new LikeRequestDto(likedCities);


        return List.of(likeRequestDto);
    }

    // 즐겨찾기 목록 삭제
    @Transactional
    public void deleteLikedCities(LikeRequestDto requestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("해당 회원이 존재하지 않습니다."));


        List<LikedList> currentLikedList = user.getLikedList();


        List<KoreanCity> citiesToRemove = requestDto.cities();


        currentLikedList.removeIf(likedCity -> citiesToRemove.stream().anyMatch(cityToRemove -> cityToRemove.equals(likedCity.getLikedcity())));


        user.setLikedList(currentLikedList);


        userRepository.save(user);
    }


}