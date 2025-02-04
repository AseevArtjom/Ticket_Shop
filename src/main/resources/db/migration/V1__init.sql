CREATE TYPE status AS ENUM('FREE', 'SOLD');

CREATE TABLE IF NOT EXISTS public.Roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR
);

CREATE TABLE IF NOT EXISTS public.App_user
(
    user_id            SERIAL PRIMARY KEY,
    user_name          VARCHAR,
    encrypted_password VARCHAR,
    enabled            BOOLEAN,
    email              VARCHAR,
    phone              VARCHAR
);

CREATE TABLE IF NOT EXISTS User_Role
(
    id      SERIAL PRIMARY KEY,
    user_id integer,
    role_id integer,
    FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles (role_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS public.Places
(
    id      SERIAL PRIMARY KEY,
    address VARCHAR,
    name    VARCHAR
);

CREATE TABLE IF NOT EXISTS public.Events
(
    id         SERIAL PRIMARY KEY,
    event_date DATE,
    name       VARCHAR,
    place_id    INT,
    FOREIGN KEY (place_id) REFERENCES Places (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.Tickets
(
    id       SERIAL PRIMARY KEY,
    cost     money,
    number   INT,
    status   status,
    user_id  INT,
    event_id INT,
    FOREIGN KEY (user_id) REFERENCES App_user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Events (id) ON DELETE CASCADE
);

