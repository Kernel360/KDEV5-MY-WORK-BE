INSERT INTO member (id, name, email, password, role, birth_date, phone_number,
                    company_id, department, position, created_at, modified_at, deleted)
VALUES (
           -- system_admin
           UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
           '관리자_이름',
           'system_admin@example.com',
           '$2a$12$0Q8Lv6HuY2k3/uP7.dfr1eTl0nMpEqD2kkJ7OJDDK2F4KrwKky1qm', -- 'password123' bcrypt
           'SYSTEM_ADMIN',
           NOW(),
           '010-1234-5678',
           UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
           '시스템 어드민',
           '시스템 어드민 포지션',
           NOW(),
           NOW(),
           false),

       -- dev_admin
       (UNHEX(REPLACE('019739ea-e7eb-76b7-b5e1-b9dc3ea1e9c2', '-', '')),
        '개발사_어드민_이름',
        'dev_admin@example.com',
        '$2a$12$0Q8Lv6HuY2k3/uP7.dfr1eTl0nMpEqD2kkJ7OJDDK2F4KrwKky1qm', -- 'password123' bcrypt
        'DEV_ADMIN',
        NOW(),
        '010-1234-5678',
        UNHEX(REPLACE('019739eb-cd83-7223-b9b0-f186641aef55', '-', '')),
        '개발팀',
        'CTO',
        NOW(),
        NOW(),
        false),

       -- client_admin
       (UNHEX(REPLACE('019739ec-b50a-7f17-b375-3740a1bffcf1', '-', '')),
        '고객사_어드민_이름',
        'client_admin@example.com',
        '$2a$12$0Q8Lv6HuY2k3/uP7.dfr1eTl0nMpEqD2kkJ7OJDDK2F4KrwKky1qm', -- 'password123' bcrypt
        'CLIENT_ADMIN',
        NOW(),
        '010-1234-5678',
        UNHEX(REPLACE('019739ec-fea5-7fe1-986c-9d37518fbfda', '-', '')),
        '개발팀',
        'CTO',
        NOW(),
        NOW(),
        false),

       -- user
       (UNHEX(REPLACE('019739ed-f977-7c85-9138-7c8c0e2721d6', '-', '')),
        '사용자_이름',
        'user01@example.com',
        '$2a$12$0Q8Lv6HuY2k3/uP7.dfr1eTl0nMpEqD2kkJ7OJDDK2F4KrwKky1qm', -- 'password123' bcrypt
        'USER',
        NOW(),
        '010-1234-5678',
        UNHEX(REPLACE('019739ec-fea5-7fe1-986c-9d37518fbfda', '-', '')),
        '개발팀',
        '백엔드',
        NOW(),
        NOW(),
        false);
