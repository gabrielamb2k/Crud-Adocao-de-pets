CREATE TYPE tipo_pet AS ENUM ('CACHORRO', 'GATO', 'PASSARO');

CREATE TABLE pets(
 id BIGSERIAL PRIMARY KEY,
 name varchar(255),
 tipo tipo_pet,
 age Integer,
 description varchar(255),
 user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
)