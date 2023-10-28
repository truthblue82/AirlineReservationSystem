export const environment = {
  production: true,
  ADMIN_EMAIL: "test@gmail.com",
  APP_BASE_URL: "http://localhost:4000",
  OAUTH2_BASE_URL: "http://localhost:8771",

  GATEWAY_BASE_URL: "https://localhost:8443",
  GATEWAY_USER_REGISTER_URI: "users/registration",
  GATEWAY_AUTHEN_URI: "oauth2/authorize?response_type=code&client_id=writer&redirect_uri={REDIRECT_URI}&scope=product:write product:read",
  GATEWAY_OAUTH2_URI: "oauth2/token",
  GATEWAY_GRANT_TYPE: "authorization_code",
  GATEWAY_USERINFO_URI: "users/info",
  GATEWAY_OAUTH2_URI_USERNAME: 'writer',
  GATEWAY_OAUTH2_URI_PASSWORD: 'secret-writer'
};
