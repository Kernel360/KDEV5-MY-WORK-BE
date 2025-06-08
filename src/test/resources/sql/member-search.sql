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
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('dcb1e8c0-ca83-469c-95ef-ad0aa80aba1b', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '마케팅팀', '부장',
             'ANONYMOUS', '010-1104-8307', 'user1@example.com', 'pass1', 0,
             NOW(), NOW(), '1991-07-31 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('6c5ce809-ae4e-4b51-acbb-59c9f98c232c', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '이영희', '개발팀', '차장',
             'CLIENT_ADMIN', '010-6670-2548', 'user2@example.com', 'pass2', 1,
             NOW(), NOW(), '1985-12-05 07:11:55'
         );
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
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('ca017d65-dec2-4c85-bb2e-e7cc710d21fa', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '정해인', '디자인팀', '대리',
             'USER', '010-4540-2469', 'user6@example.com', 'pass6', 1,
             NOW(), NOW(), '1973-03-30 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('e3fa1c40-3cad-40e9-a7e0-e640c2fe92d9', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '이영희', '인사팀', '대리',
             'CLIENT_ADMIN', '010-9361-1384', 'user7@example.com', 'pass7', 0,
             NOW(), NOW(), '1995-06-23 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('59ecb592-d0e8-4e28-99f7-f8d2782d85db', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '한상우', '기획팀', '사원',
             'DEV_ADMIN', '010-3675-9481', 'user8@example.com', 'pass8', 0,
             NOW(), NOW(), '1994-11-04 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('9791cae4-a674-4f8c-9d5b-b2230f81b2a1', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '디자인팀', '부장',
             'ANONYMOUS', '010-1379-8658', 'user9@example.com', 'pass9', 0,
             NOW(), NOW(), '1979-12-26 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('95b832d1-87bf-46ec-ae4a-301d2e46d8a5', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '강다연', '개발팀', '사원',
             'SYSTEM_ADMIN', '010-6492-5274', 'user10@example.com', 'pass10', 1,
             NOW(), NOW(), '1970-09-03 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('c72f2b1a-0d78-4e6b-b28a-fc07a583e07a', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '오세훈', '개발팀', '과장',
             'USER', '010-4870-9289', 'user11@example.com', 'pass11', 0,
             NOW(), NOW(), '1993-07-09 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('d7d000e6-b2b7-4ef0-80c7-6ca2bfaef00f', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '최지현', '개발팀', '부장',
             'CLIENT_ADMIN', '010-7009-9968', 'user12@example.com', 'pass12', 0,
             NOW(), NOW(), '1981-03-30 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('db5cc227-cea7-4bde-be10-346beb3d42b8', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '강다연', '마케팅팀', '부장',
             'SYSTEM_ADMIN', '010-9044-6436', 'user13@example.com', 'pass13', 0,
             NOW(), NOW(), '1985-08-27 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('180d1c0e-a1fd-481c-9e25-cacb917d59f2', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '홍길동', '기획팀', '대리',
             'SYSTEM_ADMIN', '010-4387-6223', 'user14@example.com', 'pass14', 0,
             NOW(), NOW(), '1978-10-20 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('42e23ef2-f109-4905-9cfb-dac2b8f59c56', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '강다연', '개발팀', '과장',
             'DEV_ADMIN', '010-9471-9565', 'user15@example.com', 'pass15', 1,
             NOW(), NOW(), '2004-03-11 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('29a917ac-90de-4195-90d6-0a1e383027fb', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '박민수', '기획팀', '과장',
             'CLIENT_ADMIN', '010-2736-2107', 'user16@example.com', 'pass16', 0,
             NOW(), NOW(), '1980-12-10 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('3cbcaabc-34ae-4985-b538-b612612e40ca', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '오세훈', '디자인팀', '차장',
             'ANONYMOUS', '010-5509-5270', 'user17@example.com', 'pass17', 1,
             NOW(), NOW(), '1976-04-25 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('83d063e9-9e94-4193-89ee-b8207900a32f', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '홍길동', '기획팀', '과장',
             'DEV_ADMIN', '010-1846-6632', 'user18@example.com', 'pass18', 1,
             NOW(), NOW(), '1973-03-19 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('8815c5be-5689-4a88-a134-5effe4bec64a', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '강다연', '마케팅팀', '부장',
             'USER', '010-8944-1870', 'user19@example.com', 'pass19', 0,
             NOW(), NOW(), '1971-01-28 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('2c8aa32f-556b-4e47-8650-318e6b9271f5', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '강다연', '기획팀', '차장',
             'CLIENT_ADMIN', '010-4607-9613', 'user20@example.com', 'pass20', 0,
             NOW(), NOW(), '1978-05-11 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('37c26c3d-8747-4982-9988-ed789a534b58', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '인사팀', '차장',
             'SYSTEM_ADMIN', '010-5142-6774', 'user21@example.com', 'pass21', 0,
             NOW(), NOW(), '1977-09-11 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('f62a4ccf-02fc-4462-9c64-621e6724f26e', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '한상우', '마케팅팀', '부장',
             'CLIENT_ADMIN', '010-3918-3507', 'user22@example.com', 'pass22', 1,
             NOW(), NOW(), '1983-07-18 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('66256411-22d7-4670-adee-9d5251d62a3e', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '디자인팀', '대리',
             'CLIENT_ADMIN', '010-2359-9580', 'user23@example.com', 'pass23', 1,
             NOW(), NOW(), '1984-02-02 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('84921cd7-7da9-4807-8f0b-7392f7700646', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '기획팀', '과장',
             'DEV_ADMIN', '010-6722-1274', 'user24@example.com', 'pass24', 1,
             NOW(), NOW(), '1982-08-04 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('ae52370e-b873-4cee-aea7-8d73a2bb0aa1', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '정해인', '인사팀', '대리',
             'DEV_ADMIN', '010-3432-6210', 'user25@example.com', 'pass25', 0,
             NOW(), NOW(), '1972-01-19 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('9f62ac72-9138-49e1-92f7-8e2e76862eb8', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '한상우', '마케팅팀', '과장',
             'ANONYMOUS', '010-6829-1275', 'user26@example.com', 'pass26', 1,
             NOW(), NOW(), '2003-06-17 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('361e0c4e-28e1-4626-8180-b62523cf3b03', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '정해인', '디자인팀', '과장',
             'SYSTEM_ADMIN', '010-6587-1032', 'user27@example.com', 'pass27', 0,
             NOW(), NOW(), '1977-03-20 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('2e8e2a2b-4202-4d40-9880-2128b61e4c25', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '마케팅팀', '사원',
             'CLIENT_ADMIN', '010-5523-8257', 'user28@example.com', 'pass28', 1,
             NOW(), NOW(), '1984-11-28 07:11:55'
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted,
    created_at, modified_at, birth_date
) VALUES (
             UNHEX(REPLACE('0ba89569-479a-4a80-9823-48a967aa1d22', '-', '')),
             UNHEX(REPLACE('6939d8be-1bf2-4f01-9189-12864e38d913', '-', '')),
             '김철수', '기획팀', '과장',
             'CLIENT_ADMIN', '010-2594-1967', 'user29@example.com', 'pass29', 0,
             NOW(), NOW(), '1997-10-26 07:11:55'
         );