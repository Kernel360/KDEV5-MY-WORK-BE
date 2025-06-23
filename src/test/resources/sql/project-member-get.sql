-- 회사
INSERT INTO company (id, name, contact_email, business_number, address, contact_phone_number, created_at, modified_at, type, file_name, deleted, detail)
VALUES (UUID_TO_BIN('6939d8be-1bf2-4f01-9189-12864e38d913'),
        '테스트 회사',
        'testcompany@example.com',
        '123-45-67890',
        '서울시 테스트구 테스트로 123',
        '02-1234-5678',
        NOW(), NOW(),
        'DEV',
        'logo.png',
        0,
        '테스트 회사 상세 정보');

-- 프로젝트
INSERT INTO project (id, name, start_at, end_at, step, created_at, modified_at, detail, deleted)
VALUES (UUID_TO_BIN('01974f0b-5c7a-7fa2-9aba-1323490b77e9'),
        '테스트 프로젝트',
        NOW(),
        NOW(),
        'STEP1',
        NOW(),
        NOW(),
        '테스트용 프로젝트입니다',
        0);

-- 멤버추가
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('c03b1d71-a83d-42af-8c5e-d373cd506e70', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '최지현', '개발팀', '부장',
             'ANONYMOUS', '010-1239-5705', 'user3@example.com', 'pass3', 0,
             NOW(), NOW(), '2006-02-14 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('51a58807-bf20-4160-b4e0-edabba6df8f9', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '오세훈', '디자인팀', '사원',
             'CLIENT_ADMIN', '010-8878-3310', 'user4@example.com', 'pass4', 0,
             NOW(), NOW(), '1978-05-21 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('346cdf98-f47b-4d13-bbe4-bd3d0ea48504', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '최지현', '디자인팀', '과장',
             'SYSTEM_ADMIN', '010-1334-3788', 'user5@example.com', 'pass5', 0,
             NOW(), NOW(), '1980-11-17 07:11:55'
         );

-- 할당 멤버
INSERT INTO project_member (id, project_id, member_id, manager, deleted, created_at)
VALUES
    (
        UUID_TO_BIN('01974f20-3bbe-7d0d-a27e-097667886779'),
        UUID_TO_BIN('01974f0b-5c7a-7fa2-9aba-1323490b77e9'),
        UUID_TO_BIN('51a58807-bf20-4160-b4e0-edabba6df8f9'),
        0,
        0,
        NOW()
    ),
    (
        UUID_TO_BIN('01974f20-5db9-70af-a445-ea979366a7cb'),
        UUID_TO_BIN('01974f0b-5c7a-7fa2-9aba-1323490b77e9'),
        UUID_TO_BIN('346cdf98-f47b-4d13-bbe4-bd3d0ea48504'),
        0,
        0,
        NOW()
    );
