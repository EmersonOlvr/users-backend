# Gerar chave privada
openssl genpkey -algorithm RSA -out my_private.pem -pkeyopt rsa_keygen_bits:2048

# Gerar chave pública a partir da privada
openssl rsa -pubout -in my_private.pem -out my_public.pem