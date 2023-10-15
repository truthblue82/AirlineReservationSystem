INSERT INTO roles ("name") VALUES ('ROLE_CUSTOMER');
INSERT INTO roles ("name") VALUES ('ROLE_ADMIN');

INSERT INTO users (username,email,password,fullname, roles) VALUES ('admin', 'tpm88941@gmail.com','$2a$10$AjAMVfhUkZ5v.HPCw5.nO.ss0dHFKNTKDEtocyXSZvX5TfnBDvtHq','Admin Admin',1);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

