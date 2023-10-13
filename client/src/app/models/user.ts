import { Role } from "./role";

export interface User {
  username: string;
  password: string;
  email: string;
  fname: string;
  mname: string;
  lname: string;
  phone?: string;
  active?: boolean;
  roles: Role[];
}
