INSERT IGNORE INTO roles (name) VALUES ('ROLE_CUSTOMER');
INSERT IGNORE INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT IGNORE INTO users (username,email,password) VALUES ('admin', 'tpm88941@gmail.com','$2a$10$AjAMVfhUkZ5v.HPCw5.nO.ss0dHFKNTKDEtocyXSZvX5TfnBDvtHq');

INSERT IGNORE INTO user_roles (user_id, role_id) VALUES (1, 2);

