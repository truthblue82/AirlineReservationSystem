import { Role } from "./role";

export interface User {
  id: number;
  username: string;
  password: string;
  email: string;
  fname?: string;
  mname?: string;
  lname?: string;
  phone?: string;
  active?: boolean;
  roles: Role[];
}
