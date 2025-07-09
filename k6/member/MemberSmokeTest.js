import http from 'k6/http';
import {check} from 'k6';
import { loginMember } from '../api/MemberApi.js';

const member = {
  email: 'admin@example.com',
  password: 'password1234'
}

export default function () {
  let response = loginMember(member);
  check(response, { 'status code 200': (r) => r.status === 200 });

}
