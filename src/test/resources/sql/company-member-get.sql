-- 1) 테스트용 회사
INSERT INTO company (
    id, name, contact_phone_number, business_number,
    address, contact_email, detail, type,
    deleted, created_at, modified_at, logo_image_path
) VALUES (
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '테스트회사','010-0000-0000','000-00-00000',
             '테스트주소','test@company.com','테스트용 회사','CLIENT',
             0,NOW(),NOW(),NULL
         );

INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('6516f3fe-057b-efdc-9aa9-87bf7b33a1d0','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '이도현', '디자인팀', '차장',
             'USER', '010-2207-6881', 'user0@example.com', 'pass0', 0
         );

INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('f1dda69f-0532-5e35-85fc-736452a4db5e','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '유재석', '기획팀', '대리',
             'DEV_ADMIN', '010-7040-2662', 'user1@example.com', 'pass1', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('7e8447ac-050e-ff02-e1b7-791ab881c439','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '정우성', '개발팀', '차장',
             'DEV_ADMIN', '010-6471-4578', 'user3@example.com', 'pass3', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('3ae3ac3b-9c49-ec9a-832b-ccf429cd14de','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '고소영', '마케팅팀', '과장',
             'USER', '010-8251-5637', 'user4@example.com', 'pass4', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('c6655629-8cbe-8302-f4f7-a86e36de1747','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '이효리', '운영팀', '과장',
             'DEV_ADMIN', '010-7218-3459', 'user6@example.com', 'pass6', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('1e757f51-b183-5782-c0e2-4e3e84d59d72','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '한지민', '디자인팀', '부장',
             'DEV_ADMIN', '010-6970-7916', 'user7@example.com', 'pass7', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('33426203-c920-4cb0-a549-3b631474ec41','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '이효리', '마케팅팀', '차장',
             'USER', '010-1177-4865', 'user8@example.com', 'pass8', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('d5911bae-adeb-fba7-1346-b9f210ef02b5','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '김민수', '운영팀', '사원',
             'CLIENT_ADMIN', '010-5809-7539', 'user9@example.com', 'pass9', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('2c869f9a-7b03-94a0-e7e4-d60582805e0e','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '박지현', '운영팀', '부장',
             'DEV_ADMIN', '010-4859-4096', 'user10@example.com', 'pass10', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('74d7a648-cd80-2002-eea1-c7445cd5c7a5','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '정우성', '마케팅팀', '차장',
             'SYSTEM_ADMIN', '010-6043-5545', 'user14@example.com', 'pass14', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('436d7f7d-f231-b25e-b8fe-51e429c09a03','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '고소영', '기획팀', '부장',
             'CLIENT_ADMIN', '010-9378-3883', 'user18@example.com', 'pass18', 0
         );
INSERT INTO member (
    id, company_id, name, department, position,
    role, phone_number, email, password, deleted
) VALUES (
             UNHEX(REPLACE('097c9f01-b2f3-30fd-3fce-2ec5b8652d06','-','')),
             UNHEX(REPLACE('0196f7a6-10b6-7123-a2dc-32c3861ea55e','-','')),
             '김민수', '기획팀', '과장',
             'CLIENT_ADMIN', '010-7462-6671', 'user19@example.com', 'pass19', 0
         );