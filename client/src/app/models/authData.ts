import { Role } from "./role";

export interface AuthData {
  userId: string;
  email: string;
  roles: Role[];
  exp: number;
  accessToken: string;
}
