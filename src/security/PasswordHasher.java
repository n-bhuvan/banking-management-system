package security;

import java.security.MessageDigest;

public class PasswordHasher {

    // Hash Password
    public static String hashPassword(
            String password) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance(
                            "SHA-256");

            byte[] hashBytes =
                    md.digest(
                            password.getBytes());

            StringBuilder builder =
                    new StringBuilder();

            for (byte b : hashBytes) {

                builder.append(
                        String.format(
                                "%02x",
                                b));
            }

            return builder.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}