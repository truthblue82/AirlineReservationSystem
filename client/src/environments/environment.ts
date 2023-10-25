// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
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
  GATEWAY_OAUTH2_URI_PASSWORD: 'secret-writer',
  GATEWAY_REGISTRATION_USER_CMD: 'curl -X POST -H "Content-Type: application/json" -d \'{"firstName": "{FIRSTNAME}", "lastName": "{LASTNAME}", "password": "{PASSWORD}", "email": "{EMAIL}"}, "phone": "{PHONE}"\' -k {GATEWAY_USER_REGISTER_URL}',
  GATEWAY_USERINFO_CMD: 'curl -X GET -H "Content-Type: application/json" -d \'\' ',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
