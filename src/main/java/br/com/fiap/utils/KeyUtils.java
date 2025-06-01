package br.com.fiap.utils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

    public static PrivateKey loadPrivateKeyFromEnv() {
        try {
            String keyPem = System.getenv("JWT_PRIVATE_KEY");
            if (keyPem == null) {
                throw new RuntimeException("JWT_PRIVATE_KEY não definida");
            }

            // Remove as linhas do cabeçalho PEM e espaços em branco
            String privateKeyPem = keyPem
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar chave privada", e);
        }
    }
}
