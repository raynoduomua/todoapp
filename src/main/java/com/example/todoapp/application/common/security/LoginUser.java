package com.example.todoapp.application.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todoapp.infrastructure.entity.common.Account;

public class LoginUser implements UserDetails {

	//	Entityクラス
	private final Account user;

	//	コンストラクタ
	public LoginUser(Account user) {
		this.user = user;
	}

	// Entityクラスである、Userオブジェクトのゲッター
	public Account getUser() {
		return this.user;
	}

	// ユーザーの認証に使用されるパスワードを返却する
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	// ユーザーの認証に使用されるユーザー名を返却する
	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	// ユーザーに付与された権限を返却する
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.user.isAdmin() == true) {
			return AuthorityUtils.createAuthorityList("ADMIN", "GENERAL");
		}
		return AuthorityUtils.createAuthorityList("GENERAL");
	}

	// アカウントの有効期限の状態を判定する
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// アカウントのロック状態を判定する
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 資格情報の有効期限の状態を判定する
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 有効なユーザかを判定する
	@Override
	public boolean isEnabled() {
		return true;
	}
}
