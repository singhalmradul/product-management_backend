mkdir -p ssl/certs ssl/private
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout ssl/private/ssl.key \
    -out ssl/certs/ssl.crt \
    -subj "/C=US/ST=State/L=City/O=Organization/OU=OrgUnit/CN=localhost"
