export interface User {
  id?: string;
  username: string;
  password: string;
  email: string;
  fullname: string;
  phone: string;
  active?: boolean;
  roles: string[];
  token: string;
  rememberMe?: boolean;
}
