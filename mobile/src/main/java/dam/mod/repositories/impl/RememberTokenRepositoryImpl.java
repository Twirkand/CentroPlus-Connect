package dam.mod.repositories.impl;

import dam.mod.models.RememberToken;
import dam.mod.repositories.IRememberTokenRepository;
import dam.mod.repositories.sqlite.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RememberTokenRepositoryImpl implements IRememberTokenRepository {

    @Override
    public boolean saveToken(int userId, String tokenHash, String expiresAt) {

        String sql = "INSERT INTO remember_tokens (user_id, token_hash, expires_at) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, tokenHash);
            ps.setString(3, expiresAt);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error guardando token", e);
        }
    }

    @Override
    public RememberToken findByHash(String tokenHash) {

        String sql = "SELECT * FROM remember_tokens WHERE token_hash = ? AND expires_at > datetime('now')";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tokenHash);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new RememberToken(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("token_hash"),
                            rs.getString("expires_at"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error buscando token", e);
        }

        return null;
    }

    @Override
    public List<RememberToken> findAllValid() {

        String sql = "SELECT * FROM remember_tokens WHERE expires_at > datetime('now')";

        List<RememberToken> list = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                list.add(new RememberToken(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("token_hash"),
                        rs.getString("expires_at")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error leyendo tokens", e);
        }

        return list;
    }

    @Override
    public boolean deleteByUserId(int userId) {

        String sql = "DELETE FROM remember_tokens WHERE user_id = ?";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error borrando tokens", e);
        }
    }
}