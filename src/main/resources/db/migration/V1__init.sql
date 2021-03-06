CREATE TABLE task(
     id SERIAL PRIMARY KEY,
     title VARCHAR (100) NOT NULL,
     created timestamp NOT NULL,
     done BOOLEAN,
     important  BOOLEAN,
     user_id BIGINT  NOT NULL
);

CREATE TABLE userInfo(
     id SERIAL PRIMARY KEY,
     lineId VARCHAR (250) NOT NULL,
     created timestamp NOT NULL
);

CREATE TABLE userToken(

     token  VARCHAR(500) NOT NULL,
     created timestamp NOT NULL,
     expire_at timestamp NOT NULL,
     user_id BIGINT NOT NULL

)