INSERT INTO company (
    deleted,
    created_at,
    modified_at,
    id,
    name,
    contact_phone_number,
    business_number,
    address,
    contact_email,
    detail,
    logo_image_path,
    type
) VALUES (
             0,
             NOW(),
             NOW(),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             'Test Company',
             '+82-10-0000-0000',
             '1234567890',
             '123 Test Street, Test City',
             'testcompany@example.com',
             'Test company for documentation test',
             NULL,
             'CLIENT'
         );

INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('60828da5-dc7c-4b8f-ace4-2833e5f74c24', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '오세훈', '디자인팀', '차장',
             'DEV_ADMIN', '010-4387-3160', 'user0@example.com', 'pass0', 0,
             NOW(), NOW(), '1988-01-13 07:11:55'
         );