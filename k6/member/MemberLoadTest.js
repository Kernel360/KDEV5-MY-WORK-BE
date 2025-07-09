import { check, group, sleep } from 'k6';
import { loginMember } from '../api/MemberApi.js';
import {loadMemberFromCSV} from "../base/UserSampleReader.js";

export let options = {
  rps: 10,
  // iterations: 100,
  vus: 100,
  duration: '3m',
}

const memberLoginData = loadMemberFromCSV()

export default function() {

  group('Member Login Load Test', function() {
    const userIndex = __VU - 1;
    const user = memberLoginData[userIndex];

    let response = loginMember({
      email: user.email,
      password: '1234'
    });

    check(response, {
      '[member login] status code 200': (r) => r.status === 200
    })
  })
}
