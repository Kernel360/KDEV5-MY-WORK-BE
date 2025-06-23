INSERT INTO company (
    id, name, detail, business_number, address, type,
    contact_phone_number, contact_email, file_name, created_at, modified_at, deleted
) VALUES (
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
             '테스트 회사',
             '테스트 회사 설명',
             '123-45-67890',
             '서울시 테스트구 테스트로 123',
             'DEV',
             '010-0000-0000',
             'company@example.com',
             'logo.png',
             NOW(),
             NOW(),
             false
         );

INSERT INTO member (
    id, name, email, password, role, birth_date, phone_number,
    company_id, department, position, created_at, modified_at, deleted
)
VALUES (
           UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
           '관리자',
           'admin@example.com',
           '$2a$12$0Q8Lv6HuY2k3/uP7.dfr1eTl0nMpEqD2kkJ7OJDDK2F4KrwKky1qm',
           'SYSTEM_ADMIN',
           NOW(),
           '010-1234-5678',
           UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
           '개발팀',
           'CTO',
           NOW(),
           NOW(),
           false
       );
