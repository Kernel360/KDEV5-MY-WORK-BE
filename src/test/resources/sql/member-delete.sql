-- 1) 테스트용 회사
INSERT INTO company (id, name, contact_phone_number, business_number,
                     address, contact_email, detail, type,
                     deleted, created_at, modified_at, logo_image_path)
VALUES (UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '테스트회사', '010-0000-0000', '000-00-00000',
        '테스트주소', 'test@company.com', '테스트용 회사', 'CLIENT',
        0, NOW(), NOW(), NULL);

INSERT INTO member (id, company_id, name, department, position,
                    role, phone_number, email, password, deleted, created_at, modified_at, birth_date)
VALUES (UNHEX(REPLACE('6516f3fe-057b-efdc-9aa9-87bf7b33a1d0', '-', '')),
        UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e', '-', '')),
        '이도현', '디자인팀', '차장',
        'USER', '010-2207-6881', 'user0@example.com', 'pass0', 0, NOW(), NOW(), NOW());
