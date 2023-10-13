import { Role } from "./role";

export interface User {
  id: number;
  username: string;
  password: string;
  email: string;
  fullname?: string;
  phone?: string;
  active?: boolean;
  roles: Role[];
  token?: string;
}
