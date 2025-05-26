# Gerar chave privada
openssl genpkey -algorithm RSA -out auth-service-jwt-private.pem -pkeyopt rsa_keygen_bits:2048

# Gerar chave pública a partir da privada
openssl rsa -pubout -in auth-service-jwt-private.pem -out auth-service-jwt-public.pem