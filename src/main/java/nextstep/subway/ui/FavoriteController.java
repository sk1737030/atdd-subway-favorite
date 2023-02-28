package nextstep.subway.ui;

import nextstep.member.domain.LoginUser;
import nextstep.member.ui.LoginedUser;
import nextstep.subway.applicaion.FavoriteService;
import nextstep.subway.applicaion.dto.FavoriteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<Void> createFavorite(@LoginedUser LoginUser loginUser, @RequestBody FavoriteRequest favoriteRequest) {
        Long favoriteId = favoriteService.createFavorite(loginUser.getUserId(), Long.parseLong(favoriteRequest.getSource()), Long.parseLong(favoriteRequest.getTarget()));
        return ResponseEntity.created(URI.create(String.format("/favorites/%d", favoriteId))).build();
    }

}
