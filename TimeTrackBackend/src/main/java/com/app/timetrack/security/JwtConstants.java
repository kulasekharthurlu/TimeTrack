package com.app.timetrack.security;

public class JwtConstants {
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60*60*60l;
	public static final String SIGNING_KEY = "karnalkajsfhweiruhwfsajhfgkgjkjkarnalkajsfhweiruhwfsajhfgkgjkjkarnalkajsfhweiruhwfsajhfgkgjkj";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
}
