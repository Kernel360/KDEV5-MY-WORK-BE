import http from 'k6/http';
import baseUrl from '../base/baseUrl.js';

export function loginMember(member) {
  let headers = {
    'Content-Type': 'application/json'
  };

  const url = `${baseUrl}/api/login`;
  return http.post(url, JSON.stringify(member), { headers: headers });
}
