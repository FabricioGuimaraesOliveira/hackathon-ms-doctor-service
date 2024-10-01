package com.fiap.hackathon_doctor.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JwtTokenUtil {

	private JwtTokenUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String generateToken(String email) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("fiap");

			return JWT.create().withClaim("email", email).sign(algorithm);
		}
		catch (JWTCreationException exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
