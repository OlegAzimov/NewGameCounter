package com.azimov.mygameapp.security;

import com.azimov.mygameapp.models.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class GameUserDetails implements UserDetails {
    private final GameUser gameUser;
    public GameUserDetails(GameUser gameUser) {
        this.gameUser = gameUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.gameUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.gameUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public GameUser getGameUser(){
        return this.gameUser;
    }
}
