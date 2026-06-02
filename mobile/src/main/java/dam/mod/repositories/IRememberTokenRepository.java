package dam.mod.repositories;

import dam.mod.models.RememberToken;
import java.util.List;

public interface IRememberTokenRepository {

    boolean saveToken(int userId, String tokenHash, String expiresAt);

    RememberToken findByHash(String tokenHash);

    List<RememberToken> findAllValid();

    boolean deleteByUserId(int userId);
}